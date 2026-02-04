package com.saloonBookingSystem.Service;


import com.saloonBookingSystem.Dto.CustomerCreateRequestDTO;
import com.saloonBookingSystem.Dto.CustomerUpdateRequestDTO;
import com.saloonBookingSystem.model.Customer;
import com.saloonBookingSystem.model.Role;
import com.saloonBookingSystem.model.User;
import com.saloonBookingSystem.repo.CustomerRepo;
import com.saloonBookingSystem.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service

public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private UserRepo userRepo;
    private static final Logger log =
            LoggerFactory.getLogger(CustomerService.class);

    // 🟢 CREATE CUSTOMER (ONE TIME)
    public Customer createCustomer(CustomerCreateRequestDTO dto) {

        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getRole() != Role.CUSTOMER) {
            throw new RuntimeException("User is not CUSTOMER");
        }

        if (customerRepo.existsById(user.getId())) {
            throw new RuntimeException("Customer already exists");
        }

        Customer customer = new Customer();
        customer.setUser(user);
        customer.setCustomerName(dto.getCustomerName());
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setCreatedAt(LocalDate.now());
        customer.setActive(true);

        return customerRepo.save(customer);
    }

    // 🔍 FETCH CUSTOMER BY USER ID (LOGIN FLOW)
    public Customer getCustomerByUser(Long userId) {
        return customerRepo.findById(userId)
                .orElseThrow(() ->{log.error("Customer not found for userId: {}", userId);
                return  new RuntimeException("Customer not found");});

    }

    // 🔄 UPDATE CUSTOMER PROFILE (MULTIPLE TIMES)
    public Customer updateCustomer(Long userId, CustomerUpdateRequestDTO dto) {

        Customer customer = customerRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customer.setCustomerName(dto.getCustomerName());
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setGender(dto.getGender());
        customer.setDateOfBirth(dto.getDateOfBirth());

        customer.setAddressLine1(dto.getAddressLine1());
        customer.setAddressLine2(dto.getAddressLine2());
        customer.setCity(dto.getCity());
        customer.setState(dto.getState());
        customer.setPincode(dto.getPincode());
        customer.setCountry(dto.getCountry());

        customer.setHairType(dto.getHairType());
        customer.setSkinType(dto.getSkinType());
        customer.setPreferences(dto.getPreferences());
        customer.setAllergies(dto.getAllergies());

        customer.setUpdatedAt(LocalDate.now());
        log.info("Customer profile created By Successfully");

        return customerRepo.save(customer);
    }
}
