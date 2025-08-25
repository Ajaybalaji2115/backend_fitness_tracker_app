package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Otp;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long> { // Long matches the @Id type in Otp entity
    
    Optional<Otp> findByEmailAndOtpCodeAndUsedFalse(String email, String otpCode);

    // Finds the latest OTP for an email, ordered by expiry time (descending)
    Optional<Otp> findTopByEmailOrderByExpiryTimeDesc(String email);
}
