// package com.example.demo.controller;



// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;

// import com.example.demo.model.Exercise;
// import com.example.demo.service.ExerciseService;
// import com.example.demo.service.AuditLogService;

// @RestController
// @RequestMapping("/api/exercises")
// public class ExerciseController {

//     @Autowired
//     private ExerciseService service;

//     @Autowired
//     private AuditLogService auditLogService;

//     @GetMapping
//     public List<Exercise> getAll() {
//         return service.getAll();
//     }

//     @GetMapping("/{id}")
//     public Exercise getById(@PathVariable Long id) {
//         return service.getById(id);
//     }

//     @PostMapping
//     public Exercise create(@RequestBody Exercise exercise) {
//         auditLogService.log("CREATE_EXERCISE", "system");
//         return service.create(exercise);
//     }

//     @PutMapping("/{id}")
//     public Exercise update(@PathVariable Long id, @RequestBody Exercise exercise) {
//         auditLogService.log("UPDATE_EXERCISE", "system");
//         return service.update(id, exercise);
//     }

//     @DeleteMapping("/{id}")
//     public void delete(@PathVariable Long id) {
//         auditLogService.log("DELETE_EXERCISE", "system");
//         service.delete(id);
//     }
// }

// ExerciseController.java (Controller)
// package com.example.demo.controller;

// import com.example.demo.model.Exercise;
// import com.example.demo.service.ExerciseService;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/exercises")
// public class ExerciseController {
//     private final ExerciseService service;

//     public ExerciseController(ExerciseService service) {
//         this.service = service;
//     }

//     @GetMapping
//     public List<Exercise> getAllExercises() {
//         return service.getAllExercises();
//     }

//     @GetMapping("/{id}")
//     public Exercise getExercise(@PathVariable Long id) {
//         return service.getExercise(id);
//     }

//     @PostMapping
//     public Exercise createExercise(@RequestBody Exercise exercise) {
//         return service.saveExercise(exercise);
//     }

//     @PutMapping("/{id}")
//     public Exercise updateExercise(@PathVariable Long id, @RequestBody Exercise exercise) {
//         exercise.setExerciseId(id);
//         return service.saveExercise(exercise);
//     }

//     @DeleteMapping("/{id}")
//     public void deleteExercise(@PathVariable Long id) {
//         service.deleteExercise(id);
//     }
// }
// package com.example.demo.controller;

// import com.example.demo.model.WorkoutAssignment;
// import com.example.demo.repository.WorkoutAssignmentRepository;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;

// @RestController
// @RequestMapping("/api/workout-assignments")
// @CrossOrigin(origins = "*")
// public class WorkoutAssignmentController {
//     private final WorkoutAssignmentRepository repo;

//     public WorkoutAssignmentController(WorkoutAssignmentRepository repo) {
//         this.repo = repo;
//     }

//     @GetMapping("/user/{userId}")
//     public List<WorkoutAssignment> getAssignmentsForUser(@PathVariable Long userId) {
//         return repo.findByUserId(userId);
//     }

//     @PostMapping
//     public WorkoutAssignment assign(@RequestBody WorkoutAssignment assignment) {
//         return repo.save(assignment);
//     }

//     @PutMapping("/{id}")
//     public WorkoutAssignment update(@PathVariable Long id, @RequestBody WorkoutAssignment assignment) {
//         assignment.setId(id);
//         return repo.save(assignment);
//     }

//     @DeleteMapping("/{id}")
//     public void delete(@PathVariable Long id) {
//         repo.deleteById(id);
//     }
// }
// package com.example.demo.controller;

// import com.example.demo.model.WorkoutAssignment;
// import com.example.demo.repository.WorkoutAssignmentRepository;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;

// @RestController
// @RequestMapping("/api/workout-assignments")
// @CrossOrigin(origins = "*")
// public class WorkoutAssignmentController {
//     private final WorkoutAssignmentRepository repo;

//     public WorkoutAssignmentController(WorkoutAssignmentRepository repo) {
//         this.repo = repo;
//     }

//     @GetMapping("/user/{userId}")
//     public List<WorkoutAssignment> getAssignmentsForUser(@PathVariable Long userId) {
//         return repo.findByUserId(userId);
//     }

//     @PostMapping
//     public WorkoutAssignment assign(@RequestBody WorkoutAssignment assignment) {
//         return repo.save(assignment);
//     }

//     @PutMapping("/{id}")
//     public WorkoutAssignment update(@PathVariable Long id, @RequestBody WorkoutAssignment assignment) {
//         assignment.setId(id);
//         return repo.save(assignment);
//     }

