// package com.example.demo.controller;



// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;

// import com.example.demo.model.WorkoutPlan;
// import com.example.demo.service.WorkoutPlanService;
// import com.example.demo.service.AuditLogService;

// @RestController
// @RequestMapping("/api/workout-plans")
// public class WorkoutPlanController {

//     @Autowired
//     private WorkoutPlanService service;

//     @Autowired
//     private AuditLogService auditLogService;

//     @GetMapping
//     public List<WorkoutPlan> getAll() {
//         return service.getAll();
//     }

//     @GetMapping("/{id}")
//     public WorkoutPlan getById(@PathVariable Long id) {
//         return service.getById(id);
//     }

//     @PostMapping
//     public WorkoutPlan create(@RequestBody WorkoutPlan plan) {
//         auditLogService.log("CREATE_WORKOUT_PLAN", plan.getCreatedBy());
//         return service.create(plan);
//     }

//     @PutMapping("/{id}")
//     public WorkoutPlan update(@PathVariable Long id, @RequestBody WorkoutPlan plan) {
//         auditLogService.log("UPDATE_WORKOUT_PLAN", plan.getCreatedBy());
//         return service.update(id, plan);
//     }

//     @DeleteMapping("/{id}")
//     public void delete(@PathVariable Long id) {
//         auditLogService.log("DELETE_WORKOUT_PLAN", "system"); // Replace "system" with real user
//         service.delete(id);
//     }
// }

// public class WorkoutPlanController {

//     private final WorkoutPlanService workoutPlanService;

//     public WorkoutPlanController(WorkoutPlanService workoutPlanService) {
//         this.workoutPlanService = workoutPlanService;
//     }

//     // Create
//     @PostMapping("/{createdById}")
//     public ResponseEntity<WorkoutPlan> createPlan(@RequestBody WorkoutPlan plan, @PathVariable Long createdById) {
//         return ResponseEntity.ok(workoutPlanService.createPlan(plan, createdById));
//     }

//     // Read
//     @GetMapping
//     public List<WorkoutPlan> getAllPlans() {
//         return workoutPlanService.getAllPlans();
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<WorkoutPlan> getPlanById(@PathVariable Long id) {
//         return workoutPlanService.getPlanById(id)
//                 .map(ResponseEntity::ok)
//                 .orElse(ResponseEntity.notFound().build());
//     }

//     @GetMapping("/difficulty/{difficulty}")
//     public List<WorkoutPlan> getPlansByDifficulty(@PathVariable String difficulty) {
//         return workoutPlanService.getPlansByDifficulty(difficulty);
//     }

//     @GetMapping("/creator/{creatorId}")
//     public List<WorkoutPlan> getPlansByCreator(@PathVariable Long creatorId) {
//         return workoutPlanService.getPlansByCreator(creatorId);
//     }

//     @GetMapping("/approved/{status}")
//     public List<WorkoutPlan> getPlansByApprovalStatus(@PathVariable boolean status) {
//         return workoutPlanService.getPlansByApprovalStatus(status);
//     }

//     // Update
//     @PutMapping("/{id}")
//     public ResponseEntity<WorkoutPlan> updatePlan(@PathVariable Long id, @RequestBody WorkoutPlan plan) {
//         return ResponseEntity.ok(workoutPlanService.updatePlan(id, plan));
//     }

//     @PutMapping("/{planId}/assign/{userId}")
//     public ResponseEntity<WorkoutPlan> assignUserToPlan(@PathVariable Long planId, @PathVariable Long userId) {
//         return ResponseEntity.ok(workoutPlanService.assignUserToPlan(planId, userId));
//     }

//     // Delete (soft delete)
//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deactivatePlan(@PathVariable Long id) {
//         workoutPlanService.deactivatePlan(id);
//         return ResponseEntity.noContent().build();
//     }
// }
// package com.example.demo.controller;

// import com.example.demo.model.WorkoutPlan;
// import com.example.demo.service.WorkoutPlanService;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/workout-plans")
// @CrossOrigin(origins = "http://localhost:3000")
// public class WorkoutPlanController {

//     private final WorkoutPlanService workoutPlanService;

//     public WorkoutPlanController(WorkoutPlanService workoutPlanService) {
//         this.workoutPlanService = workoutPlanService;
//     }

//     // Create or Update
//     @PostMapping
//     public ResponseEntity<WorkoutPlan> createPlan(@RequestBody WorkoutPlan plan) {
//         Long createdById = plan.getCreatedBy() != null ? plan.getCreatedBy().getId() : 1L; // guest fallback
//         return ResponseEntity.ok(workoutPlanService.createPlan(plan, createdById));
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<WorkoutPlan> updatePlan(@PathVariable Long id, @RequestBody WorkoutPlan plan) {
//         return ResponseEntity.ok(workoutPlanService.updatePlan(id, plan));
//     }

//     @PutMapping("/{id}/approve")
//     public ResponseEntity<WorkoutPlan> approvePlan(@PathVariable Long id) {
//         WorkoutPlan plan = workoutPlanService.getPlanById(id)
//                 .orElseThrow(() -> new RuntimeException("Plan not found"));
//         plan.setApproved(true);
//         return ResponseEntity.ok(workoutPlanService.updatePlan(id, plan));
//     }

//     // Read
//     @GetMapping
//     public List<WorkoutPlan> getAllPlans() {
//         return workoutPlanService.getAllPlans();
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<WorkoutPlan> getPlanById(@PathVariable Long id) {
//         return workoutPlanService.getPlanById(id)
//                 .map(ResponseEntity::ok)
//                 .orElse(ResponseEntity.notFound().build());
//     }

//     // Delete (soft)
//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deactivatePlan(@PathVariable Long id) {
//         workoutPlanService.deactivatePlan(id);
//         return ResponseEntity.noContent().build();
//     }
// }
// WorkoutPlanController.java
package com.example.demo.controller;

import com.example.demo.model.WorkoutPlan;
import com.example.demo.service.WorkoutPlanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plans")
public class WorkoutPlanController {
    private final WorkoutPlanService service;

    public WorkoutPlanController(WorkoutPlanService service) {
        this.service = service;
    }

    @GetMapping
    public List<WorkoutPlan> getAllPlans() {
        return service.getAllPlans();
    }

    @GetMapping("/{id}")
    public WorkoutPlan getPlan(@PathVariable Long id) {
        return service.getPlan(id);
    }

    @PostMapping
    public WorkoutPlan createPlan(@RequestBody WorkoutPlan plan) {
        return service.savePlan(plan);
    }

    @PutMapping("/{id}")
    public WorkoutPlan updatePlan(@PathVariable Long id, @RequestBody WorkoutPlan plan) {
        plan.setPlanId(id);
        return service.savePlan(plan);
    }

    @DeleteMapping("/{id}")
    public void deletePlan(@PathVariable Long id) {
        service.deletePlan(id);
    }
}
