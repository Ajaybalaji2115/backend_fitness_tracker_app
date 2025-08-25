// package com.example.demo.model;

// import jakarta.persistence.*;

// @Entity
// public class WorkoutAssignment {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private Long userId;       // trainee
//     private Long exerciseId;   // exercise from ExerciseLibrary
//     private int sets;
//     private int reps;
//     private int duration;      // in seconds/minutes
//     private int restTime;      // rest in seconds
//     private String scheduleDay;
//     private boolean completed = false;

//     // getters & setters
//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }

//     public Long getUserId() { return userId; }
//     public void setUserId(Long userId) { this.userId = userId; }

//     public Long getExerciseId() { return exerciseId; }
//     public void setExerciseId(Long exerciseId) { this.exerciseId = exerciseId; }

//     public int getSets() { return sets; }
//     public void setSets(int sets) { this.sets = sets; }

//     public int getReps() { return reps; }
//     public void setReps(int reps) { this.reps = reps; }

//     public int getDuration() { return duration; }
//     public void setDuration(int duration) { this.duration = duration; }

//     public int getRestTime() { return restTime; }
//     public void setRestTime(int restTime) { this.restTime = restTime; }

//     public String getScheduleDay() { return scheduleDay; }
//     public void setScheduleDay(String scheduleDay) { this.scheduleDay = scheduleDay; }

//     public boolean isCompleted() { return completed; }
//     public void setCompleted(boolean completed) { this.completed = completed; }
// }
// package com.example.demo.model;

// import jakarta.persistence.*;
// import lombok.Data;

// import java.time.LocalDateTime;

// @Entity
// @Data
// @Table(name = "workout_assignments")
// public class WorkoutAssignment {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private Long userId;
//     private Long exerciseId;
//     private Integer sets;
//     private Integer reps;
//     private Integer duration; // in seconds
//     private Integer restTime; // in seconds

//     private LocalDateTime scheduledDate; // 🔹 workout date & time
//     private Boolean completed = false;
// }
// package com.example.demo.model;

// import jakarta.persistence.*;
// import lombok.Data;
// import java.time.LocalDateTime;

// import com.fasterxml.jackson.annotation.JsonFormat;

// @Entity
// @Data
// @Table(name = "workout_assignments")
// public class WorkoutAssignment {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private Long userId;
//     private Long exerciseId;
//     private Integer sets;
//     private Integer reps;
//     private Integer duration; // seconds
//     private Integer restTime; // seconds
//     private String scheduleDay; // Added
//     @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
//     private LocalDateTime timestamp; // Renamed / Added

//     private Boolean completed = false;
// }
// package com.example.demo.model;

// import com.fasterxml.jackson.annotation.JsonFormat;
// import jakarta.persistence.*;
// import lombok.Data;

// import java.time.LocalDateTime;

// @Entity
// @Data
// @Table(name = "workout_assignments")
// public class WorkoutAssignment {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private Long userId;
//     private Long exerciseId;
//     private Integer sets;
//     private Integer reps;
//     private Integer duration; // seconds
//     private Integer restTime; // seconds
//     private String scheduleDay; // e.g., Monday, Tuesday

//     @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
//     private LocalDateTime scheduledDate; // renamed to match repository

//     private Boolean completed = false;
// }
// package com.example.demo.model;

// import com.fasterxml.jackson.annotation.JsonFormat;
// import jakarta.persistence.*;
// import lombok.Data;

// import java.time.LocalDateTime;

// @Entity
// @Data
// @Table(name = "workout_assignments")
// public class WorkoutAssignment {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private Long userId;
//     private Long exerciseId;
//     private Integer sets;
//     private Integer reps;
//     private Integer duration; // seconds
//     private Integer restTime; // seconds
//     private String scheduleDay; // e.g., Monday, Tuesday

//     @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
//     private LocalDateTime scheduledDate; // backend field for timestamp

//     private Boolean completed = false;
// }
package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "workout_assignments")
public class WorkoutAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;       // trainee ID
    private Long assignedBy;   // trainer/admin ID
    private Long exerciseId;
    private Integer sets;
    private Integer reps;
    private Integer duration; // seconds
    private Integer restTime; // seconds
    private String scheduleDay; // e.g., Monday, Tuesday
private Long trainerId; // ID of the trainer who assigned

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime scheduledDate;

    private Boolean completed = false;
}
