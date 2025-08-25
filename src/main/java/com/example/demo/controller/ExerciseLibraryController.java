// package com.example.demo.controller;

// import com.example.demo.model.ExerciseLibrary;
// import com.example.demo.model.WorkoutAssignment;
// import com.example.demo.service.ExerciseLibraryService;
// import com.example.demo.service.WorkoutAssignmentService;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.multipart.MultipartFile;

// import java.io.IOException;
// import java.util.List;

// @RestController
// @RequestMapping("/exercise-library")
// @CrossOrigin(origins = "*")
// public class ExerciseLibraryController {

//     private final ExerciseLibraryService service;
//     private final WorkoutAssignmentService assignmentService;
//     private final ObjectMapper objectMapper = new ObjectMapper();

//     public ExerciseLibraryController(ExerciseLibraryService service,
//                                      WorkoutAssignmentService assignmentService) {
//         this.service = service;
//         this.assignmentService = assignmentService;
//     }

//     // ------------------- CRUD for Exercise Library -------------------

//     @GetMapping
//     public List<ExerciseLibrary> getAllExercises() {
//         return service.getAllExercises();
//     }

//     @GetMapping("/{id}")
//     public ExerciseLibrary getExercise(@PathVariable Long id) {
//         return service.getExercise(id);
//     }

//     // ✅ Option A: Multipart (file + JSON string)
//     @PostMapping(consumes = {"multipart/form-data"})
//     public ExerciseLibrary createExerciseMultipart(
//             @RequestPart("exercise") String exerciseJson,
//             @RequestPart(value = "file", required = false) MultipartFile file
//     ) throws IOException {
//         ExerciseLibrary exercise = objectMapper.readValue(exerciseJson, ExerciseLibrary.class);
//         return service.saveExercise(exercise, file);
//     }

//     @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
//     public ExerciseLibrary updateExerciseMultipart(
//             @PathVariable Long id,
//             @RequestPart("exercise") String exerciseJson,
//             @RequestPart(value = "file", required = false) MultipartFile file
//     ) throws IOException {
//         ExerciseLibrary exercise = objectMapper.readValue(exerciseJson, ExerciseLibrary.class);
//         exercise.setId(id);
//         return service.saveExercise(exercise, file);
//     }

//     // ✅ Option B: JSON only (no file upload, just URLs)
//     @PostMapping(consumes = {"application/json"})
//     public ExerciseLibrary createExerciseJson(@RequestBody ExerciseLibrary exercise) {
//         return service.saveExercise(exercise, null);
//     }

//     @PutMapping(value = "/{id}", consumes = {"application/json"})
//     public ExerciseLibrary updateExerciseJson(
//             @PathVariable Long id,
//             @RequestBody ExerciseLibrary exercise
//     ) {
//         exercise.setId(id);
//         return service.saveExercise(exercise, null);
//     }

//     @DeleteMapping("/{id}")
//     public void deleteExercise(@PathVariable Long id) {
//         service.deleteExercise(id);
//     }

//     // ------------------- Assignments -------------------

//     @GetMapping("/assignments/user/{userId}")
//     public List<WorkoutAssignment> getAssignmentsForUser(@PathVariable Long userId) {
//         return assignmentService.getAssignmentsForUser(userId);
//     }

//     @PostMapping("/assignments")
//     public WorkoutAssignment assignExercise(@RequestBody WorkoutAssignment assignment) {
//         return assignmentService.saveAssignment(assignment);
//     }

//     @PutMapping("/assignments/{id}")
//     public WorkoutAssignment updateAssignment(@PathVariable Long id,
//                                               @RequestBody WorkoutAssignment assignment) {
//         assignment.setId(id);
//         return assignmentService.saveAssignment(assignment);
//     }

//     @DeleteMapping("/assignments/{id}")
//     public void deleteAssignment(@PathVariable Long id) {
//         assignmentService.deleteAssignment(id);
//     }
// }
// package com.example.demo.controller;

