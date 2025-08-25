package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.AddAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/add-admin")
// @CrossOrigin(origins = "http://localhost:3000")
// public class AddAdminController {

//     @Autowired
//     private AddAdminService addAdminService;

//     @PostMapping
//     public String addAdmin(@RequestBody User user,
//                            @RequestParam String creatorEmail,
//                            @RequestParam String creatorPassword) {
//         return addAdminService.addAdmin(user, creatorEmail, creatorPassword);
//     }
// }
@RestController
@RequestMapping("/api/add-user")
@CrossOrigin(origins = "http://localhost:3000")
public class AddAdminController {

    @Autowired
    private AddAdminService addAdminService;

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user,
                                          @RequestParam String creatorEmail,
                                          @RequestParam String creatorPassword,
                                          @RequestParam String role) {
        String message = addAdminService.addUserWithRole(user, creatorEmail, creatorPassword, role);

        if (message.contains("successfully")) {
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(message);
        }
    }
}
