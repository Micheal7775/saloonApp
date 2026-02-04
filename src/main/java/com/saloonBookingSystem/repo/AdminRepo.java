package com.saloonBookingSystem.repo;

import com.saloonBookingSystem.model.Admin;
import com.saloonBookingSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin,Long> {
    boolean existsByUser(User user);

}
