package com.bookMyShow.BookMyShow.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    String movieName;
    String directorName;
    String actorName;
    String actressName;
    int imdbRating;
    double duration;
    @OneToMany(mappedBy = "movie")
    List<Ticket> tickets;
    @JsonIgnore
    @ManyToOne
    ApplicationUser movieOwner;
}
