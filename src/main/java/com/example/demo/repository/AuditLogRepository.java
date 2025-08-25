// AuditLogRepository.java
package com.example.demo.repository;

import com.example.demo.model.AuditLog;

// Correct import for Spring Data JPA
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
 
    Page<AuditLog> findByActionContainingIgnoreCase(String action, Pageable pageable);


}