// import com.example.demo.model.ExerciseLibrary;
// import com.example.demo.model.Role;
// import com.example.demo.model.User;
// import com.example.demo.service.ExerciseLibraryService;
// import com.example.demo.service.UserService;
// import com.example.demo.security.JwtUtil;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/exercise-library")
// @CrossOrigin(origins = "*")
// public class ExerciseLibraryController {

//     private final ExerciseLibraryService service;
//     private final JwtUtil jwtUtil;
//     private final UserService userService;

//     public ExerciseLibraryController(ExerciseLibraryService service, JwtUtil jwtUtil, UserService userService) {
//         this.service = service;
//         this.jwtUtil = jwtUtil;
//         this.userService = userService;
//     }

//     // -------------------- GET All Exercises (any role) --------------------
//     @GetMapping
//     public ResponseEntity<List<ExerciseLibrary>> getAllExercises() {
//         List<ExerciseLibrary> exercises = service.getAllExercises();
//         return ResponseEntity.ok(exercises);
//     }

//     // -------------------- GET Single Exercise --------------------
//     @GetMapping("/{id}")
//     public ResponseEntity<ExerciseLibrary> getExercise(@PathVariable Long id) {
//         ExerciseLibrary ex = service.getExercise(id);
//         if (ex == null) return ResponseEntity.notFound().build();
//         return ResponseEntity.ok(ex);
//     }

//     // -------------------- POST Exercise (TRAINER only) --------------------
//     @PostMapping(consumes = {"application/json"})
//     public ResponseEntity<?> createExercise(@RequestHeader("Authorization") String authHeader,
//                                             @RequestBody ExerciseLibrary exercise) {
//         if (!isTrainer(authHeader)) {
//             return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only TRAINER can create exercises");
//         }
//         ExerciseLibrary saved = service.saveExercise(exercise, null);
//         return ResponseEntity.status(HttpStatus.CREATED).body(saved);
//     }

//     // -------------------- PUT Exercise (TRAINER only) --------------------
//     @PutMapping("/{id}")
//     public ResponseEntity<?> updateExercise(@RequestHeader("Authorization") String authHeader,
//                                             @PathVariable Long id,
//                                             @RequestBody ExerciseLibrary exercise) {
//         if (!isTrainer(authHeader)) {
//             return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only TRAINER can update exercises");
//         }
//         exercise.setId(id);
//         ExerciseLibrary updated = service.saveExercise(exercise, null);
//         return ResponseEntity.ok(updated);
//     }

//     // -------------------- DELETE Exercise (TRAINER only) --------------------
//     @DeleteMapping("/{id}")
//     public ResponseEntity<?> deleteExercise(@RequestHeader("Authorization") String authHeader,
//                                             @PathVariable Long id) {
//         if (!isTrainer(authHeader)) {
//             return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only TRAINER can delete exercises");
//         }
//         service.deleteExercise(id);
//         return ResponseEntity.noContent().build();
//     }

//     // -------------------- Helper Method to Check Role --------------------
//     private boolean isTrainer(String authHeader) {
//         if (authHeader == null || !authHeader.startsWith("Bearer ")) return false;
//         String token = authHeader.substring(7);
//         String email = jwtUtil.extractUsername(token);
//         User user = userService.getUserByEmail(email).orElse(null);
//         return user != null && user.getRole() == Role.TRAINER;
//     }
// }
// package com.example.demo.controller;

// import com.example.demo.model.ExerciseLibrary;
// import com.example.demo.model.Role;
// import com.example.demo.service.ExerciseLibraryService;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/exercise-library")
// @CrossOrigin(origins = "*")
// public class ExerciseLibraryController {

//     private final ExerciseLibraryService service;

//     public ExerciseLibraryController(ExerciseLibraryService service) {
//         this.service = service;
//     }

