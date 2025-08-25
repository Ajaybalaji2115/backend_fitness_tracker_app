// package com.example.demo.repository;



// import org.springframework.data.jpa.repository.JpaRepository;
// import com.example.demo.model.WorkoutPlan;

// public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlan, Long> {
// }
// WorkoutPlanRepository.java
package com.example.demo.repository;

import com.example.demo.model.WorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlan, Long> {
}

