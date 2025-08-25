// package com.example.demo.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;

// import com.example.demo.model.FitnessGoal;
// import com.example.demo.service.FitnessGoalService;
// import com.example.demo.service.AuditLogService;

// @RestController
// @RequestMapping("/api/fitness-goals")
// public class FitnessGoalController {

//     @Autowired
//     private FitnessGoalService service;

//     @Autowired
//     private AuditLogService auditLogService;

//     @GetMapping
//     public List<FitnessGoal> getAll() {
//         return service.getAll();
//     }

//     @GetMapping("/{id}")
//     public FitnessGoal getById(@PathVariable Long id) {
//         return service.getById(id);
//     }

//     @PostMapping
//     public FitnessGoal create(@RequestBody FitnessGoal goal) {
//         auditLogService.log("CREATE_FITNESS_GOAL", "system");
//         return service.create(goal);
//     }

//     @PutMapping("/{id}")
//     public FitnessGoal update(@PathVariable Long id, @RequestBody FitnessGoal goal) {
//         auditLogService.log("UPDATE_FITNESS_GOAL", "system");
//         return service.update(id, goal);
//     }

//     @DeleteMapping("/{id}")
//     public void delete(@PathVariable Long id) {
//         auditLogService.log("DELETE_FITNESS_GOAL", "system");
//         service.delete(id);
//     }
// }
// FitnessGoalsController.java (Controller)
// package com.example.demo.controller;

// import com.example.demo.model.FitnessGoals;
// import com.example.demo.service.FitnessGoalsService;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/fitness-goals")
// public class FitnessGoalsController {
//     private final FitnessGoalsService service;

//     public FitnessGoalsController(FitnessGoalsService service) {
//         this.service = service;
//     }

//     @GetMapping
//     public List<FitnessGoals> getAllGoals() {
//         return service.getAllGoals();
//     }

//     @GetMapping("/{id}")
//     public FitnessGoals getGoal(@PathVariable Long id) {
//         return service.getGoal(id);
//     }

//     @PostMapping
//     public FitnessGoals createGoal(@RequestBody FitnessGoals goal) {
//         return service.saveGoal(goal);
//     }

//     @PutMapping("/{id}")
//     public FitnessGoals updateGoal(@PathVariable Long id, @RequestBody FitnessGoals goal) {
//         goal.setGoalId(id);
//         return service.saveGoal(goal);
//     }

//     @DeleteMapping("/{id}")
//     public void deleteGoal(@PathVariable Long id) {
//         service.deleteGoal(id);
//     }
// }
// package com.example.demo.controller;

// import com.example.demo.model.FitnessGoals;
// import com.example.demo.service.FitnessGoalsService;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/fitness-goals")
// @CrossOrigin(origins = "*")
// public class FitnessGoalsController {

//     private final FitnessGoalsService service;

//     public FitnessGoalsController(FitnessGoalsService service) {
//         this.service = service;
//     }

//     // Fetch all goals for the logged-in user
//     @GetMapping("/user/{userId}")
//     public List<FitnessGoals> getGoalsByUser(@PathVariable Long userId) {
//         return service.getAllGoalsForUser(userId);
//     }

//     @GetMapping("/{id}")
//     public FitnessGoals getGoal(@PathVariable Long id) {
//         return service.getGoal(id);
//     }

//     @PostMapping
//     public FitnessGoals createGoal(@RequestBody FitnessGoals goal) {
//         return service.saveGoal(goal);
//     }

//     @PutMapping("/{id}")
//     public FitnessGoals updateGoal(@PathVariable Long id, @RequestBody FitnessGoals goal) {
//         goal.setGoalId(id);
//         return service.saveGoal(goal);
//     }

//     @DeleteMapping("/{id}")
//     public void deleteGoal(@PathVariable Long id) {
//         service.deleteGoal(id);
//     }
// }
package com.example.demo.controller;

import com.example.demo.model.FitnessGoals;
import com.example.demo.service.FitnessGoalsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fitness-goals")
@CrossOrigin(origins = "*")
public class FitnessGoalsController {

    private final FitnessGoalsService service;

    public FitnessGoalsController(FitnessGoalsService service) {
        this.service = service;
    }

    // Get all goals for a specific user
    @GetMapping("/user/{userId}")
    public List<FitnessGoals> getGoalsForUser(@PathVariable Long userId) {
        return service.getAllGoalsForUser(userId);
    }

    // Get single goal by ID
    @GetMapping("/{id}")
    public FitnessGoals getGoal(@PathVariable Long id) {
        return service.getGoal(id);
    }

    // Create new goal
    @PostMapping
    public FitnessGoals createGoal(@RequestBody FitnessGoals goal) {
        return service.saveGoal(goal);
    }

    // Update existing goal
    @PutMapping("/{id}")
    public FitnessGoals updateGoal(@PathVariable Long id, @RequestBody FitnessGoals goal) {
        goal.setGoalId(id);
        return service.saveGoal(goal);
    }

    // Delete goal
    @DeleteMapping("/{id}")
    public void deleteGoal(@PathVariable Long id) {
        service.deleteGoal(id);
    }
}
