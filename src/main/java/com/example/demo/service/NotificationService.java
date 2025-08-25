
package com.example.demo.service;

import com.example.demo.model.Notification;
import com.example.demo.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository repository;

    public NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }

    public List<Notification> getNotificationsForUser(Long userId) {
        return repository.findByUserIdOrderByCreatedDateDesc(userId);
    }

    public Notification createNotification(Notification notification) {
        return repository.save(notification);
    }

    public Notification markAsRead(Long id) {
        Notification notif = repository.findById(id).orElseThrow();
        notif.setIsRead(true);
        return repository.save(notif);
    }

    public void deleteNotification(Long id) {
        repository.deleteById(id);
    }
}
