
// package com.example.demo.controller;

// import com.example.demo.model.*;
// import com.example.demo.security.JwtUtil;
// import com.example.demo.service.*;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Sort;

// import org.springframework.http.*;

// import org.springframework.web.bind.annotation.*;


// import java.util.*;


// @RestController
// @RequestMapping("/users")
// public class UserController {

//     @Autowired
//     private UserService userService;
//    @Autowired
//     private JwtUtil jwtUtil;
//     @PostMapping("/register")
//     public ResponseEntity<User> register(@RequestBody User user) {
//         return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
//     }

//   @PostMapping("/login")
//     public ResponseEntity<String> login(@RequestBody Map<String, String> payload) {
//         // 1. Authenticate the user through the service layer
//         Optional<User> userOptional = userService.login(payload.get("email"), payload.get("password"));

//         if (userOptional.isPresent()) {
//             User user = userOptional.get();
//             // 2. Check if the authenticated user has the ADMIN role
          
//                 // 3. Generate a JWT token for the admin
//                 String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
//                 // 4. Return the token in the response body
//                 return ResponseEntity.ok(token);
           
//         } else {
//             // Authentication failed
//             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
//         }
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
//         return ResponseEntity.ok(userService.updateUser(id, user));
//     }

//      @DeleteMapping("/{id}")
//     public ResponseEntity<Void> delete(@PathVariable Long id) {
//         userService.deleteUser(id);
//         return ResponseEntity.noContent().build();
//     }

   
//     @GetMapping("/role/{role}")
//     public ResponseEntity<Page<User>> getByRole(
//             @PathVariable Role role,
//             @RequestParam(defaultValue = "0") int page,         
//             @RequestParam(defaultValue = "10") int size,        
//             @RequestParam(defaultValue = "id") String sortBy,  
//             @RequestParam(defaultValue = "asc") String sortDir 
//     ) {
//         Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
//         Pageable pageable = PageRequest.of(page, size, sort);
//         Page<User> usersPage = userService.getAllByRole(role, pageable);
//         return ResponseEntity.ok(usersPage);
//     }
// }
    // http://localhost:8080/users/role/ADMIN?page=0&size=10&sortField=name&sortDir=asc
// }
package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> payload) {
        Optional<User> userOptional = userService.login(payload.get("email"), payload.get("password"));

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // ✅ Generate token
            String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

            // ✅ Return full response object
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("userId", user.getId());
            response.put("email", user.getEmail());
            response.put("role", user.getRole().name());

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<Page<User>> getByRole(
            @PathVariable Role role,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<User> usersPage = userService.getAllByRole(role, pageable);
        return ResponseEntity.ok(usersPage);
    }
}
//http://localhost:8080/users/role/{role}?page=0&size=10&sortBy=id&sortDir=asc