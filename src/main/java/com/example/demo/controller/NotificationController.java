// package com.example.demo.controller;



// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// import com.example.demo.model.Notification;
// import com.example.demo.service.NotificationService;
// import com.example.demo.service.AuditLogService;

// @RestController
// @RequestMapping("/api/notifications")
// public class NotificationController {

//     @Autowired
//     private NotificationService service;

//     @Autowired
//     private AuditLogService auditLogService;

//     @GetMapping
//     public List<Notification> getAll() {
//         return service.getAll();
//     }

//     @GetMapping("/{id}")
//     public Notification getById(@PathVariable Long id) {
//         return service.getById(id);
//     }

//     @PostMapping
//     public Notification create(@RequestBody Notification notification) {
//         auditLogService.log("CREATE_NOTIFICATION", "system");
//         return service.create(notification);
//     }

//     @PutMapping("/{id}")
//     public Notification update(@PathVariable Long id, @RequestBody Notification notification) {
//         auditLogService.log("UPDATE_NOTIFICATION", "system");
//         return service.update(id, notification);
//     }

//     @DeleteMapping("/{id}")
//     public void delete(@PathVariable Long id) {
//         auditLogService.log("DELETE_NOTIFICATION", "system");
//         service.delete(id);
//     }
// }
package com.example.demo.controller;

import com.example.demo.model.Notification;
import com.example.demo.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    // 🔹 Get all notifications for a user
    
    @GetMapping("/{userId}")
    public List<Notification> getUserNotifications(@PathVariable Long userId) {
        return service.getNotificationsForUser(userId);
    }

    // 🔹 Create a new notification (optional, for testing)
    @PostMapping
    public Notification createNotification(@RequestBody Notification notification) {
        return service.createNotification(notification);
    }

    // 🔹 Mark as read
    @PutMapping("/{id}/read")
    public Notification markAsRead(@PathVariable Long id) {
        return service.markAsRead(id);
    }

    // 🔹 Delete notification
    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable Long id) {
        service.deleteNotification(id);
    }
}
