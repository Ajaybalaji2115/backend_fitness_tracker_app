// package com.example.demo.repository;

// import org.springframework.data.jpa.repository.JpaRepository;
// import com.example.demo.model.FitnessGoal;

// public interface FitnessGoalRepository extends JpaRepository<FitnessGoal, Long> {
// }
// FitnessGoalsRepository.java (Repository)
// package com.example.demo.repository;

// import com.example.demo.model.FitnessGoals;
// import org.springframework.data.jpa.repository.JpaRepository;

// import java.util.List;

// public interface FitnessGoalsRepository extends JpaRepository<FitnessGoals, Long> {
//     List<FitnessGoals> findByUserId(Long userId);
//     List<FitnessGoals> findByStatus(String status);
// }

package com.example.demo.repository;

import com.example.demo.model.FitnessGoals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FitnessGoalsRepository extends JpaRepository<FitnessGoals, Long> {
    List<FitnessGoals> findByUserId(Long userId);
    List<FitnessGoals> findByStatus(String status);
}
