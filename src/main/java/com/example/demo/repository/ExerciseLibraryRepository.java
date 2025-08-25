// package com.example.demo.repository;



// import org.springframework.data.jpa.repository.JpaRepository;
// import com.example.demo.model.ExerciseLibrary;

// public interface ExerciseLibraryRepository extends JpaRepository<ExerciseLibrary, Long> {
// }
// ExerciseLibraryRepository.java (Repository)
// package com.example.demo.repository;

// import com.example.demo.model.ExerciseLibrary;
// import org.springframework.data.jpa.repository.JpaRepository;

// public interface ExerciseLibraryRepository extends JpaRepository<ExerciseLibrary, Long> {
// }
package com.example.demo.repository;

import com.example.demo.model.ExerciseLibrary;
import com.example.demo.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseLibraryRepository extends JpaRepository<ExerciseLibrary, Long> {
    
}
