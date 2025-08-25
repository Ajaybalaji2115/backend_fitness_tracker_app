// package com.example.demo.service;





// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import java.util.List;

// import com.example.demo.model.WorkoutSession;
// import com.example.demo.repository.WorkoutSessionRepository;

// @Service
// public class WorkoutSessionService {

//     @Autowired
//     private WorkoutSessionRepository repository;

//     public List<WorkoutSession> getAll() {
//         return repository.findAll();
//     }

//     public WorkoutSession getById(Long id) {
//         return repository.findById(id).orElse(null);
//     }

//     public WorkoutSession create(WorkoutSession session) {
//         return repository.save(session);
//     }

//     public WorkoutSession update(Long id, WorkoutSession session) {
//         session.setId(id);
//         return repository.save(session);
//     }

//     public void delete(Long id) {
//         repository.deleteById(id);
//     }
// }
// WorkoutSessionService.java (Service)
package com.example.demo.service;

import com.example.demo.model.WorkoutSession;
import com.example.demo.repository.WorkoutSessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutSessionService {
    private final WorkoutSessionRepository repository;

    public WorkoutSessionService(WorkoutSessionRepository repository) {
        this.repository = repository;
    }

    public List<WorkoutSession> getAllSessions() {
        return repository.findAll();
    }

    public WorkoutSession getSession(Long id) {
        return repository.findById(id).orElse(null);
    }

    public WorkoutSession saveSession(WorkoutSession session) {
        return repository.save(session);
    }

    public void deleteSession(Long id) {
        repository.deleteById(id);
    }
}
