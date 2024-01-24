package com.bookMyShow.BookMyShow.Repositories;

import com.bookMyShow.BookMyShow.Models.Show;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ShowRepository extends JpaRepository<Show, UUID> {
    @Transactional
    @Modifying
    @Query(value = "update show set available_tickets=:availableTickets where id=:id",nativeQuery = true)
    public void updateAvailableTickets(UUID id,int availableTickets);
}
