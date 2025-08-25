// package com.example.demo.controller;


// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;

// import com.example.demo.model.ExerciseLibrary;
// import com.example.demo.service.ExerciseLibraryService;
// import com.example.demo.service.AuditLogService;

// @RestController
// @RequestMapping("/api/exercise-library")
// public class ExerciseLibraryController {

//     @Autowired
//     private ExerciseLibraryService service;

//     @Autowired
//     private AuditLogService auditLogService;

//     @GetMapping
//     public List<ExerciseLibrary> getAll() {
//         return service.getAll();
//     }

//     @GetMapping("/{id}")
//     public ExerciseLibrary getById(@PathVariable Long id) {
//         return service.getById(id);
//     }

//     @PostMapping
//     public ExerciseLibrary create(@RequestBody ExerciseLibrary library) {
//         auditLogService.log("CREATE_EXERCISE_LIBRARY", "system");
//         return service.create(library);
//     }

//     @PutMapping("/{id}")
//     public ExerciseLibrary update(@PathVariable Long id, @RequestBody ExerciseLibrary library) {
//         auditLogService.log("UPDATE_EXERCISE_LIBRARY", "system");
//         return service.update(id, library);
//     }

//     @DeleteMapping("/{id}")
//     public void delete(@PathVariable Long id) {
//         auditLogService.log("DELETE_EXERCISE_LIBRARY", "system");
//         service.delete(id);
//     }
// }
// ExerciseLibraryController.java (Controller)
// package com.example.demo.controller;

// import com.example.demo.model.ExerciseLibrary;
// import com.example.demo.service.ExerciseLibraryService;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.multipart.MultipartFile;

// import java.util.List;

// @RestController
// @RequestMapping("/exercise-library")
// public class ExerciseLibraryController {
//     private final ExerciseLibraryService service;

//     public ExerciseLibraryController(ExerciseLibraryService service) {
//         this.service = service;
//     }

//     @GetMapping
//     public List<ExerciseLibrary> getAllExercises() {
//         return service.getAllExercises();
//     }

//     @GetMapping("/{id}")
//     public ExerciseLibrary getExercise(@PathVariable Long id) {
//         return service.getExercise(id);
//     }

//     @PostMapping
//     public ExerciseLibrary createExercise(@RequestBody ExerciseLibrary exercise) {
//         return service.saveExercise(exercise);
//     }

//     @PutMapping("/{id}")
//     public ExerciseLibrary updateExercise(@PathVariable Long id, @RequestBody ExerciseLibrary exercise) {
//         exercise.setExerciseId(id);
//         return service.saveExercise(exercise);
//     }

//     @DeleteMapping("/{id}")
//     public void deleteExercise(@PathVariable Long id) {
//         service.deleteExercise(id);
//     }
// }
// ExerciseLibraryController.java
// @RestController
// @RequestMapping("/exercise-library")
// public class ExerciseLibraryController {
//     private final ExerciseLibraryService service;

//     public ExerciseLibraryController(ExerciseLibraryService service) {
//         this.service = service;
//     }

//     @GetMapping
//     public List<ExerciseLibrary> getAllExercises() {
//         return service.getAllExercises();
//     }

//     @GetMapping("/{id}")
//     public ExerciseLibrary getExercise(@PathVariable Long id) {
//         return service.getExercise(id);
//     }

//     @PostMapping(consumes = {"multipart/form-data"})
//     public ExerciseLibrary createExercise(
//             @RequestPart("exercise") ExerciseLibrary exercise,
//             @RequestPart(value = "file", required = false) MultipartFile file
//     ) {
//         return service.saveExercise(exercise, file);
//     }

//     @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
//     public ExerciseLibrary updateExercise(
//             @PathVariable Long id,
//             @RequestPart("exercise") ExerciseLibrary exercise,
//             @RequestPart(value = "file", required = false) MultipartFile file
//     ) {
//         exercise.setExerciseId(id);
//         return service.saveExercise(exercise, file);
//     }

//     @DeleteMapping("/{id}")
//     public void deleteExercise(@PathVariable Long id) {
//         service.deleteExercise(id);
//     }
// }
// package com.example.demo.controller;

// import com.example.demo.model.ExerciseLibrary;
// import com.example.demo.service.ExerciseLibraryService;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.multipart.MultipartFile;

// import java.util.List;

// @RestController
// @RequestMapping("/exercise-library")
// @CrossOrigin(origins = "*") // allow frontend React calls
// public class ExerciseLibraryController {
//     private final ExerciseLibraryService service;

//     public ExerciseLibraryController(ExerciseLibraryService service) {
//         this.service = service;
//     }

//     @GetMapping
//     public List<ExerciseLibrary> getAllExercises() {
//         return service.getAllExercises();
//     }

//     @GetMapping("/{id}")
//     public ExerciseLibrary getExercise(@PathVariable Long id) {
//         return service.getExercise(id);
//     }

//     @PostMapping(consumes = {"multipart/form-data"})
//     public ExerciseLibrary createExercise(
//             @RequestPart("exercise") ExerciseLibrary exercise,
//             @RequestPart(value = "file", required = false) MultipartFile file
//     ) {
//         return service.saveExercise(exercise, file);
//     }

//     @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
//     public ExerciseLibrary updateExercise(
//             @PathVariable Long id,
//             @RequestPart("exercise") ExerciseLibrary exercise,
//             @RequestPart(value = "file", required = false) MultipartFile file
//     ) {
//         exercise.setExerciseId(id);
//         return service.saveExercise(exercise, file);
//     }

//     @DeleteMapping("/{id}")
//     public void deleteExercise(@PathVariable Long id) {
//         service.deleteExercise(id);
//     }
// }
// package com.example.demo.controller;

