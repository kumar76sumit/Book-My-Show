package com.bookMyShow.BookMyShow.Controllers;

import com.bookMyShow.BookMyShow.DTOs.Request.AddShowDto;
import com.bookMyShow.BookMyShow.DTOs.Response.GeneralMessageDto;
import com.bookMyShow.BookMyShow.Exceptions.ResourceNotFoundException;
import com.bookMyShow.BookMyShow.Exceptions.UnAuthorized;
import com.bookMyShow.BookMyShow.Exceptions.UserNotFoundException;
import com.bookMyShow.BookMyShow.Models.Show;
import com.bookMyShow.BookMyShow.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/show")
public class ShowController {
    @Autowired
    ShowService showService;
    @PostMapping("/addShow")
    public ResponseEntity addShows(@RequestBody AddShowDto addShowDto, @RequestParam String email) {
        try {
            Show show=showService.addShows(addShowDto,email);
            return new ResponseEntity<>(show, HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(new GeneralMessageDto(e.getMessage()),HttpStatus.NOT_FOUND);
        } catch (UnAuthorized e) {
            return new ResponseEntity<>(new GeneralMessageDto(e.getMessage()),HttpStatus.UNAUTHORIZED);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new GeneralMessageDto(e.getMessage()),HttpStatus.NOT_FOUND);
        }
    }
}
