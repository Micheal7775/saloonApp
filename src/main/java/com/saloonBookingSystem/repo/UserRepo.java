package com.saloonBookingSystem.repo;

import com.saloonBookingSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User>findByUserName(String userName);
    boolean existsByUserName(String userName);


    Optional<User> findByEmail(String email);
}
