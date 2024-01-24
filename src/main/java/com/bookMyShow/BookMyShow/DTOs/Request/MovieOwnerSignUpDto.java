package com.bookMyShow.BookMyShow.DTOs.Request;

import com.bookMyShow.BookMyShow.Enums.UserType;
import com.bookMyShow.BookMyShow.Models.Movie;
import lombok.*;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MovieOwnerSignUpDto {
    String name;
    String email;
    long phoneNumber;
    String password;
    UserType userType;
    int companyAge;
    List<Movie> movies;
}
