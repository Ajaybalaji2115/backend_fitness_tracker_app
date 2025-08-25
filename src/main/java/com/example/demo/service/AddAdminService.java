package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// @Service
// public class AddAdminService {
//     @Autowired
//     private UserRepository userRepository;

//     private final String allowedEmail = "ajaybalaji123@gmail.com";
//     private final String allowedPassword = "1234567";

//     public String addAdmin(User user, String creatorEmail, String creatorPassword) {
//      public String addAdmin(User user, String creatorEmail, String creatorPassword) {
//         // Trim to avoid extra spaces
//         if (creatorEmail != null && creatorPassword != null &&
//             creatorEmail.trim().equals(allowedEmail) &&
//             creatorPassword.trim().equals(allowedPassword)) {

//             // Prevent someone from sending role manually
//             user.setRole(Role.ADMIN);

//             // Save new admin
//             userRepository.save(user);
//             return "Admin user created successfully!";
//         } else {
//             return "You are not authorized to create an admin!";
//         }
//     }

// // }
// @Service
// public class AddAdminService {

//     @Autowired
//     private UserRepository userRepository;

//     private final String allowedEmail = "ajaybalaji123@gmail.com";
//     private final String allowedPassword = "1234567";

//     public String addUserWithRole(User user, String creatorEmail, String creatorPassword, String role) {
//         if (creatorEmail != null && creatorPassword != null &&
//             creatorEmail.trim().equals(allowedEmail) &&
//             creatorPassword.trim().equals(allowedPassword)) {

//             if (role == null || (!role.equalsIgnoreCase("ADMIN") && !role.equalsIgnoreCase("TRAINER"))) {
//                 return "Invalid role. Only ADMIN or TRAINER allowed.";
//             }

//             user.setRole(Role.valueOf(role.toUpperCase()));
//             userRepository.save(user);
//             return role.toUpperCase() + " user created successfully!";
//         } else {
//             return "You are not authorized to create a user!";
//         }
//     }
// }


@Service
public class AddAdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inject BCrypt encoder

    private final String allowedEmail = "ajaybalaji123@gmail.com";
    private final String allowedPassword = "1234567";

    public String addUserWithRole(User user, String creatorEmail, String creatorPassword, String role) {
        if (creatorEmail != null && creatorPassword != null &&
            creatorEmail.trim().equals(allowedEmail) &&
            creatorPassword.trim().equals(allowedPassword)) {

            if (role == null || (!role.equalsIgnoreCase("ADMIN") && !role.equalsIgnoreCase("TRAINER"))) {
                return "Invalid role. Only ADMIN or TRAINER allowed.";
            }

            user.setRole(Role.valueOf(role.toUpperCase()));

            // ✅ Hash the password before saving
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            userRepository.save(user);
            return role.toUpperCase() + " user created successfully!";
        } else {
            return "You are not authorized to create a user!";
        }
    }
}
