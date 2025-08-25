
package com.example.demo.controller;

import com.example.demo.model.ProgressHistory;
import com.example.demo.service.ProgressHistoryService;
import com.example.demo.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/progress")
@CrossOrigin(origins = "http://localhost:3000") // adjust frontend origin if needed
public class ProgressHistoryController {

    private final ProgressHistoryService progressService;
    private final UserService userService; // Service to get user info by username

    public ProgressHistoryController(ProgressHistoryService progressService, UserService userService) {
        this.progressService = progressService;
        this.userService = userService;
    }

    /**
     * Get progress for logged-in user.
     * Example: GET /api/progress/me?days=30
     */
    // @GetMapping("/me")
    // public List<ProgressHistory> getMyProgress(
    //         @RequestParam(value = "days", required = false) Integer days,
    //         Principal principal) {
    //     // Find userId from username
    //     Long userId = userService.findUserIdByUsername(principal.getName());
    //     return progressService.getProgressByUserIdAndDays(userId, days);
    // }
//    @GetMapping("/{historyId}")
//     public ResponseEntity<ProgressHistory> getProgressById(@PathVariable Long historyId) {
//         Optional<ProgressHistory> progress = progressService.getProgressById(historyId);
//         return progress.map(ResponseEntity::ok)
//                        .orElseGet(() -> ResponseEntity.notFound().build());
//     }
// @GetMapping("/user/{userId}")
// public List<ProgressHistory> getUserProgress(@PathVariable Long userId,
//                                              @RequestParam(value = "days", required = false) Integer days) {
//     return progressService.getProgressByUserIdAndDays(userId, days);
// }

    /**
     * Add new progress record for logged-in user
     * Does not require userId in payload, assigns based on logged-in user
     */
    @GetMapping("/user/{userId}")
public List<ProgressHistory> getUserProgress(
        @PathVariable Long userId,
        @RequestParam(value = "days", required = false) Integer days) {
    return progressService.getProgressByUserIdAndDays(userId, days);
}

  @PostMapping("/me")
public ProgressHistory addMyProgress(@RequestBody ProgressHistory progress) {
    // Use userId provided inside progress object directly
    Long userId = progress.getUserId();
    // Optionally, validate userId or enforce any logic here

    // No need to override userId since we rely on client
    return progressService.addProgress(progress);
}

}
