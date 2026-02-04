package com.saloonBookingSystem.model;

import jakarta.persistence.*;

@Entity
public class Staff {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Id
    private Long id; // SAME as user id

    private String staffName;
    private String specialization; // Hair, Facial, etc
    private boolean available;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

}
