package com.bookMyShow.BookMyShow.DTOs.Request;

import com.bookMyShow.BookMyShow.Enums.UserType;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RegularUserSignUpDto {
    String name;
    String email;
    long phoneNumber;
    String password;
    UserType userType;
    int age;
}
