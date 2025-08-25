// package com.example.demo.repository;


// import org.springframework.data.jpa.repository.JpaRepository;
// import com.example.demo.model.WorkoutSession;

// public interface WorkoutSessionRepository extends JpaRepository<WorkoutSession, Long> {
// }

// WorkoutSessionRepository.java (Repository)
package com.example.demo.repository;

import com.example.demo.model.WorkoutSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutSessionRepository extends JpaRepository<WorkoutSession, Long> {
}