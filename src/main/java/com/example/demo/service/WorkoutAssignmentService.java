
// package com.example.demo.service;

// import com.example.demo.model.Notification;
// import com.example.demo.model.WorkoutAssignment;
// import com.example.demo.repository.NotificationRepository;
// import com.example.demo.repository.WorkoutAssignmentRepository;
// import org.springframework.scheduling.annotation.Scheduled;
// import org.springframework.stereotype.Service;

// import java.time.LocalDateTime;
// import java.util.List;

// @Service
// public class WorkoutAssignmentService {
//     private final WorkoutAssignmentRepository repository;
//     private final NotificationRepository notificationRepository;

//     public WorkoutAssignmentService(WorkoutAssignmentRepository repository, NotificationRepository notificationRepository) {
//         this.repository = repository;
//         this.notificationRepository = notificationRepository;
//     }

//     public WorkoutAssignment saveAssignment(WorkoutAssignment assignment) {
//         return repository.save(assignment);
//     }

//     public List<WorkoutAssignment> getAssignmentsForUser(Long userId) {
//         return repository.findByUserId(userId);
//     }

//     public void deleteAssignment(Long id) {
//         repository.deleteById(id);
//     }
// public WorkoutAssignment updateAssignment(Long id, WorkoutAssignment updatedAssignment) {
//     return repository.findById(id).map(existing -> {
//         existing.setExerciseId(updatedAssignment.getExerciseId());
//         existing.setSets(updatedAssignment.getSets());
//         existing.setReps(updatedAssignment.getReps());
//         existing.setDuration(updatedAssignment.getDuration());
//         existing.setRestTime(updatedAssignment.getRestTime());
//         existing.setScheduledDate(updatedAssignment.getScheduledDate());
//         existing.setCompleted(updatedAssignment.getCompleted());
//         return repository.save(existing);
//     }).orElseThrow(() -> new RuntimeException("WorkoutAssignment not found with id " + id));
// }

//     // 🔹 Scheduler: run every hour to check upcoming workouts
// @Scheduled(cron = "0 */5 * * * *")
//     public void checkUpcomingWorkouts() {
//         LocalDateTime now = LocalDateTime.now();
//         LocalDateTime nextHour = now.plusHours(1);

//         List<WorkoutAssignment> upcoming = repository.findByScheduledDateBetween(now, nextHour);

//         for (WorkoutAssignment wa : upcoming) {
//             Notification notif = new Notification();
//             notif.setUserId(wa.getUserId());
//             notif.setMessage("⏰ Reminder: You have a workout scheduled at " + wa.getScheduledDate());
//             notif.setType("REMINDER");
//             notif.setIsRead(false);
//             notif.setCreatedDate(LocalDateTime.now());
//             notif.setPriority("MEDIUM");

//             notificationRepository.save(notif);
//         }
//     }

//     // 🔹 Scheduler: check overdue workouts
//     @Scheduled(cron = "0 30 * * * *") // every hour at minute 30
//     public void checkMissedWorkouts() {
//         LocalDateTime now = LocalDateTime.now();
//         List<WorkoutAssignment> missed = repository.findByCompletedFalseAndScheduledDateBefore(now);

//         for (WorkoutAssignment wa : missed) {
//             Notification notif = new Notification();
//             notif.setUserId(wa.getUserId());
//             notif.setMessage("⚠️ You missed your scheduled workout at " + wa.getScheduledDate());
//             notif.setType("ALERT");
//             notif.setIsRead(false);
//             notif.setCreatedDate(LocalDateTime.now());
//             notif.setPriority("HIGH");

//             notificationRepository.save(notif);
//         }
//     }
    
// }

// package com.example.demo.service;

// import com.example.demo.model.Notification;
// import com.example.demo.model.WorkoutAssignment;
// import com.example.demo.repository.NotificationRepository;
// import com.example.demo.repository.WorkoutAssignmentRepository;
// import org.springframework.scheduling.annotation.Scheduled;
// import org.springframework.stereotype.Service;

// import java.time.LocalDateTime;
// import java.util.List;

// @Service
// public class WorkoutAssignmentService {

//     private final WorkoutAssignmentRepository repository;
//     private final NotificationRepository notificationRepository;

//     public WorkoutAssignmentService(WorkoutAssignmentRepository repository,
//                                     NotificationRepository notificationRepository) {
//         this.repository = repository;
//         this.notificationRepository = notificationRepository;
//     }

