
// package com.example.demo.service;

// import com.example.demo.model.ExerciseLibrary;
// import com.example.demo.repository.ExerciseLibraryRepository;
// import org.springframework.stereotype.Service;
// import org.springframework.web.multipart.MultipartFile;

// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Paths;
// import java.nio.file.StandardCopyOption;
// import java.util.List;

// @Service
// public class ExerciseLibraryService {
//     private final ExerciseLibraryRepository repository;

//     public ExerciseLibraryService(ExerciseLibraryRepository repository) {
//         this.repository = repository;
//     }

//     public List<ExerciseLibrary> getAllExercises() {
//         return repository.findAll();
//     }

//     public ExerciseLibrary getExercise(Long id) {
//         return repository.findById(id).orElse(null);
//     }

//     public ExerciseLibrary saveExercise(ExerciseLibrary exercise, MultipartFile file) {
//         try {
//             if (file != null && !file.isEmpty()) {
//                 String uploadDir = "uploads/";
//                 Files.createDirectories(Paths.get(uploadDir));
//                 String filePath = uploadDir + file.getOriginalFilename();

//                 Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
//                 exercise.setMediaUrl("/" + filePath);
                
//                 // Guess file type
//                 if (file.getContentType() != null && file.getContentType().startsWith("video")) {
//                     exercise.setMediaType("video");
//                 } else {
//                     exercise.setMediaType("image");
//                 }
//             } else if (exercise.getMediaUrl() != null && !exercise.getMediaUrl().isBlank()) {
//                 // Trainer provided external link (YouTube, etc.)
//                 if (exercise.getMediaUrl().contains("youtube") || exercise.getMediaUrl().contains("youtu.be")) {
//                     exercise.setMediaType("video");
//                 } else {
//                     exercise.setMediaType("link");
//                 }
//             }
//         } catch (IOException e) {
//             throw new RuntimeException("File upload failed", e);
//         }
//         return repository.save(exercise);
//     }

//     public void deleteExercise(Long id) {
//         repository.deleteById(id);
//     }
// }
package com.example.demo.service;

import com.example.demo.model.ExerciseLibrary;
import com.example.demo.model.User;
import com.example.demo.repository.ExerciseLibraryRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class ExerciseLibraryService {
    private final ExerciseLibraryRepository repository;

    public ExerciseLibraryService(ExerciseLibraryRepository repository) {
        this.repository = repository;
    }

    public List<ExerciseLibrary> getAllExercises() {
        return repository.findAll();
    }

    public ExerciseLibrary getExercise(Long id) {
        return repository.findById(id).orElse(null);
    }

    public ExerciseLibrary saveExercise(ExerciseLibrary exercise, MultipartFile file) {
        try {
            if (file != null && !file.isEmpty()) {
                String uploadDir = "uploads/";
                Files.createDirectories(Paths.get(uploadDir));
                String filePath = uploadDir + file.getOriginalFilename();

                Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
                exercise.setMediaUrl("/" + filePath);

                if (file.getContentType() != null && file.getContentType().startsWith("video")) {
                    exercise.setMediaType("video");
                } else {
                    exercise.setMediaType("image");
                }
            } else if (exercise.getMediaUrl() != null && !exercise.getMediaUrl().isBlank()) {
                if (exercise.getMediaUrl().contains("youtube") || exercise.getMediaUrl().contains("youtu.be")) {
                    exercise.setMediaType("link");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("File upload failed", e);
        }
        return repository.save(exercise);
    }

    public void deleteExercise(Long id) {
        repository.deleteById(id);
    }
     
}
