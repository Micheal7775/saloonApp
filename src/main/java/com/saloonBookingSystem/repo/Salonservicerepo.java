package com.saloonBookingSystem.repo;

import com.saloonBookingSystem.model.SalonService;
import com.saloonBookingSystem.model.ServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Repository
public interface Salonservicerepo extends JpaRepository<SalonService,Long> {
    List<SalonService> findByCategory(ServiceCategory cat);
}