//     // 🔹 Save or create assignment (TRAINER/ADMIN)
//     public WorkoutAssignment saveAssignment(WorkoutAssignment assignment) {
//         return repository.save(assignment);
//     }

//     // 🔹 Get assignments for a user
//     public List<WorkoutAssignment> getAssignmentsForUser(Long userId) {
//         return repository.findByUserId(userId);
//     }

//     // 🔹 Delete assignment (TRAINER/ADMIN)
//     public void deleteAssignment(Long id) {
//         repository.deleteById(id);
//     }

//     // 🔹 Update assignment (TRAINER/ADMIN)
//     public WorkoutAssignment updateAssignment(Long id, WorkoutAssignment updatedAssignment) {
//         return repository.findById(id).map(existing -> {
//             existing.setExerciseId(updatedAssignment.getExerciseId());
//             existing.setSets(updatedAssignment.getSets());
//             existing.setReps(updatedAssignment.getReps());
//             existing.setDuration(updatedAssignment.getDuration());
//             existing.setRestTime(updatedAssignment.getRestTime());
//             existing.setScheduledDate(updatedAssignment.getScheduledDate());
//             existing.setCompleted(updatedAssignment.getCompleted());
//             return repository.save(existing);
//         }).orElseThrow(() -> new RuntimeException("WorkoutAssignment not found with id " + id));
//     }

//     // 🔹 Scheduler: notify upcoming workouts every 5 minutes
//     @Scheduled(cron = "0 */5 * * * *")
//     public void checkUpcomingWorkouts() {
//         LocalDateTime now = LocalDateTime.now();
//         LocalDateTime nextHour = now.plusHours(1);

//         List<WorkoutAssignment> upcoming = repository.findByScheduledDateBetween(now, nextHour);

//         for (WorkoutAssignment wa : upcoming) {
//             Notification notif = new Notification();
//             notif.setUserId(wa.getUserId());
//             notif.setMessage("⏰ Reminder: You have a workout scheduled at " + wa.getScheduledDate());
//             notif.setType("REMINDER");
//             notif.setIsRead(false);
//             notif.setCreatedDate(LocalDateTime.now());
//             notif.setPriority("MEDIUM");

//             notificationRepository.save(notif);
//         }
//     }

//     // 🔹 Scheduler: notify missed workouts every hour at 30 minutes
//     @Scheduled(cron = "0 30 * * * *")
//     public void checkMissedWorkouts() {
//         LocalDateTime now = LocalDateTime.now();
//         List<WorkoutAssignment> missed = repository.findByCompletedFalseAndScheduledDateBefore(now);

//         for (WorkoutAssignment wa : missed) {
//             Notification notif = new Notification();
//             notif.setUserId(wa.getUserId());
//             notif.setMessage("⚠️ You missed your scheduled workout at " + wa.getScheduledDate());
//             notif.setType("ALERT");
//             notif.setIsRead(false);
//             notif.setCreatedDate(LocalDateTime.now());
//             notif.setPriority("HIGH");

//             notificationRepository.save(notif);
//         }
//     }
// }
// package com.example.demo.service;

// import com.example.demo.model.Notification;
// import com.example.demo.model.WorkoutAssignment;
// import com.example.demo.repository.NotificationRepository;
// import com.example.demo.repository.WorkoutAssignmentRepository;
// import org.springframework.scheduling.annotation.Scheduled;
// import org.springframework.stereotype.Service;

// import java.time.LocalDateTime;
// import java.util.List;

// @Service
// public class WorkoutAssignmentService {

//     private final WorkoutAssignmentRepository repository;
//     private final NotificationRepository notificationRepository;

//     public WorkoutAssignmentService(WorkoutAssignmentRepository repository,
//                                     NotificationRepository notificationRepository) {
//         this.repository = repository;
//         this.notificationRepository = notificationRepository;
//     }

//     public WorkoutAssignment saveAssignment(WorkoutAssignment assignment) {
//         return repository.save(assignment);
//     }

//     public List<WorkoutAssignment> getAssignmentsForUser(Long userId) {
//         return repository.findByUserId(userId);
//     }

