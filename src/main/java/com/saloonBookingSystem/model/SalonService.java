package com.saloonBookingSystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "salon_services")
public class SalonService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 💇 Service name (Hair Cut, Facial)
    @Column(nullable = false, unique = true)
    private String name;

    // ⏱ Duration in minutes (30, 60)
    @Column(nullable = false)
    private int durationMinutes;

    // 💰 Price
    @Column(nullable = false)
    private double price;

    // 📂 Category
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ServiceCategory category;

    /* ===== Getters & Setters ===== */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ServiceCategory getCategory() {
        return category;
    }

    public void setCategory(ServiceCategory category) {
        this.category = category;
    }
}
