package com.bookMyShow.BookMyShow.Services;

import com.bookMyShow.BookMyShow.DTOs.Request.HallOwnerSignUpDto;
import com.bookMyShow.BookMyShow.Models.ApplicationUser;
import com.bookMyShow.BookMyShow.Models.Hall;
import com.bookMyShow.BookMyShow.Repositories.ApplicationUserRepository;
import com.bookMyShow.BookMyShow.Repositories.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HallService {
    @Autowired
    HallRepository hallRepository;
    @Autowired
    ApplicationUserRepository applicationUserRepository;
    public ResponseEntity signUpAndAddHalls(HallOwnerSignUpDto hallOwnerSignUpDto) {
        ApplicationUser hallOwner=new ApplicationUser();
        hallOwner.setName(hallOwnerSignUpDto.getName());
        hallOwner.setEmail(hallOwnerSignUpDto.getEmail());
        hallOwner.setPhoneNumber(hallOwnerSignUpDto.getPhoneNumber());
        hallOwner.setPassword(hallOwnerSignUpDto.getPassword());
        hallOwner.setType(hallOwnerSignUpDto.getUserType().toString());
        hallOwner.setAge(hallOwnerSignUpDto.getCompanyAge());
        applicationUserRepository.save(hallOwner);
        List<Hall> halls=hallOwnerSignUpDto.getHalls();
        for(Hall hall:halls) {
            hall.setHallOwner(hallOwner);
            hallRepository.save(hall);
        }
        return new ResponseEntity<>(hallOwner, HttpStatus.CREATED);
    }
    public Hall findById(UUID id) {
        return hallRepository.findById(id).orElse(null);
    }
}
