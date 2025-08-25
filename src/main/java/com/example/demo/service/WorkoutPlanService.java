
package com.example.demo.service;

import com.example.demo.model.WorkoutPlan;
import com.example.demo.repository.WorkoutPlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutPlanService {
    private final WorkoutPlanRepository repository;

    public WorkoutPlanService(WorkoutPlanRepository repository) {
        this.repository = repository;
    }

    public List<WorkoutPlan> getAllPlans() {
        return repository.findAll();
    }

    public WorkoutPlan getPlan(Long id) {
        return repository.findById(id).orElse(null);
    }

    public WorkoutPlan savePlan(WorkoutPlan plan) {
        return repository.save(plan);
    }

    public void deletePlan(Long id) {
        repository.deleteById(id);
    }
}

