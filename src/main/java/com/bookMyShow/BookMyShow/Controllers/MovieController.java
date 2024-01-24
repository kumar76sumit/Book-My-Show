package com.bookMyShow.BookMyShow.Controllers;

import com.bookMyShow.BookMyShow.DTOs.Request.MovieOwnerSignUpDto;
import com.bookMyShow.BookMyShow.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    MovieService movieService;
    @PostMapping("/signUp")
    public ResponseEntity signUpAndAddMovies(@RequestBody MovieOwnerSignUpDto movieOwnerSignUpDto) {
        return movieService.signUpAndAddMovies(movieOwnerSignUpDto);
    }
}
