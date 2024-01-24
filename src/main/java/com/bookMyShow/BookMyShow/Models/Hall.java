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
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    String hallName;
    String address;
    @JsonIgnore
    @ManyToOne
    ApplicationUser hallOwner;
    @OneToMany(mappedBy = "hall")
    List<Screen> screens;
    @OneToMany(mappedBy = "hall")
    List<Show> shows;
    @OneToMany(mappedBy = "hall")
    List<Ticket> tickets;
}
