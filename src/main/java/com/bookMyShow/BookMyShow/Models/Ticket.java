package com.bookMyShow.BookMyShow.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    @JsonIgnore
    @ManyToOne
    ApplicationUser user;
    @JsonIgnore
    @ManyToOne
    Movie movie;
    @JsonIgnore
    @ManyToOne
    Hall hall;
    @JsonIgnore
    @ManyToOne
    Show show;
}
