package com.saloonBookingSystem.Service;

import com.saloonBookingSystem.Dto.AdminRequestDTO;
import com.saloonBookingSystem.model.Admin;
import com.saloonBookingSystem.model.Role;
import com.saloonBookingSystem.model.User;
import com.saloonBookingSystem.repo.AdminRepo;
import com.saloonBookingSystem.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    AdminRepo adminRepo;

@Autowired
    UserRepo userRepo;
    public void CreateAdmin(AdminRequestDTO adminRequestDTO){

        User user=userRepo.findById(adminRequestDTO.getId()).orElseThrow(()->new RuntimeException("Admin not found"));
        if (user.getRole() != Role.ADMIN) {
            throw new RuntimeException("User is not ADMIN");
        }
        if (adminRepo.existsByUser(user)) {
            throw new RuntimeException("Admin already exists for this user");
        }
        Admin admin=new Admin();

        admin.setUser(user);
        admin.setAdminName(adminRequestDTO.getAdminName());
        admin.setBranchName(adminRequestDTO.getBranchName());
        adminRepo.save(admin);

    }
    public Admin updateAdmin(Long adminId, AdminRequestDTO dto) {

        Admin admin = adminRepo.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        admin.setAdminName(dto.getAdminName());
        admin.setBranchName(dto.getBranchName());

        return adminRepo.save(admin);
    }

}
