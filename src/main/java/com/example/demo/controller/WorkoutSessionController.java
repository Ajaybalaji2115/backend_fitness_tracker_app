// package com.example.demo.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;

// import com.example.demo.model.WorkoutSession;
// import com.example.demo.service.WorkoutSessionService;
// import com.example.demo.service.AuditLogService;

// @RestController
// @RequestMapping("/api/workout-sessions")
// public class WorkoutSessionController {

//     @Autowired
//     private WorkoutSessionService service;

//     @Autowired
//     private AuditLogService auditLogService;

//     @GetMapping
//     public List<WorkoutSession> getAll() {
//         return service.getAll();
//     }

//     @GetMapping("/{id}")
//     public WorkoutSession getById(@PathVariable Long id) {
//         return service.getById(id);
//     }

//     @PostMapping
//     public WorkoutSession create(@RequestBody WorkoutSession session) {
//         auditLogService.log("CREATE_WORKOUT_SESSION", "system");
//         return service.create(session);
//     }

//     @PutMapping("/{id}")
//     public WorkoutSession update(@PathVariable Long id, @RequestBody WorkoutSession session) {
//         auditLogService.log("UPDATE_WORKOUT_SESSION", "system");
//         return service.update(id, session);
//     }

//     @DeleteMapping("/{id}")
//     public void delete(@PathVariable Long id) {
//         auditLogService.log("DELETE_WORKOUT_SESSION", "system");
//         service.delete(id);
//     }
// }
// WorkoutSessionController.java (Controller)
package com.example.demo.controller;

import com.example.demo.model.WorkoutSession;
import com.example.demo.service.WorkoutSessionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workout-sessions")
public class WorkoutSessionController {
    private final WorkoutSessionService service;

    public WorkoutSessionController(WorkoutSessionService service) {
        this.service = service;
    }

    @GetMapping
    public List<WorkoutSession> getAllSessions() {
        return service.getAllSessions();
    }

    @GetMapping("/{id}")
    public WorkoutSession getSession(@PathVariable Long id) {
        return service.getSession(id);
    }

    @PostMapping
    public WorkoutSession createSession(@RequestBody WorkoutSession session) {
        return service.saveSession(session);
    }

    @PutMapping("/{id}")
    public WorkoutSession updateSession(@PathVariable Long id, @RequestBody WorkoutSession session) {
        session.setSessionId(id);
        return service.saveSession(session);
    }

    @DeleteMapping("/{id}")
    public void deleteSession(@PathVariable Long id) {
        service.deleteSession(id);
    }
}
