package com.bookMyShow.BookMyShow.Controllers;

import com.bookMyShow.BookMyShow.DTOs.Request.HallOwnerSignUpDto;
import com.bookMyShow.BookMyShow.Services.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hall")
public class HallController {
    @Autowired
    HallService hallService;
    @PostMapping("/signUp")
    public ResponseEntity signUpAndAddHalls(@RequestBody HallOwnerSignUpDto hallOwnerSignUpDto) {
        return hallService.signUpAndAddHalls(hallOwnerSignUpDto);
    }
}
