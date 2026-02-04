package com.saloonBookingSystem.Controller;

import com.saloonBookingSystem.Dto.StaffRequestDTO;
import com.saloonBookingSystem.Service.StaffService;
import com.saloonBookingSystem.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/staff")
public class StaffController {
    @Autowired
     StaffService staffService;
    @PostMapping
    public Staff createStaff(@RequestBody StaffRequestDTO dto) {
        return staffService.createStaff(dto);
    }

    // ✅ UPDATE STAFF
    @PutMapping("/{id}")
    public Staff updateStaff(@PathVariable Long id,
                             @RequestBody StaffRequestDTO dto) {
        return staffService.updateStaff(id, dto);
    }

    // ✅ DELETE STAFF
    @DeleteMapping("/{id}")
    public String deleteStaff(@PathVariable Long id) {
        staffService.deleteStaff(id);
        return "Staff deleted successfully";
    }
}