//     @DeleteMapping("/{id}")
//     public void delete(@PathVariable Long id) {
//         repo.deleteById(id);
//     }
// }
// package com.example.demo.controller;

// import com.example.demo.model.WorkoutAssignment;
// import com.example.demo.repository.WorkoutAssignmentRepository;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/workout-assignments")
// @CrossOrigin(origins = "*")
// public class WorkoutAssignmentController {

//     private final WorkoutAssignmentRepository repo;

//     public WorkoutAssignmentController(WorkoutAssignmentRepository repo) {
//         this.repo = repo;
//     }

//     @GetMapping("/user/{userId}")
//     public List<WorkoutAssignment> getAssignmentsForUser(@PathVariable Long userId) {
//         return repo.findByUserId(userId);
//     }

//     @PostMapping
//     public WorkoutAssignment assign(@RequestBody WorkoutAssignment assignment) {
//         if (assignment.getScheduleDay() == null || assignment.getScheduleDay().isEmpty())
//             throw new RuntimeException("Please enter schedule day before assigning");
//         if (assignment.getScheduledDate() == null)
//             throw new RuntimeException("Please enter schedule timestamp before assigning");
//         return repo.save(assignment);
//     }

//     @PutMapping("/{id}")
//     public WorkoutAssignment update(@PathVariable Long id, @RequestBody WorkoutAssignment assignment) {
//         assignment.setId(id);
//         return repo.save(assignment);
//     }

//     @DeleteMapping("/{id}")
//     public void delete(@PathVariable Long id) {
//         repo.deleteById(id);
//     }
// }
// package com.example.demo.controller;

// import com.example.demo.model.WorkoutAssignment;
// import com.example.demo.repository.WorkoutAssignmentRepository;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/workout-assignments")
// @CrossOrigin(origins = "*")
// public class WorkoutAssignmentController {

//     private final WorkoutAssignmentRepository repo;

//     public WorkoutAssignmentController(WorkoutAssignmentRepository repo) {
//         this.repo = repo;
//     }

//     @GetMapping("/user/{userId}")
//     public List<WorkoutAssignment> getAssignmentsForUser(@PathVariable Long userId) {
//         return repo.findByUserId(userId);
//     }

//     @PostMapping
//     public WorkoutAssignment assign(@RequestBody WorkoutAssignment assignment) {
//         if (assignment.getUserId() == null)
//             throw new RuntimeException("User ID is required");
//         if (assignment.getScheduleDay() == null || assignment.getScheduleDay().isEmpty())
//             throw new RuntimeException("Schedule day is required");
//         if (assignment.getScheduledDate() == null)
//             throw new RuntimeException("Scheduled date is required");
//         return repo.save(assignment);
//     }

//     @PutMapping("/{id}")
//     public WorkoutAssignment update(@PathVariable Long id, @RequestBody WorkoutAssignment assignment) {
//         return repo.findById(id).map(existing -> {
//             existing.setExerciseId(assignment.getExerciseId());
//             existing.setSets(assignment.getSets());
//             existing.setReps(assignment.getReps());
//             existing.setDuration(assignment.getDuration());
//             existing.setRestTime(assignment.getRestTime());
//             existing.setScheduleDay(assignment.getScheduleDay());
//             existing.setScheduledDate(assignment.getScheduledDate());
//             existing.setCompleted(assignment.getCompleted());
//             return repo.save(existing);
//         }).orElseThrow(() -> new RuntimeException("WorkoutAssignment not found with id " + id));
//     }

//     @DeleteMapping("/{id}")
//     public void delete(@PathVariable Long id) {
//         repo.deleteById(id);
//     }
// }
// package com.example.demo.controller;

// import com.example.demo.model.WorkoutAssignment;
// import com.example.demo.model.User;
// import com.example.demo.repository.WorkoutAssignmentRepository;
// import com.example.demo.service.UserService;
// import com.example.demo.security.JwtUtil;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/workout-assignments")
// @CrossOrigin(origins = "*")
// public class WorkoutAssignmentController {
// @Autowired
// private JwtUtil jwtUtil;
//     private final WorkoutAssignmentRepository repo;
//     private final UserService userService;
//     // private final JwtUtil jwtUtil;

//     public WorkoutAssignmentController(WorkoutAssignmentRepository repo,
//                                        UserService userService,
//                                        JwtUtil jwtUtil) {
//         this.repo = repo;
//         this.userService = userService;
//         this.jwtUtil = jwtUtil;
//     }

//     // For user view
//     @GetMapping("/user/{userId}")
//     public List<WorkoutAssignment> getAssignmentsForUser(@PathVariable Long userId) {
//         return repo.findByUserId(userId);
//     }

