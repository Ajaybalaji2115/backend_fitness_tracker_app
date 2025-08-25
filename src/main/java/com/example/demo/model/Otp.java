package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity // This tells JPA it's a table in MySQL
@Table(name = "otps") // Optional: specify table name
public class Otp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID in MySQL
    private Long id;

    @Column(nullable = false) // email is unique
    private String email;

    @Column(nullable = false)
    private String otpCode;

    @Column(nullable = false)
    private LocalDateTime expiryTime;

    @Column(nullable = false)
    private boolean used; // Ensure OTPs are used only once
}
