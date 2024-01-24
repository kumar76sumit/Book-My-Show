package com.bookMyShow.BookMyShow.Repositories;

import com.bookMyShow.BookMyShow.Models.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, UUID> {
    @Query(value = "select * from application_user where email=:email",nativeQuery = true)
    public ApplicationUser findByEmail(String email);
}
