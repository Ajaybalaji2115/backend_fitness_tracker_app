// AuditLogController.java
package com.example.demo.controller;

import com.example.demo.model.AuditLog;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.AuditLogService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import java.util.List;

@RestController
@RequestMapping("/audit-logs")
public class AuditLogController {

    @Autowired
    private AuditLogService auditLogService;

    @Autowired
    private UserService userService;

    // Admin-only: get all logs
    // @GetMapping
    // public ResponseEntity<?> getAllLogs(@RequestHeader("userId") Long userId) {
    //     User user = userService.getById(userId);
    //     if (user.getRole() != Role.ADMIN) {
    //         return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
    //     }

    //     List<AuditLog> logs = auditLogService.getAllLogs();
    //     return ResponseEntity.ok(logs);
    // }
    
@GetMapping
public ResponseEntity<?> getLogs(
        @RequestHeader("userId") Long userId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "timestamp") String sortBy,
        @RequestParam(defaultValue = "desc") String sortDir,
        @RequestParam(required = false) String actionFilter
) {
    User user = userService.getById(userId);
    if (user.getRole() != Role.ADMIN) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
    }

    Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
    Page<AuditLog> logs = auditLogService.getLogs(actionFilter, PageRequest.of(page, size, sort));

    return ResponseEntity.ok(logs);
}
}
