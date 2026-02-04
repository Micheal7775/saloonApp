package com.saloonBookingSystem.Controller;

import com.saloonBookingSystem.Dto.GoogleLoginRequestDTO;
import com.saloonBookingSystem.Dto.LoginRequestDTO;
import com.saloonBookingSystem.ExceptoinHandler.UserNotFoundException;
import com.saloonBookingSystem.Service.UserService;
import com.saloonBookingSystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private UserService userService;

    // 🔐 NORMAL LOGIN
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestBody LoginRequestDTO req) throws UserNotFoundException {

        User user = userService.login(req.getUserName(), req.getPassword());

        return ResponseEntity.ok(Map.of(
                "message", "login successful",
                "userId", user.getId(),
                "userName", user.getUserName(),
                "role", user.getRole().name()
        ));
    }

    // 👤 SIGNUP
    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody User user) {

        userService.createCustomer(user);
        return ResponseEntity.ok("User registered");
    }

    // 🔵 GOOGLE LOGIN
    @PostMapping("/google")
    public ResponseEntity<Map<String, Object>> googleLogin(
            @RequestBody GoogleLoginRequestDTO req) {

        User user = userService.loginOrCreateGoogleUser(
                req.getEmail(),
                req.getName()
        );

        return ResponseEntity.ok(Map.of(
                "message", "google login successful",
                "userId", user.getId(),
                "userName", user.getUserName(),
                "role", user.getRole().name()
        ));
    }
}
