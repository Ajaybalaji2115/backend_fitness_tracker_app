// package com.example.demo.model;



// import jakarta.persistence.*;
// import lombok.Data;

// import java.time.LocalDateTime;

// @Entity
// @Data
// public class Notification {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private Long userId;  // Optionally use @ManyToOne User

//     private String message;
//     private boolean read = false;
//     private LocalDateTime timestamp = LocalDateTime.now();
// }
package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    private long userId;
    private String message;
    private String type; // REMINDER, ACHIEVEMENT, ALERT
    private Boolean isRead = false;
    private LocalDateTime createdDate;
    private String priority; // LOW, MEDIUM, HIGH
}
