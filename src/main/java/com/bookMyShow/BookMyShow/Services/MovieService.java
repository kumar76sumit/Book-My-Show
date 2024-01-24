package com.bookMyShow.BookMyShow.Services;

import com.bookMyShow.BookMyShow.DTOs.Request.MovieOwnerSignUpDto;
import com.bookMyShow.BookMyShow.Models.ApplicationUser;
import com.bookMyShow.BookMyShow.Models.Movie;
import com.bookMyShow.BookMyShow.Models.Ticket;
import com.bookMyShow.BookMyShow.Repositories.ApplicationUserRepository;
import com.bookMyShow.BookMyShow.Repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    ApplicationUserRepository applicationUserRepository;
    public ResponseEntity signUpAndAddMovies(MovieOwnerSignUpDto movieOwnerSignUpDto) {
        ApplicationUser movieOwner=new ApplicationUser();
        movieOwner.setName(movieOwnerSignUpDto.getName());
        movieOwner.setEmail(movieOwnerSignUpDto.getEmail());
        movieOwner.setPhoneNumber(movieOwnerSignUpDto.getPhoneNumber());
        movieOwner.setPassword(movieOwnerSignUpDto.getPassword());
        movieOwner.setType(movieOwnerSignUpDto.getUserType().toString());
        movieOwner.setAge(movieOwnerSignUpDto.getCompanyAge());
        applicationUserRepository.save(movieOwner);
        List<Movie> movies=movieOwnerSignUpDto.getMovies();
        for(Movie movie:movies) {
            movie.setMovieOwner(movieOwner);
            movieRepository.save(movie);
        }
        return new ResponseEntity<>(movieOwner,HttpStatus.CREATED);
    }
    public Movie findById(UUID id) {
        return movieRepository.findById(id).orElse(null);
    }
    public int getTotalTicketCount(Movie movie) {
        return movie.getTickets().size();
    }
    public int getBoxOfficeCollection(Movie movie) {
        int total=0;
        for(Ticket ticket:movie.getTickets()) {
            total+=ticket.getShow().getTicketPrice();
        }
        return total;
    }
}
