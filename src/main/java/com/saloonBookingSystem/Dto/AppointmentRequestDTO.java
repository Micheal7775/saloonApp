package com.saloonBookingSystem.Dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AppointmentRequestDTO {

    private Long customerId;
    private LocalDate appointmentDate;
    private Long staffId;           // OPTIONAL ✅

    private LocalTime startTime;   // 🔥 important
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public List<Long> getServiceIds() {
        return serviceIds;
    }

    public void setServiceIds(List<Long> serviceIds) {
        this.serviceIds = serviceIds;
    }

    private List<Long> serviceIds; // multiple services

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    // getters & setters
}
