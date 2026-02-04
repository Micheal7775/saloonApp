package com.saloonBookingSystem.Controller;

import com.saloonBookingSystem.Dto.CustomerCreateRequestDTO;
import com.saloonBookingSystem.Dto.CustomerUpdateRequestDTO;
import com.saloonBookingSystem.Service.CustomerService;
import com.saloonBookingSystem.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://saloonapp-4rw7.onrender.com"
})
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // 🟢 CREATE CUSTOMER PROFILE (ONE TIME)
    // POST /customer
    @PostMapping
    public ResponseEntity<Customer> createCustomer(
            @RequestBody CustomerCreateRequestDTO dto) {

        Customer customer = customerService.createCustomer(dto);
        return ResponseEntity.ok(customer);
    }

    // 🔍 GET CUSTOMER BY USER ID
    // GET /customer/user/{userId}
    @GetMapping("/user/{userId}")
    public ResponseEntity<Customer> getCustomerByUser(
            @PathVariable Long userId) {

        Customer customer = customerService.getCustomerByUser(userId);

        if (customer == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(customer);
    }

    // 🔄 UPDATE CUSTOMER PROFILE
    // PUT /customer/{userId}
    @PutMapping("/{userId}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable Long userId,
            @RequestBody CustomerUpdateRequestDTO dto) {

        Customer updated = customerService.updateCustomer(userId, dto);
        return ResponseEntity.ok(updated);
    }

    // 🟢 BACKEND PING (TEST)
    // GET /customer/ping
    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Backend is alive 🚀");
    }
}
