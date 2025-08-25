// package com.example.demo.model;

// import jakarta.persistence.*;
// import lombok.Data;

// import java.time.LocalDate;
// import java.util.List;

// @Entity
// @Data
// public class FitnessGoal {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private Long userId; // Optionally change to @ManyToOne User

//     private String goalType;   // e.g., Weight Loss, Strength, Endurance
//     private String target;     // e.g., "Lose 5kg", "Run 10km"
//     private LocalDate targetDate;
//     private boolean achieved = false;
//     @ManyToMany(mappedBy = "fitnessGoals")
//     private List<User> users;
// }
// FitnessGoals.java (Model)
// package com.example.demo.model;

// import jakarta.persistence.*;
// import lombok.Data;

// import java.time.LocalDate;

// import com.fasterxml.jackson.annotation.JsonFormat;

// @Entity
// @Data
// @Table(name = "fitness_goals")
// public class FitnessGoals {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long goalId;

//     private long userId; // Or a reference to the User entity

//     private String goalType; // e.g., "Weight Loss", "Muscle Gain"
//     private Double targetValue;
//     @JsonFormat(pattern = "yyyy-MM-dd")
//     private LocalDate targetDate;
//     private Double currentProgress;
//     private String status; // PENDING, IN_PROGRESS, COMPLETED
// @JsonFormat(pattern = "yyyy-MM-dd")
//     private LocalDate achievedDate; // NEW: When goal is achieved
// }
package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Data
@Table(name = "fitness_goals")
public class FitnessGoals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long goalId;

    private Long userId;

    private String goalType; 
    private Double targetValue;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate targetDate;

    private Double currentProgress;
    private String status; // PENDING, IN_PROGRESS, COMPLETED

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate achievedDate; // When goal is achieved
}
