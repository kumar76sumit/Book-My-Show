package com.bookMyShow.BookMyShow.DTOs.Request;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddShowDto {
    int hour;
    int minutes;
    int ticketPrice;
    UUID movieId;
    UUID hallId;
}