// import com.example.demo.model.ExerciseLibrary;
// import com.example.demo.model.WorkoutAssignment;
// import com.example.demo.service.ExerciseLibraryService;
// import com.example.demo.service.WorkoutAssignmentService;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.multipart.MultipartFile;

// import java.util.List;

// @RestController
// @RequestMapping("/exercise-library")
// @CrossOrigin(origins = "*") // allow frontend React calls
// public class ExerciseLibraryController {

//     private final ExerciseLibraryService service;
//     private final WorkoutAssignmentService assignmentService;

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

//     @PostMapping(consumes = {"multipart/form-data"})
//     public ExerciseLibrary createExercise(
//             @RequestPart("exercise") ExerciseLibrary exercise,
//             @RequestPart(value = "file", required = false) MultipartFile file
//     ) {
//         return service.saveExercise(exercise, file);
//     }

//     @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
//     public ExerciseLibrary updateExercise(
//             @PathVariable Long id,
//             @RequestPart("exercise") ExerciseLibrary exercise,
//             @RequestPart(value = "file", required = false) MultipartFile file
//     ) {
//         exercise.setExerciseId(id);
//         return service.saveExercise(exercise, file);
//     }

//     @DeleteMapping("/{id}")
//     public void deleteExercise(@PathVariable Long id) {
//         service.deleteExercise(id);
//     }

//     // ------------------- NEW: Assign Exercises to Users -------------------

//     // Get all assigned exercises for a user
//     @GetMapping("/assignments/user/{userId}")
//     public List<WorkoutAssignment> getAssignmentsForUser(@PathVariable Long userId) {
//         return assignmentService.getAssignmentsForUser(userId);
//     }

//     // Assign a new exercise from the library to a user
//     @PostMapping("/assignments")
//     public WorkoutAssignment assignExercise(@RequestBody WorkoutAssignment assignment) {
//         return assignmentService.saveAssignment(assignment);
//     }

//     // Update an assignment (change sets/reps/schedule)
//     @PutMapping("/assignments/{id}")
//     public WorkoutAssignment updateAssignment(@PathVariable Long id,
//                                               @RequestBody WorkoutAssignment assignment) {
//         assignment.setId(id);
//         return assignmentService.saveAssignment(assignment);
//     }

//     // Delete an assignment (remove exercise from user’s plan)
//     @DeleteMapping("/assignments/{id}")
//     public void deleteAssignment(@PathVariable Long id) {
//         assignmentService.deleteAssignment(id);
//     }
// }
// package com.example.demo.controller;

// import com.example.demo.model.ExerciseLibrary;
// import com.example.demo.model.WorkoutAssignment;
// import com.example.demo.service.ExerciseLibraryService;
// import com.example.demo.service.WorkoutAssignmentService;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.multipart.MultipartFile;

// import java.util.List;

// @RestController
// @RequestMapping("/exercise-library")
// @CrossOrigin(origins = "*")
// public class ExerciseLibraryController {

//     private final ExerciseLibraryService service;
//     private final WorkoutAssignmentService assignmentService;

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

//     // Add new exercise (trainer can upload OR provide link)
//     @PostMapping(consumes = {"multipart/form-data"})
//     public ExerciseLibrary createExercise(
//             @RequestPart("exercise") ExerciseLibrary exercise,
//             @RequestPart(value = "file", required = false) MultipartFile file
//     ) {
//         return service.saveExercise(exercise, file);
//     }

//     @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
//     public ExerciseLibrary updateExercise(
//             @PathVariable Long id,
//             @RequestPart("exercise") ExerciseLibrary exercise,
//             @RequestPart(value = "file", required = false) MultipartFile file
//     ) {
//         exercise.setExerciseId(id);
//         return service.saveExercise(exercise, file);
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
//         exercise.setExerciseId(id);
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
//         exercise.setExerciseId(id);
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

// import com.example.demo.model.Exercise;
// import com.example.demo.repository.ExerciseRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/exercises")
// @CrossOrigin(origins = "*") // Allow frontend access
// public class ExerciseController {

//     @Autowired
//     private ExerciseRepository exerciseRepository;

//     // Add new exercise
//     @PostMapping
//     public Exercise createExercise(@RequestBody Exercise exercise) {
//         return exerciseRepository.save(exercise);
//     }

//     // Get all exercises
//     @GetMapping
//     public List<Exercise> getAllExercises() {
//         return exerciseRepository.findAll();
//     }

//     // Get single exercise by ID
//     @GetMapping("/{id}")
//     public Exercise getExerciseById(@PathVariable Long id) {
//         return exerciseRepository.findById(id).orElseThrow(() -> new RuntimeException("Exercise not found"));
//     }

//     // Update exercise
//     @PutMapping("/{id}")
//     public Exercise updateExercise(@PathVariable Long id, @RequestBody Exercise exerciseDetails) {
//         Exercise exercise = exerciseRepository.findById(id).orElseThrow(() -> new RuntimeException("Exercise not found"));

//         exercise.setName(exerciseDetails.getName());
//         exercise.setCategory(exerciseDetails.getCategory());
//         exercise.setMuscleGroup(exerciseDetails.getMuscleGroup());
//         exercise.setEquipment(exerciseDetails.getEquipment());
//         exercise.setDifficulty(exerciseDetails.getDifficulty());
//         exercise.setInstructions(exerciseDetails.getInstructions());
//         exercise.setImageUrl(exerciseDetails.getImageUrl());
//         exercise.setVideoUrl(exerciseDetails.getVideoUrl());

//         return exerciseRepository.save(exercise);
//     }

//     // Delete exercise
//     @DeleteMapping("/{id}")
//     public String deleteExercise(@PathVariable Long id) {
//         exerciseRepository.deleteById(id);
//         return "Exercise deleted successfully!";
//     }
// }
