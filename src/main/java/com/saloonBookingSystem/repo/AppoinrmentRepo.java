package com.saloonBookingSystem.repo;

import com.saloonBookingSystem.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppoinrmentRepo extends JpaRepository<Appointment,Long> {
}
