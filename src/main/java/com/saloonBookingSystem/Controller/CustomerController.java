package com.saloonBookingSystem.Controller;

import com.saloonBookingSystem.Dto.CustomerCreateRequestDTO;
import com.saloonBookingSystem.Dto.CustomerUpdateRequestDTO;
import com.saloonBookingSystem.Service.CustomerService;
import com.saloonBookingSystem.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:5173")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // 🟢 CREATE PROFILE (ONE TIME)
    @PostMapping
    public Customer createCustomer(
            @RequestBody CustomerCreateRequestDTO dto) {

        return customerService.createCustomer(dto);
    }

    // 🔍 GET CUSTOMER AFTER LOGIN
    @GetMapping("/user/{userId}")
    public Customer getCustomerByUser(
            @PathVariable Long userId) {

        return customerService.getCustomerByUser(userId);
    }

    // 🔄 UPDATE PROFILE (EDIT)
    @PutMapping("/{userId}")
    public Customer updateCustomer(
            @PathVariable Long userId,
            @RequestBody CustomerUpdateRequestDTO dto) {

        return customerService.updateCustomer(userId, dto);
    }
}
