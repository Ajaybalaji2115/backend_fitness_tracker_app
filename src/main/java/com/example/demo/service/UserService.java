
// package com.example.demo.service;

// import com.example.demo.model.*;
// import com.example.demo.repository.*;


// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import java.util.*;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Sort;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;


// @Service
// public class UserService {
//     @Autowired
//     private UserRepository userRepository;

//     @Autowired
//     private AuditLogService auditLogService;

//     // public User createUser(User user) {
//     //      if (user.getEmail().endsWith("admin@gmail.com")) {
//     //     user.setRole(Role.ADMIN);
//     // } else {
//     //     user.setRole(Role.GUEST); // default role for all other users
//     // }
//     //     User saved = userRepository.save(user);
//     //     auditLogService.log("Created user", user.getEmail());
//     //     return saved;
//     // }
// public User createUser(User user) {
//    // If no role is provided, default to GUEST
//     if (user.getRole() == null) {
//         user.setRole(Role.GUEST);
//     } else if (user.getRole() != Role.ADMIN && user.getRole() != Role.GUEST) {
//         // Only allow ADMIN or GUEST
//         user.setRole(Role.GUEST);
//     }

//     User saved = userRepository.save(user);
//     auditLogService.log("Created user with role: " + user.getRole(), user.getEmail());
//     return saved;
//     // if (user.getRole() == null) {
//     //         user.setRole(Role.GUEST); // default role
//     //     }
//     //     User saved = userRepository.save(user);
//     //     auditLogService.log("Created user with role: " + user.getRole(), user.getEmail());
//     //     return saved;
// }

//     public User updateUser(Long id, User updated) {
//         User existing = userRepository.findById(id).orElseThrow();
//         existing.setName(updated.getName());
//         existing.setEmail(updated.getEmail());
//         existing.setPassword(updated.getPassword());
//         auditLogService.log("Updated user", existing.getEmail());
//         return userRepository.save(existing);
//     }
//  public void deleteUser(Long id) {
//         // This is a simple, permanent delete.
//         // It's a good idea to add error handling here to check if the user exists.
        
//         userRepository.deleteById(id);
//     }

//     public Optional<User> login(String email, String password) {
//         Optional<User> user = userRepository.findByEmail(email);
//         boolean success = user.isPresent() && user.get().getPassword().equals(password);
//         auditLogService.log("Login attempt", email);

//         if (success) {
//             return user;
//         }
//         return Optional.empty();
//     }

//     public User getByEmail(String email) {
//         return userRepository.findByEmail(email).orElseThrow();
//     }

//     public Page<User> getAllByRole(Role role, Pageable pageable) {
//         return userRepository.findByRole(role, pageable);
//     }
//     public Optional<User> getUserByEmail(String email) {
//         return userRepository.findByEmail(email); // ✅ uses repository method
//     }
//    public Long findUserIdByUsername(String name) {
//         User user = userRepository.findByName(name)
//                      .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + name));
//         return user.getId();
//     }
//     public User getById(Long id) {
//     return userRepository.findById(id).orElseThrow();
// }

// }
package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuditLogService auditLogService;

    @Autowired
    private PasswordEncoder passwordEncoder; // For password hashing

    // Create user with proper role and hashed password
    public User createUser(User user) {
        // Default role if not provided
        if (user.getRole() == null || (user.getRole() != Role.ADMIN && user.getRole() != Role.GUEST)) {
            user.setRole(Role.GUEST);
        }

        // Hash the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
// user.setPassword(user.getPassword());

        User saved = userRepository.save(user);
        auditLogService.log("Created user with role: " + user.getRole(), user.getEmail());
        return saved;
    }

    // Update user details (password will be re-encoded)
    public User updateUser(Long id, User updated) {
        User existing = userRepository.findById(id).orElseThrow(() ->
            new NoSuchElementException("User not found with ID: " + id)
        );
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());

        // Only update password if provided
        if (updated.getPassword() != null && !updated.getPassword().isEmpty()) {
            existing.setPassword(passwordEncoder.encode(updated.getPassword()));
        }

        auditLogService.log("Updated user", existing.getEmail());
        return userRepository.save(existing);
    }

    // Delete user
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NoSuchElementException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
        auditLogService.log("Deleted user with ID: " + id, "");
    }

    // Login method with BCrypt password check
    public Optional<User> login(String email, String rawPassword) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(rawPassword, user.getPassword())) {
                auditLogService.log("Login successful", email);
                return Optional.of(user);
            } else {
                auditLogService.log("Login failed: wrong password", email);
            }
        } else {
            auditLogService.log("Login failed: user not found", email);
        }
        return Optional.empty();
    }

    // Get user by email
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    // Get all users by role with pagination
    public Page<User> getAllByRole(Role role, Pageable pageable) {
        return userRepository.findByRole(role, pageable);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Long findUserIdByUsername(String name) {
        User user = userRepository.findByName(name)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + name));
        return user.getId();
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + id));
    }
}
