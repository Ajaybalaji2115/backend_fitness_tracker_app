// package com.example.demo.repository;

// import com.example.demo.model.WorkoutAssignment;
// import org.springframework.data.jpa.repository.JpaRepository;
// import java.util.List;

// public interface WorkoutAssignmentRepository extends JpaRepository<WorkoutAssignment, Long> {
//     List<WorkoutAssignment> findByUserId(Long userId);
// }
// package com.example.demo.repository;

// import com.example.demo.model.WorkoutAssignment;
// import org.springframework.data.jpa.repository.JpaRepository;

// import java.time.LocalDateTime;
// import java.util.List;

// public interface WorkoutAssignmentRepository extends JpaRepository<WorkoutAssignment, Long> {
//     List<WorkoutAssignment> findByUserId(Long userId);

//     List<WorkoutAssignment> findByScheduledDateBetween(LocalDateTime start, LocalDateTime end);

//     List<WorkoutAssignment> findByCompletedFalseAndScheduledDateBefore(LocalDateTime now);
// }

// package com.example.demo.repository;

// import com.example.demo.model.WorkoutAssignment;
// import org.springframework.data.jpa.repository.JpaRepository;

// import java.time.LocalDateTime;
// import java.util.List;

// public interface WorkoutAssignmentRepository extends JpaRepository<WorkoutAssignment, Long> {
//     List<WorkoutAssignment> findByUserId(Long userId);
//     List<WorkoutAssignment> findByScheduledDateBetween(LocalDateTime start, LocalDateTime end);
//     List<WorkoutAssignment> findByCompletedFalseAndScheduledDateBefore(LocalDateTime now);
// }
// package com.example.demo.repository;

// import com.example.demo.model.WorkoutAssignment;
// import org.springframework.data.jpa.repository.JpaRepository;

// import java.time.LocalDateTime;
// import java.util.List;

// public interface WorkoutAssignmentRepository extends JpaRepository<WorkoutAssignment, Long> {

//     List<WorkoutAssignment> findByUserId(Long userId);

//     List<WorkoutAssignment> findByScheduledDateBetween(LocalDateTime start, LocalDateTime end);

//     List<WorkoutAssignment> findByCompletedFalseAndScheduledDateBefore(LocalDateTime now);
// }
// package com.example.demo.repository;

// import com.example.demo.model.WorkoutAssignment;
// import org.springframework.data.jpa.repository.JpaRepository;

// import java.time.LocalDateTime;
// import java.util.List;

// public interface WorkoutAssignmentRepository extends JpaRepository<WorkoutAssignment, Long> {
//     List<WorkoutAssignment> findByUserId(Long userId);
//     List<WorkoutAssignment> findByScheduledDateBetween(LocalDateTime start, LocalDateTime end);
//     List<WorkoutAssignment> findByCompletedFalseAndScheduledDateBefore(LocalDateTime now);
// }
package com.example.demo.repository;

import com.example.demo.model.WorkoutAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface WorkoutAssignmentRepository extends JpaRepository<WorkoutAssignment, Long> {

    List<WorkoutAssignment> findByUserId(Long userId);
    List<WorkoutAssignment> findByAssignedBy(Long assignedBy);
    List<WorkoutAssignment> findByScheduledDateBetween(LocalDateTime start, LocalDateTime end);
    List<WorkoutAssignment> findByCompletedFalseAndScheduledDateBefore(LocalDateTime now);
   
List<WorkoutAssignment> findByTrainerId(Long trainerId);

}