//     public WorkoutAssignment updateAssignment(Long id, WorkoutAssignment updatedAssignment) {
//         return repository.findById(id).map(existing -> {
//             existing.setExerciseId(updatedAssignment.getExerciseId());
//             existing.setSets(updatedAssignment.getSets());
//             existing.setReps(updatedAssignment.getReps());
//             existing.setDuration(updatedAssignment.getDuration());
//             existing.setRestTime(updatedAssignment.getRestTime());
//             existing.setScheduledDate(updatedAssignment.getScheduledDate());
//             existing.setCompleted(updatedAssignment.getCompleted());
//             return repository.save(existing);
//         }).orElseThrow(() -> new RuntimeException("WorkoutAssignment not found with id " + id));
//     }

//     public void deleteAssignment(Long id) {
//         repository.deleteById(id);
//     }

//     @Scheduled(cron = "0 */5 * * * *")
//     public void checkUpcomingWorkouts() {
//         LocalDateTime now = LocalDateTime.now();
//         LocalDateTime nextHour = now.plusHours(1);
//         List<WorkoutAssignment> upcoming = repository.findByScheduledDateBetween(now, nextHour);
//         for (WorkoutAssignment wa : upcoming) {
//             Notification notif = new Notification();
//             notif.setUserId(wa.getUserId());
//             notif.setMessage("⏰ Reminder: You have a workout scheduled at " + wa.getScheduledDate());
//             notif.setType("REMINDER");
//             notif.setIsRead(false);
//             notif.setCreatedDate(LocalDateTime.now());
//             notif.setPriority("MEDIUM");
//             notificationRepository.save(notif);
//         }
//     }

//     @Scheduled(cron = "0 30 * * * *")
//     public void checkMissedWorkouts() {
//         LocalDateTime now = LocalDateTime.now();
//         List<WorkoutAssignment> missed = repository.findByCompletedFalseAndScheduledDateBefore(now);
//         for (WorkoutAssignment wa : missed) {
//             Notification notif = new Notification();
//             notif.setUserId(wa.getUserId());
//             notif.setMessage("⚠️ You missed your scheduled workout at " + wa.getScheduledDate());
//             notif.setType("ALERT");
//             notif.setIsRead(false);
//             notif.setCreatedDate(LocalDateTime.now());
//             notif.setPriority("HIGH");
//             notificationRepository.save(notif);
//         }
//     }
// }
// package com.example.demo.service;

// import com.example.demo.model.Notification;
// import com.example.demo.model.WorkoutAssignment;
// import com.example.demo.repository.NotificationRepository;
// import com.example.demo.repository.WorkoutAssignmentRepository;
// import org.springframework.scheduling.annotation.Scheduled;
// import org.springframework.stereotype.Service;

// import java.time.LocalDateTime;
// import java.util.List;

// @Service
// public class WorkoutAssignmentService {

//     private final WorkoutAssignmentRepository repository;
//     private final NotificationRepository notificationRepository;

//     public WorkoutAssignmentService(WorkoutAssignmentRepository repository,
//                                     NotificationRepository notificationRepository) {
//         this.repository = repository;
//         this.notificationRepository = notificationRepository;
//     }

//     public WorkoutAssignment saveAssignment(WorkoutAssignment assignment) {
//         return repository.save(assignment);
//     }

//     public List<WorkoutAssignment> getAssignmentsForUser(Long userId) {
//         return repository.findByUserId(userId);
//     }

//     public WorkoutAssignment updateAssignment(Long id, WorkoutAssignment updatedAssignment) {
//         return repository.findById(id).map(existing -> {
//             existing.setExerciseId(updatedAssignment.getExerciseId());
//             existing.setSets(updatedAssignment.getSets());
//             existing.setReps(updatedAssignment.getReps());
//             existing.setDuration(updatedAssignment.getDuration());
//             existing.setRestTime(updatedAssignment.getRestTime());
//             existing.setScheduleDay(updatedAssignment.getScheduleDay());
//             existing.setScheduledDate(updatedAssignment.getScheduledDate());
//             existing.setCompleted(updatedAssignment.getCompleted());
//             return repository.save(existing);
//         }).orElseThrow(() -> new RuntimeException("WorkoutAssignment not found with id " + id));
//     }

//     public void deleteAssignment(Long id) {
//         repository.deleteById(id);
//     }

