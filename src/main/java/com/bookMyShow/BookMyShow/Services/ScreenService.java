package com.bookMyShow.BookMyShow.Services;

import com.bookMyShow.BookMyShow.DTOs.Request.AddScreenDto;
import com.bookMyShow.BookMyShow.Exceptions.ResourceNotFoundException;
import com.bookMyShow.BookMyShow.Exceptions.UnAuthorized;
import com.bookMyShow.BookMyShow.Exceptions.UserNotFoundException;
import com.bookMyShow.BookMyShow.Models.ApplicationUser;
import com.bookMyShow.BookMyShow.Models.Hall;
import com.bookMyShow.BookMyShow.Models.Screen;
import com.bookMyShow.BookMyShow.Repositories.ScreenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ScreenService {
    @Autowired
    ScreenRepository screenRepository;
    @Autowired
    ApplicationUserService applicationUserService;
    @Autowired
    HallService hallService;
    public void addScreens(AddScreenDto addScreenDto,String email) {
        UUID hallId=addScreenDto.getHallId();
        List<Screen> screens=addScreenDto.getScreens();

        ApplicationUser applicationUser=applicationUserService.findByEmail(email);
        if(applicationUser==null) throw new UserNotFoundException(String.format("User with email %s does not exist.",email));
        if(!applicationUser.getType().equals("hallOwner")) throw new UnAuthorized(String.format("User with email %s is not authorized to add the screens.",email));
        Hall hall=hallService.findById(hallId);
        if(hall==null) throw new ResourceNotFoundException(String.format("Hall with hallId %s does not exist.",hallId.toString()));
        if(hall.getHallOwner().getId()!=applicationUser.getId()) throw new UnAuthorized(String.format("User with email %s does not authorized to add screens in hall %s",email,hall.getHallName()));
        for(Screen screen:screens) {
            screen.setHall(hall);
            screenRepository.save(screen);
        }
    }
}
