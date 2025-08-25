// CompletedExercise.java (Model for a completed exercise)
package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "completed_exercises")
public class CompletedExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long completedId;

    private String name;
    private Integer setsCompleted;
    private Integer repsCompleted;
    private Double weightUsed;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private WorkoutSession workoutSession;
}