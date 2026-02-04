package com.saloonBookingSystem.Controller;

import com.saloonBookingSystem.model.SalonService;
import com.saloonBookingSystem.model.ServiceCategory;
import com.saloonBookingSystem.repo.Salonservicerepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
@CrossOrigin(origins = "http://localhost:5173")
public class SalonServiceController {

    @Autowired
    private Salonservicerepo serviceRepo;

    // 🔹 Get all services
    @GetMapping
    public List<SalonService> getAll() {
        return serviceRepo.findAll();
    }

    // 🔹 Filter by category (HAIR / SKIN / SPA)
    @GetMapping("/category/{cat}")
    public List<SalonService> byCategory(@PathVariable ServiceCategory cat) {
        return serviceRepo.findByCategory(cat);
    }
}
