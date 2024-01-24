package com.bookMyShow.BookMyShow.Controllers;

import com.bookMyShow.BookMyShow.DTOs.Response.GeneralMessageDto;
import com.bookMyShow.BookMyShow.Exceptions.ResourceNotFoundException;
import com.bookMyShow.BookMyShow.Exceptions.UnAuthorized;
import com.bookMyShow.BookMyShow.Exceptions.UserNotFoundException;
import com.bookMyShow.BookMyShow.Models.Ticket;
import com.bookMyShow.BookMyShow.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;
    @PostMapping("/bookTicket")
    public ResponseEntity bookTicket(@RequestParam String email, @RequestParam UUID showId) {
        try {
            Ticket ticket=ticketService.bookTicket(email,showId);
            return new ResponseEntity<>(ticket, HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(new GeneralMessageDto(e.getMessage()),HttpStatus.NOT_FOUND);
        } catch (UnAuthorized e) {
            return new ResponseEntity<>(new GeneralMessageDto(e.getMessage()), HttpStatus.UNAUTHORIZED);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new GeneralMessageDto(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
