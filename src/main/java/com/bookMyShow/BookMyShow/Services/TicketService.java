package com.bookMyShow.BookMyShow.Services;

import com.bookMyShow.BookMyShow.Exceptions.ResourceNotFoundException;
import com.bookMyShow.BookMyShow.Exceptions.UnAuthorized;
import com.bookMyShow.BookMyShow.Exceptions.UserNotFoundException;
import com.bookMyShow.BookMyShow.Models.*;
import com.bookMyShow.BookMyShow.Repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    ApplicationUserService applicationUserService;
    @Autowired
    ShowService showService;
    @Autowired
    QrCodeGeneratorService qrCodeGeneratorService;
    @Autowired
    MailService mailService;
    @Autowired
    MovieService movieService;
    public Ticket bookTicket(String userEmail, UUID showId) {
        ApplicationUser applicationUser=applicationUserService.findByEmail(userEmail);
        if(applicationUser==null) throw new UserNotFoundException(String.format("User with email %s does not exist.",userEmail));

        if(applicationUser.getType().equals("movieOwner") || applicationUser.getType().equals("hallOwner")) throw new UnAuthorized(String.format("User with email %s is not authorized to book a ticket.",userEmail));

        Show show=showService.findById(showId);
        if(show==null) throw new ResourceNotFoundException(String.format("Show with showId %s does not exist.",showId.toString()));

        showService.updateAvailableTickets(show);
        Ticket ticket=new Ticket();
        ticket.setUser(applicationUser);
        ticket.setMovie(show.getMovie());
        ticket.setHall(show.getHall());
        ticket.setShow(show);
        ticketRepository.save(ticket);

        Movie movie=show.getMovie();
        Hall hall=show.getHall();

        String userMessage = String.format("Hey %s,\n" +
                "Congratulations!! your ticket got booked on our Accio Booking Application. Below are your ticket details:\n" +
                "1. Movie Name - %s\n" +
                "2. Hall Name - %s\n" +
                "3. Hall Address - %s\n" +
                "4. Date And timings - %s\n" +
                "5. Ticket Price- %d\n" +
                "\nHope you will enjoy your show, All The Best\n" +
                "BookMyShow-by sumit", applicationUser.getName(), movie.getMovieName(), hall.getHallName(), hall.getAddress(), show.getStartTime().toString(), show.getTicketPrice());
        String subject=String.format("Congratulations!! %s your ticket got generated !!", applicationUser.getName());
        try {
            qrCodeGeneratorService.generateQr(userMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mailService.generateMail(applicationUser.getEmail(),subject,userMessage,qrCodeGeneratorService.qrLocation);

        int totalTickets = movieService.getTotalTicketCount(movie);
        int totalIncome = movieService.getBoxOfficeCollection(movie);

        String movieMessage = String.format("Hii %s\n" +
                "Congratulations!! your ticket got sold\n" +
                "TotalTicketsSold : %d" +
                "TotalIncome : %d", movie.getMovieOwner().getName(), totalTickets, totalIncome);

        String movieSubject = String.format("Congratulations!! %s One more ticket sold", movie.getMovieOwner().getName());
        mailService.generateMail(movie.getMovieOwner().getEmail(), movieSubject, movieMessage);
        return ticket;
    }
}
