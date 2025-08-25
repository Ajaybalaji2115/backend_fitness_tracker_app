// package com.example.demo.model;



// import jakarta.persistence.*;
// import lombok.Data;

// import java.time.LocalDateTime;
// import java.util.List;

// @Entity
// @Data
// public class WorkoutPlan {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(length = 100)
//     private String title;

//     @Column(length = 500)
//     private String description;

//     @Enumerated(EnumType.STRING)
//     private DifficultyLevel difficulty;

//     private String createdBy;
//     private LocalDateTime creationDate;
//     private boolean isActive = true;

//     public enum DifficultyLevel {
//         BEGINNER, INTERMEDIATE, ADVANCED
//     }
//      @ManyToOne
//     @JoinColumn(name = "user_id")  // FK in WorkoutPlan table
//     private User user;
//     @OneToMany(mappedBy = "workoutPlan", cascade = CascadeType.ALL)
// private List<Exercise> exercises;
// @OneToMany(mappedBy = "workoutPlan", cascade = CascadeType.ALL)
// private List<UserPlanProgress> userProgress;
// @OneToMany(mappedBy = "workoutPlan", cascade = CascadeType.ALL)
// private List<WorkoutSession> sessions;
// }
// WorkoutPlan.java
package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "workout_plans")
public class WorkoutPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;

    @Column(length = 100)
    private String title;

    @Column(length = 500)
    private String description;

    private String difficulty; // BEGINNER, INTERMEDIATE, ADVANCED
    private String createdBy;
    private LocalDateTime creationDate;
    private Boolean isActive;

    // @OneToMany(mappedBy = "workoutPlan", cascade = CascadeType.ALL)
    // private List<Exercise> exercises;

    // Getters and Setters
}