//     // For trainer view
//     @GetMapping("/assigned-by/{trainerId}")
//     public List<WorkoutAssignment> getAssignmentsForTrainer(@PathVariable Long trainerId) {
//         return repo.findByAssignedBy(trainerId);
//     }

//     @PostMapping
//     public WorkoutAssignment assign(@RequestBody WorkoutAssignment assignment,
//                                     @RequestHeader("Authorization") String authHeader) {

//         if (assignment.getUserId() == null)
//             throw new RuntimeException("User ID is required");
//         if (assignment.getScheduleDay() == null || assignment.getScheduleDay().isEmpty())
//             throw new RuntimeException("Schedule day is required");
//         if (assignment.getScheduledDate() == null)
//             throw new RuntimeException("Scheduled date is required");

//         // Extract trainer/admin ID from JWT
//         String token = authHeader.substring(7);
//         String email = jwtUtil.getEmailFromToken(token);
//         User trainer = userService.getUserByEmail(email)
//                                   .orElseThrow(() -> new RuntimeException("Trainer not found"));

//         assignment.setAssignedBy(trainer.getId());

//         return repo.save(assignment);
//     }

//     @PutMapping("/{id}")
//     public WorkoutAssignment update(@PathVariable Long id, @RequestBody WorkoutAssignment assignment) {
//         return repo.findById(id).map(existing -> {
//             existing.setExerciseId(assignment.getExerciseId());
//             existing.setSets(assignment.getSets());
//             existing.setReps(assignment.getReps());
//             existing.setDuration(assignment.getDuration());
//             existing.setRestTime(assignment.getRestTime());
//             existing.setScheduleDay(assignment.getScheduleDay());
//             existing.setScheduledDate(assignment.getScheduledDate());
//             existing.setCompleted(assignment.getCompleted());
//             return repo.save(existing);
//         }).orElseThrow(() -> new RuntimeException("WorkoutAssignment not found with id " + id));
//     }

//     @DeleteMapping("/{id}")
//     public void delete(@PathVariable Long id) {
//         repo.deleteById(id);
//     }
// }
package com.example.demo.controller;

import com.example.demo.model.WorkoutAssignment;
import com.example.demo.repository.WorkoutAssignmentRepository;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workout-assignments")
@CrossOrigin(origins = "*")
public class WorkoutAssignmentController {

    private final WorkoutAssignmentRepository repo;

    public WorkoutAssignmentController(WorkoutAssignmentRepository repo) {
        this.repo = repo;
    }

    // Get assignments for a specific user
    @GetMapping("/user/{userId}")
    public List<WorkoutAssignment> getAssignmentsForUser(@PathVariable Long userId) {
        return repo.findByUserId(userId);
    }

    // Get assignments for current logged-in user or trainer
    @GetMapping("/my-assignments")
    public List<WorkoutAssignment> getAssignmentsForCurrentUser(
            @RequestParam Long userId,
            @RequestParam String role) {

        if ("USER".equalsIgnoreCase(role)) {
            return repo.findByUserId(userId); // Show only user's assignments
        } else if ("TRAINER".equalsIgnoreCase(role) || "ADMIN".equalsIgnoreCase(role)) {
            return repo.findByTrainerId(userId); // Show assignments assigned by trainer
        } else {
            return List.of();
        }
    }

    @PostMapping
    //  @PreAuthorize("hasAnyRole('TRAINER','ADMIN')")
    public WorkoutAssignment assign(@RequestBody WorkoutAssignment assignment) {
        if (assignment.getUserId() == null)
            throw new RuntimeException("User ID is required");
        if (assignment.getScheduleDay() == null || assignment.getScheduleDay().isEmpty())
            throw new RuntimeException("Schedule day is required");
        if (assignment.getScheduledDate() == null)
            throw new RuntimeException("Scheduled date is required");
        return repo.save(assignment);
    }

    @PutMapping("/{id}")
    public WorkoutAssignment update(@PathVariable Long id, @RequestBody WorkoutAssignment assignment) {
        return repo.findById(id).map(existing -> {
            existing.setExerciseId(assignment.getExerciseId());
            existing.setSets(assignment.getSets());
            existing.setReps(assignment.getReps());
            existing.setDuration(assignment.getDuration());
            existing.setRestTime(assignment.getRestTime());
            existing.setScheduleDay(assignment.getScheduleDay());
            existing.setScheduledDate(assignment.getScheduledDate());
            existing.setCompleted(assignment.getCompleted());
            return repo.save(existing);
        }).orElseThrow(() -> new RuntimeException("WorkoutAssignment not found with id " + id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
