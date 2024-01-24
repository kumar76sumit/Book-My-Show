package com.bookMyShow.BookMyShow.Services;

import com.bookMyShow.BookMyShow.DTOs.Request.AddShowDto;
import com.bookMyShow.BookMyShow.Exceptions.ResourceNotFoundException;
import com.bookMyShow.BookMyShow.Exceptions.UnAuthorized;
import com.bookMyShow.BookMyShow.Exceptions.UserNotFoundException;
import com.bookMyShow.BookMyShow.Models.*;
import com.bookMyShow.BookMyShow.Repositories.ScreenRepository;
import com.bookMyShow.BookMyShow.Repositories.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ShowService {
    @Autowired
    ShowRepository showRepository;
    @Autowired
    ApplicationUserService applicationUserService;
    @Autowired
    MovieService movieService;
    @Autowired
    HallService hallService;
    @Autowired
    ScreenRepository screenRepository;
    public Show addShows(AddShowDto addShowDto, String email) {
        ApplicationUser applicationUser=applicationUserService.findByEmail(email);
        // check for verified user
        if(applicationUser==null) throw new UserNotFoundException(String.format("User with email %s does not exist.",email));

        // check if user have rights
        if(!applicationUser.getType().equals("hallOwner")) throw new UnAuthorized(String.format("User with email %s is not authorized to add the shows.",email));

        UUID movieId=addShowDto.getMovieId();
        Movie movie=movieService.findById(movieId);
        // check if movie exist
        if(movie==null) throw new ResourceNotFoundException(String.format("Movie with movieId %s does not exist.",movieId.toString()));

        UUID hallId=addShowDto.getHallId();
        Hall hall=hallService.findById(hallId);
        // check if hall exist
        if(hall==null) throw new ResourceNotFoundException(String.format("Hall with hallId %s does not exist.",hallId.toString()));

        // check if the user and hallOwner are matching
        if(hall.getHallOwner().getId()!=applicationUser.getId()) throw new UnAuthorized(String.format("User with email %s does not authorized to add shows in hall %s",email,hall.getHallName()));

        // now hallOwner can add shows
        List<Screen> screens=new ArrayList<>();
        for(Screen screen: hall.getScreens()) {
            if(!screen.isStatus()) screens.add(screen);
        }

        // if all screens are engage
        if(screens.isEmpty()) throw new ResourceNotFoundException(String.format("Currently no screens are available to add show in hall with hallId %s.",hallId.toString()));
        Screen screen=screens.get(0);
        screen.setStatus(true);

        Show show=new Show();
        show.setHall(hall);
        show.setMovie(movie);
        show.setScreen(screen);
        show.setAvailableTickets(screen.getScreenCapacity());

        // converting the dateTime
        Date startDateTime=new Date();
        startDateTime.setHours(addShowDto.getHour());
        startDateTime.setMinutes(addShowDto.getMinutes());
        startDateTime.setSeconds(0);

        // calculating endTime
        Date endDateTime=new Date();
        int hours=(int)(addShowDto.getHour()+ movie.getDuration())%24;
        endDateTime.setHours(hours);
        endDateTime.setMinutes(addShowDto.getMinutes());
        endDateTime.setSeconds(0);

        show.setStartTime(startDateTime);
        show.setEndTime(endDateTime);
        show.setTicketPrice(addShowDto.getTicketPrice());

        screenRepository.save(screen);
        showRepository.save(show);
        return show;
    }
    public Show findById(UUID id) {
        return showRepository.findById(id).orElse(null);
    }
    public void updateAvailableTickets(Show show) {
        int availableTickets=show.getAvailableTickets()-1;
        showRepository.updateAvailableTickets(show.getId(),availableTickets);
    }
}
