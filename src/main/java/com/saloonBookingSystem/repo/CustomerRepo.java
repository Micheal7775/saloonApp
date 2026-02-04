package com.saloonBookingSystem.repo;

import com.saloonBookingSystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    boolean existsByUserId(Long userId);

    Optional<Customer> findByUserId(Long userId);
}
