// package com.example.demo.repository;



// import org.springframework.data.jpa.repository.JpaRepository;
// import com.example.demo.model.Notification;

// public interface NotificationRepository extends JpaRepository<Notification, Long> {
// }
package com.example.demo.repository;

import com.example.demo.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserIdOrderByCreatedDateDesc(Long userId);
}
