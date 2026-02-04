package com.saloonBookingSystem.Service;

import com.saloonBookingSystem.Dto.AppointmentRequestDTO;
import com.saloonBookingSystem.Dto.AppointmentResponseDTO;
import com.saloonBookingSystem.model.Appointment;
import com.saloonBookingSystem.model.AppointmentStatus;
import com.saloonBookingSystem.model.Customer;
import com.saloonBookingSystem.model.Staff;
import com.saloonBookingSystem.repo.AppoinrmentRepo;
import com.saloonBookingSystem.repo.CustomerRepo;
import com.saloonBookingSystem.repo.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Autowired
    private AppoinrmentRepo appointmentRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private StaffRepo staffRepo;

    // 🟢 CUSTOMER BOOK APPOINTMENT
    public AppointmentResponseDTO bookAppointment(AppointmentRequestDTO dto) {
        // ✅ Correct customer fetch
        Customer customer = customerRepo.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Appointment appointment = new Appointment();
        appointment.setCustomer(customer);
        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setStartTime(dto.getStartTime());
        appointment.setStatus(AppointmentStatus.PENDING);


// ✅ staff optional
        if (dto.getStaffId() != null) {
            Staff staff = staffRepo.findById(dto.getStaffId())
                    .orElseThrow(() -> new RuntimeException("Staff not found"));
            appointment.setStaff(staff);
        }

        Appointment saved = appointmentRepo.save(appointment);

        AppointmentResponseDTO res = new AppointmentResponseDTO();
        res.setAppointmentId(saved.getId());
        res.setCustomerName(customer.getCustomerName());
        res.setAppointmentDate(saved.getAppointmentDate());
        res.setStartTime(saved.getStartTime());
        res.setEndTime(saved.getEndTime());
        res.setTotalDuration(saved.getTotalDuration());
        res.setTotalPrice(saved.getTotalPrice());
        res.setStatus(saved.getStatus().name());

        return res;

    }

    // 🟢 STAFF ACCEPT APPOINTMENT
    public Appointment acceptAppointment(Long appointmentId) {

        Appointment appointment = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setStatus(AppointmentStatus.CONFIRMED);
        return appointmentRepo.save(appointment);
    }

    // 🔴 STAFF REJECT APPOINTMENT
    public Appointment rejectAppointment(Long appointmentId) {

        Appointment appointment = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setStatus(AppointmentStatus.REJECTED);
        return appointmentRepo.save(appointment);
    }
}
