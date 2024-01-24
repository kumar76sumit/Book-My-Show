package com.bookMyShow.BookMyShow.Controllers;

import com.bookMyShow.BookMyShow.DTOs.Request.RegularUserSignUpDto;
import com.bookMyShow.BookMyShow.Services.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class ApplicationUserController {
    @Autowired
    ApplicationUserService applicationUserService;
    @PostMapping("/signUp")
    public ResponseEntity signUp(@RequestBody RegularUserSignUpDto regularUserSignUpDto) {
        return applicationUserService.signUp(regularUserSignUpDto);
    }
}
