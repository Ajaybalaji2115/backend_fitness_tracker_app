package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "progress_history")
public class ProgressHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long historyId;
    private Long userId;

    private LocalDate date;
    private Double completionPercentage;
    private String notes;

    // Optional metrics (like calories, duration)
    private Double caloriesBurned;
    private Double durationMinutes;

    // Getters and Setters
    public Long getHistoryId() {
        return historyId;
    }
    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getCompletionPercentage() {
        return completionPercentage;
    }
    public void setCompletionPercentage(Double completionPercentage) {
        this.completionPercentage = completionPercentage;
    }

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Double getCaloriesBurned() {
        return caloriesBurned;
    }
    public void setCaloriesBurned(Double caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public Double getDurationMinutes() {
        return durationMinutes;
    }
    public void setDurationMinutes(Double durationMinutes) {
        this.durationMinutes = durationMinutes;
    }
}
