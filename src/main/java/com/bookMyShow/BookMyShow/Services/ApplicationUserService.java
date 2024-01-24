package com.bookMyShow.BookMyShow.Services;

import com.bookMyShow.BookMyShow.DTOs.Request.RegularUserSignUpDto;
import com.bookMyShow.BookMyShow.Models.ApplicationUser;
import com.bookMyShow.BookMyShow.Repositories.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService {
    @Autowired
    ApplicationUserRepository applicationUserRepository;
    public ResponseEntity signUp(RegularUserSignUpDto regularUserSignUpDto) {
        ApplicationUser regularUser=new ApplicationUser();
        regularUser.setName(regularUserSignUpDto.getName());
        regularUser.setEmail(regularUserSignUpDto.getEmail());
        regularUser.setPhoneNumber(regularUserSignUpDto.getPhoneNumber());
        regularUser.setPassword(regularUserSignUpDto.getPassword());
        regularUser.setType(regularUserSignUpDto.getUserType().toString());
        regularUser.setAge(regularUserSignUpDto.getAge());
        applicationUserRepository.save(regularUser);
        return new ResponseEntity<>(regularUser, HttpStatus.CREATED);
    }
    public ApplicationUser findByEmail(String email) {
        return applicationUserRepository.findByEmail(email);
    }
}
