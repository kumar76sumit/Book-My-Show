package com.bookMyShow.BookMyShow.Controllers;

import com.bookMyShow.BookMyShow.DTOs.Request.AddScreenDto;
import com.bookMyShow.BookMyShow.DTOs.Response.GeneralMessageDto;
import com.bookMyShow.BookMyShow.Exceptions.ResourceNotFoundException;
import com.bookMyShow.BookMyShow.Exceptions.UnAuthorized;
import com.bookMyShow.BookMyShow.Exceptions.UserNotFoundException;
import com.bookMyShow.BookMyShow.Services.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/screen")
public class ScreenController {
    @Autowired
    ScreenService screenService;
    @PostMapping("/addScreen")
    public ResponseEntity addScreens(@RequestBody AddScreenDto addScreenDto, @RequestParam String email) {
        try {
            screenService.addScreens(addScreenDto,email);
            return new ResponseEntity(new GeneralMessageDto("Screens added successfully."), HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(new GeneralMessageDto(e.getMessage()),HttpStatus.NOT_FOUND);
        } catch (UnAuthorized e) {
            return new ResponseEntity(new GeneralMessageDto(e.getMessage()),HttpStatus.UNAUTHORIZED);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new GeneralMessageDto(e.getMessage()),HttpStatus.NOT_FOUND);
        }
    }
}
