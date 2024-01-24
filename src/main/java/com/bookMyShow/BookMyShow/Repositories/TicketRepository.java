package com.bookMyShow.BookMyShow.Repositories;

import com.bookMyShow.BookMyShow.Models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {
}