//     @Scheduled(cron = "0 */5 * * * *")
//     public void checkUpcomingWorkouts() {
//         LocalDateTime now = LocalDateTime.now();
//         LocalDateTime nextHour = now.plusHours(1);
//         List<WorkoutAssignment> upcoming = repository.findByScheduledDateBetween(now, nextHour);
//         for (WorkoutAssignment wa : upcoming) {
//             Notification notif = new Notification();
//             notif.setUserId(wa.getUserId());
//             notif.setMessage("⏰ Reminder: You have a workout scheduled at " + wa.getScheduledDate());
//             notif.setType("REMINDER");
//             notif.setIsRead(false);
//             notif.setCreatedDate(LocalDateTime.now());
//             notif.setPriority("MEDIUM");
//             notificationRepository.save(notif);
//         }
//     }

//     @Scheduled(cron = "0 30 * * * *")
//     public void checkMissedWorkouts() {
//         LocalDateTime now = LocalDateTime.now();
//         List<WorkoutAssignment> missed = repository.findByCompletedFalseAndScheduledDateBefore(now);
//         for (WorkoutAssignment wa : missed) {
//             Notification notif = new Notification();
//             notif.setUserId(wa.getUserId());
//             notif.setMessage("⚠️ You missed your scheduled workout at " + wa.getScheduledDate());
//             notif.setType("ALERT");
//             notif.setIsRead(false);
//             notif.setCreatedDate(LocalDateTime.now());
//             notif.setPriority("HIGH");
//             notificationRepository.save(notif);
//         }
//     }
// }
package com.example.demo.service;

import com.example.demo.model.Notification;
import com.example.demo.model.WorkoutAssignment;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.repository.WorkoutAssignmentRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WorkoutAssignmentService {

    private final WorkoutAssignmentRepository repository;
    private final NotificationRepository notificationRepository;

    public WorkoutAssignmentService(WorkoutAssignmentRepository repository,
                                    NotificationRepository notificationRepository) {
        this.repository = repository;
        this.notificationRepository = notificationRepository;
    }

    public WorkoutAssignment saveAssignment(WorkoutAssignment assignment) {
        return repository.save(assignment);
    }

    public List<WorkoutAssignment> getAssignmentsForUser(Long userId) {
        return repository.findByUserId(userId);
    }

    public List<WorkoutAssignment> getAssignmentsForTrainer(Long trainerId) {
        return repository.findByAssignedBy(trainerId);
    }

    public WorkoutAssignment updateAssignment(Long id, WorkoutAssignment updatedAssignment) {
        return repository.findById(id).map(existing -> {
            existing.setExerciseId(updatedAssignment.getExerciseId());
            existing.setSets(updatedAssignment.getSets());
            existing.setReps(updatedAssignment.getReps());
            existing.setDuration(updatedAssignment.getDuration());
            existing.setRestTime(updatedAssignment.getRestTime());
            existing.setScheduleDay(updatedAssignment.getScheduleDay());
            existing.setScheduledDate(updatedAssignment.getScheduledDate());
            existing.setCompleted(updatedAssignment.getCompleted());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("WorkoutAssignment not found with id " + id));
    }

    public void deleteAssignment(Long id) {
        repository.deleteById(id);
    }

    @Scheduled(cron = "0 */5 * * * *")
    public void checkUpcomingWorkouts() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextHour = now.plusHours(1);
        List<WorkoutAssignment> upcoming = repository.findByScheduledDateBetween(now, nextHour);
        for (WorkoutAssignment wa : upcoming) {
            Notification notif = new Notification();
            notif.setUserId(wa.getUserId());
            notif.setMessage("⏰ Reminder: You have a workout scheduled at " + wa.getScheduledDate());
            notif.setType("REMINDER");
            notif.setIsRead(false);
            notif.setCreatedDate(LocalDateTime.now());
            notif.setPriority("MEDIUM");
            notificationRepository.save(notif);
        }
    }

    @Scheduled(cron = "0 30 * * * *")
    public void checkMissedWorkouts() {
        LocalDateTime now = LocalDateTime.now();
        List<WorkoutAssignment> missed = repository.findByCompletedFalseAndScheduledDateBefore(now);
        for (WorkoutAssignment wa : missed) {
            Notification notif = new Notification();
            notif.setUserId(wa.getUserId());
            notif.setMessage("⚠️ You missed your scheduled workout at " + wa.getScheduledDate());
            notif.setType("ALERT");
            notif.setIsRead(false);
            notif.setCreatedDate(LocalDateTime.now());
            notif.setPriority("HIGH");
            notificationRepository.save(notif);
        }
    }
}
