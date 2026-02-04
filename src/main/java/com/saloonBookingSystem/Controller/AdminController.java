package com.saloonBookingSystem.Controller;

import com.saloonBookingSystem.Dto.AdminRequestDTO;
import com.saloonBookingSystem.Service.AdminService;
import com.saloonBookingSystem.model.Admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    Logger logger =
            LoggerFactory.getLogger(AdminController.class);



    @Autowired
    private AdminService adminService;
    @PostMapping("/create")
    public void createAdmin(@RequestBody AdminRequestDTO adminRequestDTO){
        logger.info("user created");
        adminService.CreateAdmin(adminRequestDTO);
    }
    @PutMapping("/{id}")
    public Admin updateAdmin(@PathVariable Long id,
                             @RequestBody AdminRequestDTO dto) {

        logger.info("update admin");
        return adminService.updateAdmin(id, dto);
    }

}
