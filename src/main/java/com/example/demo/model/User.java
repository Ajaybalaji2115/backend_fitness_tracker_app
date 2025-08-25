// package com.example.demo.model;

// import java.sql.*;
// import java.time.LocalDate;
// import java.time.LocalDateTime;

// import javax.management.relation.Role;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.EnumType;
// import jakarta.persistence.Enumerated;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;
// // import jakarta.validation.constraints.Size;
// import jakarta.validation.constraints.Email;
// import jakarta.validation.constraints.Size;
// import lombok.Data;

// @Entity
// @Data
// @Table(name = "users")
// public class User {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long userId;

//     @Column(unique = true, nullable = false, length = 50)
//     @Size(min = 3, message = "Username must be at least 3 characters")
//     private String username;

//     @Column(unique = true, nullable = false, length = 100)
//     @Email(message = "Email should be valid")
//     private String email;

//     @Column(nullable = false)
//     private String password;

//     @Enumerated(EnumType.STRING)
//     @Column(nullable = false)
//     private Role role;

//     @Column(nullable = false)
//     private LocalDate joinDate;

//     @Column(nullable = false, updatable = false)
//     private Timestamp createdDate = Timestamp.valueOf(LocalDateTime.now());

//     private Timestamp lastLogin;

//     private Boolean isActive = true;

//     private Boolean verified = false;

//     // Getters and setters

// public enum Role {
//     TRAINER, MEMBER
// }
// }
// package com.example.demo.model;
// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.EnumType;
// import jakarta.persistence.Enumerated;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;

// import jakarta.validation.constraints.Email;
// import jakarta.validation.constraints.NotBlank;
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Entity
// @Data
// @Builder
// @NoArgsConstructor
// @AllArgsConstructor
// public class User {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @NotBlank(message = "Name is required")
//     private String name;

//     @NotBlank(message = "Email is required")
//     @Email(message = "Invalid email format")
//     @Column(unique = true)
//     private String email;

//     @NotBlank(message = "Password is required")
//     private String password;

//     @Enumerated(EnumType.STRING)
//     private Role role = Role.GUEST;

//     private boolean active = true;

// }
package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role = Role.GUEST;

    private boolean active = true;
}
