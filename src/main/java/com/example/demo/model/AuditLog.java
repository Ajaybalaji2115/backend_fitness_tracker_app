// package com.example.demo.model;

// import java.time.LocalDateTime;

// import jakarta.persistence.*;
// import lombok.Data;


// @Entity
// @Data
// public class AuditLog {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String action;
//     private String performedBy;
//     private LocalDateTime timestamp = LocalDateTime.now();

//     // Getters and Setters
//     @ManyToOne
// @JoinColumn(name = "user_id")
// private User user;

// }
// AuditLog.java
package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action;          // e.g., "Created workout plan"
    private String performedBy;     // email or name of user
    private LocalDateTime timestamp;
}