//     // GET All Exercises (any role)
//     @GetMapping
//     public ResponseEntity<List<ExerciseLibrary>> getAllExercises() {
//         List<ExerciseLibrary> exercises = service.getAllExercises();
//         return ResponseEntity.ok(exercises);
//     }

//     // GET Single Exercise
//     @GetMapping("/{id}")
//     public ResponseEntity<ExerciseLibrary> getExercise(@PathVariable Long id) {
//         ExerciseLibrary ex = service.getExercise(id);
//         if (ex == null) return ResponseEntity.notFound().build();
//         return ResponseEntity.ok(ex);
//     }

//     // POST Exercise (TRAINER only)
//     @PostMapping
//     public ResponseEntity<?> createExercise(@RequestBody ExerciseLibrary exercise) {
//         if (!isTrainer()) {
//             return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only TRAINER can create exercises");
//         }
//         ExerciseLibrary saved = service.saveExercise(exercise, null);
//         return ResponseEntity.status(HttpStatus.CREATED).body(saved);
//     }

//     // PUT Exercise (TRAINER only)
//     @PutMapping("/{id}")
//     public ResponseEntity<?> updateExercise(@PathVariable Long id, @RequestBody ExerciseLibrary exercise) {
//         if (!isTrainer()) {
//             return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only TRAINER can update exercises");
//         }
//         exercise.setId(id);
//         ExerciseLibrary updated = service.saveExercise(exercise, null);
//         return ResponseEntity.ok(updated);
//     }

//     // DELETE Exercise (TRAINER only)
//     @DeleteMapping("/{id}")
//     public ResponseEntity<?> deleteExercise(@PathVariable Long id) {
//         if (!isTrainer()) {
//             return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only TRAINER can delete exercises");
//         }
//         service.deleteExercise(id);
//         return ResponseEntity.noContent().build();
//     }

//     // Helper Method to Check Role
//     private boolean isTrainer() {
//         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//         if (auth == null || auth.getAuthorities() == null) return false;
//         return auth.getAuthorities().stream()
//                 .anyMatch(a -> a.getAuthority().equals("ROLE_TRAINER"));
//     }
// }
package com.example.demo.controller;

import com.example.demo.model.ExerciseLibrary;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.ExerciseLibraryService;
import com.example.demo.service.UserService;
import com.example.demo.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercise-library")
@CrossOrigin(origins = "*")
public class ExerciseLibraryController {

    private final ExerciseLibraryService service;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public ExerciseLibraryController(ExerciseLibraryService service, JwtUtil jwtUtil, UserService userService) {
        this.service = service;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<ExerciseLibrary>> getAllExercises() {
        return ResponseEntity.ok(service.getAllExercises());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseLibrary> getExercise(@PathVariable Long id) {
        ExerciseLibrary ex = service.getExercise(id);
        return ex == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(ex);
    }

    @PostMapping
    public ResponseEntity<?> createExercise(@RequestHeader("Authorization") String authHeader,
                                            @RequestBody ExerciseLibrary exercise) {
        if (!isTrainer(authHeader)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only TRAINER can create exercises");
        }
        ExerciseLibrary saved = service.saveExercise(exercise, null);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateExercise(@RequestHeader("Authorization") String authHeader,
                                            @PathVariable Long id,
                                            @RequestBody ExerciseLibrary exercise) {
        if (!isTrainer(authHeader)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only TRAINER can update exercises");
        }
        exercise.setId(id);
        ExerciseLibrary updated = service.saveExercise(exercise, null);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExercise(@RequestHeader("Authorization") String authHeader,
                                            @PathVariable Long id) {
        if (!isTrainer(authHeader)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only TRAINER can delete exercises");
        }
        service.deleteExercise(id);
        return ResponseEntity.noContent().build();
    }

    private boolean isTrainer(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) return false;
        String token = authHeader.substring(7);
        String email = jwtUtil.extractUsername(token);
        User user = userService.getUserByEmail(email).orElse(null);
        return user != null && user.getRole() == Role.TRAINER;
    }
}
