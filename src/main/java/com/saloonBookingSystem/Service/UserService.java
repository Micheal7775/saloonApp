package com.saloonBookingSystem.Service;

import com.saloonBookingSystem.ExceptoinHandler.UserNotFoundException;
import com.saloonBookingSystem.model.Role;
import com.saloonBookingSystem.model.User;
import com.saloonBookingSystem.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 🔐 NORMAL LOGIN
    public User login(String userName, String password) throws UserNotFoundException {

        log.info("Login attempt for username={}", userName);

        User user = userRepo.findByUserName(userName)
                .orElseThrow(() -> {
                    log.error("User not found with username={}", userName);
                    return new UserNotFoundException("User not found");
                });

        if (user.getPassword() == null ||
                !passwordEncoder.matches(password, user.getPassword())) {
            log.warn("Invalid password attempt for username={}", userName);
            throw new UserNotFoundException("Invalid password");
        }

        log.info("Login successful for username={}", userName);
        return user;
    }

    // 👤 SIGNUP (NORMAL USER)
    public User createCustomer(User user) {

        log.info("Signup request for username={}", user.getUserName());

        if (userRepo.existsByUserName(user.getUserName())) {
            log.warn("Username already exists: {}", user.getUserName());
            throw new RuntimeException("Username already exists");
        }

        user.setRole(Role.CUSTOMER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepo.save(user);
        log.info("User created successfully with id={}", savedUser.getId());

        return savedUser;
    }

    // 🔵 GOOGLE LOGIN (EMAIL BASED)
    public User loginOrCreateGoogleUser(String email, String name) {

        log.info("Google login attempt for email={}", email);

        return userRepo.findByEmail(email)
                .orElseGet(() -> {
                    log.info("Creating new Google user for email={}", email);

                    User user = new User();
                    user.setEmail(email);
                    user.setUserName(name);
                    user.setRole(Role.CUSTOMER);
                    user.setPassword(null);

                    return userRepo.save(user);
                });
    }
}
