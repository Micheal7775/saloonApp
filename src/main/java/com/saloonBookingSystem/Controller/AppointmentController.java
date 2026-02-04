package com.saloonBookingSystem.Controller;

import com.saloonBookingSystem.Dto.AppointmentRequestDTO;
import com.saloonBookingSystem.Dto.AppointmentResponseDTO;
import com.saloonBookingSystem.Service.AppointmentService;
import com.saloonBookingSystem.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
@CrossOrigin(origins = "http://localhost:5173")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // 🟢 CUSTOMER → BOOK APPOINTMENT
    @PostMapping("/book")
    public AppointmentResponseDTO bookAppointment(
            @RequestBody AppointmentRequestDTO dto) {

        return appointmentService.bookAppointment(dto);
    }

    // 🟢 STAFF → ACCEPT APPOINTMENT
    @PutMapping("/{id}/accept")
    public Appointment acceptAppointment(@PathVariable Long id) {

        return appointmentService.acceptAppointment(id);
    }

    // 🔴 STAFF → REJECT APPOINTMENT
    @PutMapping("/{id}/reject")
    public Appointment rejectAppointment(@PathVariable Long id) {

        return appointmentService.rejectAppointment(id);
    }
}
