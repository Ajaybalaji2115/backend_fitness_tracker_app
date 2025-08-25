// package com.example.demo.model;

// import jakarta.persistence.*;
// import lombok.Data;

// import java.time.LocalDateTime;

// @Entity
// @Data
// public class WorkoutSession {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private Long userId;           // Optionally use @ManyToOne User
//     private Long workoutPlanId;    // Optionally use @ManyToOne WorkoutPlan

//     private LocalDateTime startTime;
//     private LocalDateTime endTime;

//     private boolean completed;
//     private String notes;
//     @ManyToOne
// @JoinColumn(name = "user_id")
// private User user;
// @ManyToOne
// @JoinColumn(name = "workout_plan_id")
// private WorkoutPlan workoutPlan;
// }
// WorkoutSession.java (Model)
package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "workout_sessions")
public class WorkoutSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;

    private String userId; // Or a reference to the User entity

    @ManyToOne
    @JoinColumn(name = "workout_plan_id")
    private WorkoutPlan workoutPlan;

    private LocalDateTime sessionDate;
    private String duration;

    // A list of completed exercises in this session
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "session_id")
    private List<CompletedExercise> completedExercises;
}
