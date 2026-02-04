package com.saloonBookingSystem.Controller;

import com.saloonBookingSystem.Service.SalonServices;
import com.saloonBookingSystem.model.SalonService;
import com.saloonBookingSystem.model.ServiceCategory;
import com.saloonBookingSystem.repo.Salonservicerepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://saloonapp-4rw7.onrender.com"
})
public class SalonServiceController {

    @Autowired
    private SalonServices salonServices;

    // 🔹 Get all services
    @GetMapping
    public List<SalonService> getAll() {
        return salonServices.getAll();
    }

    // 🔹 Filter by category (HAIR / SKIN / SPA)
    @GetMapping("/category/{cat}")
    public List<SalonService> byCategory(@PathVariable ServiceCategory cat) {
        return salonServices.byCategory(cat);
    }
}
