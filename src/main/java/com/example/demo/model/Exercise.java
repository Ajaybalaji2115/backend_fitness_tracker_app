// package com.example.demo.model;



// import jakarta.persistence.*;
// import lombok.Data;

// @Entity
// @Data
// public class Exercise {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String name;
//     private String description;
//     private String muscleGroup;
//     private String equipment;
//     private String difficulty; // Optionally convert to enum later
//     @ManyToOne
// @JoinColumn(name = "workout_plan_id")
// private WorkoutPlan workoutPlan;
// }
// Exercise.java (Model)
// package com.example.demo.model;

// import jakarta.persistence.*;
// import lombok.Data;

// @Entity
// @Data
// @Table(name = "exercises")
// public class Exercise {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long exerciseId;

//     private String name;
//     private String description;
//     private Integer sets;
//     private Integer reps;

//     @ManyToOne
//     @JoinColumn(name = "workout_plan_id")
//     private WorkoutPlan workoutPlan;

//     private String targetMuscles;
//     private String equipment;
// }
// package com.example.demo.model;

// import jakarta.persistence.*;

// @Entity
// public class Exercise {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String name;
//     private String description;
//     private String imageUrl;   // store image path/URL
//     private String videoUrl;   // optional YouTube/demo link

//     public Exercise() {}

//     public Exercise(String name, String description, String imageUrl, String videoUrl) {
//         this.name = name;
//         this.description = description;
//         this.imageUrl = imageUrl;
//         this.videoUrl = videoUrl;
//     }

//     // getters & setters
//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }

//     public String getName() { return name; }
//     public void setName(String name) { this.name = name; }

//     public String getDescription() { return description; }
//     public void setDescription(String description) { this.description = description; }

//     public String getImageUrl() { return imageUrl; }
//     public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

//     public String getVideoUrl() { return videoUrl; }
//     public void setVideoUrl(String videoUrl) { this.videoUrl = videoUrl; }
// }
