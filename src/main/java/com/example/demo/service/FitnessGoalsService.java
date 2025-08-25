
// package com.example.demo.service;

// import com.example.demo.model.FitnessGoals;
// import com.example.demo.model.Notification;
// import com.example.demo.repository.FitnessGoalsRepository;
// import com.example.demo.repository.NotificationRepository;
// import org.springframework.stereotype.Service;

// import java.time.LocalDate;
// import java.util.List;

// @Service
// public class FitnessGoalsService {
//     private final FitnessGoalsRepository repository;
//     private final NotificationRepository notificationRepository;

//     public FitnessGoalsService(FitnessGoalsRepository repository, NotificationRepository notificationRepository) {
//         this.repository = repository;
//         this.notificationRepository = notificationRepository;
//     }

//     public List<FitnessGoals> getAllGoals() {
//         return repository.findAll();
//     }

//     public FitnessGoals getGoal(Long id) {
//         return repository.findById(id).orElse(null);
//     }

//     public FitnessGoals saveGoal(FitnessGoals goal) {
//         // Check progress vs. target
//         if (goal.getCurrentProgress() != null && goal.getTargetValue() != null) {
//             if (goal.getCurrentProgress() >= goal.getTargetValue()) {
//                 goal.setStatus("COMPLETED");
//                 goal.setAchievedDate(LocalDate.now());

//                 // Create notification
//                 Notification notif = new Notification();
//                 notif.setUserId(goal.getUserId());
//                 notif.setMessage("🎉 Congratulations! You achieved your fitness goal: " + goal.getGoalType());
//                 notif.setType("ACHIEVEMENT");
//                 notif.setIsRead(false);
//                 notif.setCreatedDate(LocalDate.now().atStartOfDay()); 
//                 notif.setPriority("HIGH");

//                 notificationRepository.save(notif);
//             } else if ("PENDING".equals(goal.getStatus())) {
//                 goal.setStatus("IN_PROGRESS");
//             }
//         }
//         return repository.save(goal);
//     }

//     public void deleteGoal(Long id) {
//         repository.deleteById(id);
//     }
// }
// package com.example.demo.service;

// import com.example.demo.model.FitnessGoals;
// import com.example.demo.model.Notification;
// import com.example.demo.repository.FitnessGoalsRepository;
// import com.example.demo.repository.NotificationRepository;
// import org.springframework.stereotype.Service;

// import java.time.LocalDate;
// import java.util.List;

// @Service
// public class FitnessGoalsService {
//     private final FitnessGoalsRepository repository;
//     private final NotificationRepository notificationRepository;

//     public FitnessGoalsService(FitnessGoalsRepository repository, NotificationRepository notificationRepository) {
//         this.repository = repository;
//         this.notificationRepository = notificationRepository;
//     }

//     public List<FitnessGoals> getAllGoalsForUser(Long userId) {
//         return repository.findByUserId(userId);
//     }

//     public FitnessGoals getGoal(Long id) {
//         return repository.findById(id).orElse(null);
//     }

//     public FitnessGoals saveGoal(FitnessGoals goal) {
//         // Check progress vs. target
//         if (goal.getCurrentProgress() != null && goal.getTargetValue() != null) {
//             if (goal.getCurrentProgress() >= goal.getTargetValue()) {
//                 goal.setStatus("COMPLETED");
//                 goal.setAchievedDate(LocalDate.now());

//                 // Create notification
//                 Notification notif = new Notification();
               
//                 notif.setUserId(goal.getUserId());
//                 notif.setMessage("🎉 Congratulations! You achieved your fitness goal: " + goal.getGoalType());
//                 notif.setType("ACHIEVEMENT");
//                 notif.setIsRead(false);
//                 notif.setCreatedDate(LocalDate.now().atStartOfDay());
//                 notif.setPriority("HIGH");

//                 notificationRepository.save(notif);
//             } else if ("PENDING".equals(goal.getStatus())) {
//                 goal.setStatus("IN_PROGRESS");
//             }
//         }
//         return repository.save(goal);
//     }

//     public void deleteGoal(Long id) {
//         repository.deleteById(id);
//     }
// }
package com.example.demo.service;

import com.example.demo.model.FitnessGoals;
import com.example.demo.model.Notification;
import com.example.demo.repository.FitnessGoalsRepository;
import com.example.demo.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FitnessGoalsService {

    private final FitnessGoalsRepository repository;
    private final NotificationRepository notificationRepository;

    public FitnessGoalsService(FitnessGoalsRepository repository, NotificationRepository notificationRepository) {
        this.repository = repository;
        this.notificationRepository = notificationRepository;
    }

    // Fetch all goals for a specific user
    public List<FitnessGoals> getAllGoalsForUser(Long userId) {
        return repository.findByUserId(userId);
    }

    // Fetch single goal by ID
    public FitnessGoals getGoal(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Create or update goal
    public FitnessGoals saveGoal(FitnessGoals goal) {
        // Automatically update status if target achieved
        if (goal.getCurrentProgress() != null && goal.getTargetValue() != null) {
            if (goal.getCurrentProgress() >= goal.getTargetValue()) {
                goal.setStatus("COMPLETED");
                goal.setAchievedDate(LocalDate.now());

                // Create notification
                Notification notif = new Notification();
                notif.setUserId(goal.getUserId());
                notif.setMessage("🎉 Congratulations! You achieved your goal: " + goal.getGoalType());
                notif.setType("ACHIEVEMENT");
                notif.setIsRead(false);
                notif.setCreatedDate(LocalDate.now().atStartOfDay());
                notif.setPriority("HIGH");
                notificationRepository.save(notif);
            } else if ("PENDING".equals(goal.getStatus())) {
                goal.setStatus("IN_PROGRESS");
            }
        }
        return repository.save(goal);
    }

    // Delete goal
    public void deleteGoal(Long id) {
        repository.deleteById(id);
    }
}
