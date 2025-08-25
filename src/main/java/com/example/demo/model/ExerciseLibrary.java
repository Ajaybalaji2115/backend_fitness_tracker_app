// package com.example.demo.model;



// import jakarta.persistence.*;
// import lombok.Data;

// @Entity
// @Data
// public class ExerciseLibrary {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String name;
//     private String description;
//     private String category;    // e.g., Strength, Cardio, Flexibility
//     private String videoUrl;    // Optional instructional video
// }
// ExerciseLibrary.java (Model)
// package com.example.demo.model;

// import jakarta.persistence.*;
// import lombok.Data;

// @Entity
// @Data
// @Table(name = "exercise_library")
// public class ExerciseLibrary {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long exerciseId;

//     private String name;
//     private String category;
//     private String muscleGroup;
//     private String equipment;
//     private String difficulty;
//     private String instructions;
// }
// ExerciseLibraryService.java
// ExerciseLibrary.java
// @Entity
// @Data
// @Table(name = "exercise_library")
// public class ExerciseLibrary {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long exerciseId;

//     private String name;
//     private String category;
//     private String muscleGroup;
//     private String equipment;
//     private String difficulty;
//     private String instructions;

//     private String mediaUrl; // image/video URL stored in DB
// }
// package com.example.demo.model;

// import jakarta.persistence.*;
// import lombok.Data;

// @Entity
// @Data
// @Table(name = "exercise_library")
// public class ExerciseLibrary {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long exerciseId;

//     private String name;
//     private String category;
//     private String muscleGroup;
//     private String equipment;
//     private String difficulty;
//     private String instructions;

//     private String mediaUrl; // image/video URL stored in DB
// }
// package com.example.demo.model;

// import jakarta.persistence.*;
// import lombok.Data;

// @Entity
// @Data
// @Table(name = "exercise_library")
// public class ExerciseLibrary {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Column(name = "id") // match DB column
//     private Long id;

//     @Column(name = "name")
//     private String name;

//     @Column(name = "category")
//     private String category;

//     @Column(name = "muscle_group")
//     private String muscleGroup;

//     @Column(name = "equipment")
//     private String equipment;

//     @Column(name = "difficulty")
//     private String difficulty;

//     @Column(name = "instructions")
//     private String instructions;

//     @Column(name = "description")
//     private String description; // optional, if used

//     @Column(name = "image_url")
//     private String imageUrl;

//     @Column(name = "video_url")
//     private String videoUrl;

//     @Column(name = "media_url")
//     private String mediaUrl;

//     @Column(name = "media_type")
//     private String mediaType;
// }
package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "exercise_library")
public class ExerciseLibrary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private String muscleGroup;
    private String equipment;
    private String difficulty;
    private String instructions;
    private String description; 
    private String imageUrl;
    private String videoUrl;
    private String mediaUrl;   // uploaded file URL
    private String mediaType;  // image/video/link
}
