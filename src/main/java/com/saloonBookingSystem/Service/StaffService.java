package com.saloonBookingSystem.Service;

import com.saloonBookingSystem.Dto.StaffRequestDTO;
import com.saloonBookingSystem.model.Role;
import com.saloonBookingSystem.model.Staff;
import com.saloonBookingSystem.model.User;
import com.saloonBookingSystem.repo.StaffRepo;
import com.saloonBookingSystem.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



    @Service
    public class StaffService {

        @Autowired
        private StaffRepo staffRepo;

        @Autowired
        private UserRepo userRepo;

        // ✅ CREATE STAFF
        public Staff createStaff(StaffRequestDTO dto) {

            User user = userRepo.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            if (user.getRole() != Role.STAFF) {
                throw new RuntimeException("User is not STAFF");
            }

            if (staffRepo.existsById(user.getId())) {
                throw new RuntimeException("Staff already exists for this user");
            }

            Staff staff = new Staff();
            staff.setStaffName(dto.getStaffName());
            staff.setSpecialization(dto.getSpecialization());
            staff.setAvailable(dto.isAvailable());

            // 🔥 MOST IMPORTANT LINE
            staff.setUser(user); // MapsId → staff.id = user.id

            return staffRepo.save(staff);
        }

        // ✅ UPDATE STAFF
        public Staff updateStaff(Long staffId, StaffRequestDTO dto) {

            Staff staff = staffRepo.findById(staffId)
                    .orElseThrow(() -> new RuntimeException("Staff not found"));

            staff.setStaffName(dto.getStaffName());
            staff.setSpecialization(dto.getSpecialization());
            staff.setAvailable(dto.isAvailable());

            return staffRepo.save(staff);
        }

        // ✅ DELETE STAFF
        public void deleteStaff(Long staffId) {
            staffRepo.deleteById(staffId);
        }
    }


