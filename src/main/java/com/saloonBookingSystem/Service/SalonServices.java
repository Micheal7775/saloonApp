package com.saloonBookingSystem.Service;

import com.saloonBookingSystem.model.SalonService;
import com.saloonBookingSystem.model.ServiceCategory;
import com.saloonBookingSystem.repo.Salonservicerepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalonServices {


    @Autowired
    Salonservicerepo salonservicerepo;


    public List<SalonService> getAll() {
        return salonservicerepo.findAll();
    }

    public List<SalonService> byCategory(ServiceCategory cat){
        return salonservicerepo.findByCategory(cat);
    }
}