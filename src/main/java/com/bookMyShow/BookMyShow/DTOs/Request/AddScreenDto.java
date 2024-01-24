package com.bookMyShow.BookMyShow.DTOs.Request;

import com.bookMyShow.BookMyShow.Models.Screen;
import lombok.*;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddScreenDto {
    UUID hallId;
    List<Screen> screens;
}
