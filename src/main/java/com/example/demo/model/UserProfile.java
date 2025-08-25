// // package com.example.demo.model;

// // import jakarta.persistence.Entity;
// // import jakarta.persistence.GeneratedValue;
// // import jakarta.persistence.GenerationType;
// // import jakarta.persistence.Id;
// // import jakarta.persistence.JoinColumn;
// // import jakarta.persistence.OneToOne;
// // import jakarta.validation.constraints.*;
// // import lombok.Data;



// // @Entity
// // @Data
// // public class UserProfile {
// //     @Id
// //     @GeneratedValue(strategy = GenerationType.IDENTITY)
// //     private Long id;

// //     @NotBlank(message = "Goal is required")
// //     private String goal;

// //     @Min(value = 0, message = "Progress must be non-negative")
// //     private int progress;

// //     private String notes;

// //     @OneToOne
// //     @JoinColumn(name="user_id")
// //     private User user;

// //     // Getters and Setters
// // }
// package com.example.demo.model;

// import jakarta.persistence.*;
// import jakarta.validation.constraints.*;
// import lombok.Data;

// import java.sql.Timestamp;

// @Entity
// @Data
// @Table(name = "user_profiles")
// public class UserProfile {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long profileId;

//     @OneToOne
//     @JoinColumn(name = "userId", nullable = false)
//     private User user;

//     @Min(13)
//     @Max(120)
//     private int age;

//     private double height;   // ✅ double instead of BigDecimal
//     private double weight;

//     @Enumerated(EnumType.STRING)
//     private FitnessLevel fitnessLevel;

//     private String fitnessGoals;
//     private String healthConditions;

//     @Column(length = 100)
//     private String emergencyContact;

//     @Column(length = 15)
//     private String phoneNumber;

//     @Column(updatable = false, insertable = false,
//             columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
//     private Timestamp createdDate;
//  private String password;
//     public enum FitnessLevel {
//         BEGINNER, INTERMEDIATE, ADVANCED
//     }
// }
// package com.example.demo.model;

// import jakarta.persistence.*;
// import lombok.*;

// @Entity
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// public class UserProfile {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long profileId;

//     private Long userId;
//     private int age;
//     private String fitnessLevel;
//     private String goals;
//     private String healthConditions;
//     private String preferences;
//     private String progress; //
// }
// UserProfile.java (Model)
package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user_profiles")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    private Long userId; // Or a reference to the User entity
 private String name;
    private String email;
    private Integer age;
    private String fitnessLevel;
    private String goals;
    private String healthConditions;
    private String preferences;
     private Double height;
    private Double weight;
    private String emergencyContact;
    private String phoneNumber;
    private String fitnessGoals;
    private String password;
}