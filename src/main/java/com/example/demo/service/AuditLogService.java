// AuditLogService.java
package com.example.demo.service;

import com.example.demo.model.AuditLog;
import com.example.demo.repository.AuditLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditLogService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    // Log an action
    public void log(String action, String performedBy) {
        AuditLog auditLog = AuditLog.builder()
                .action(action)
                .performedBy(performedBy)
                .timestamp(LocalDateTime.now())
                .build();
        auditLogRepository.save(auditLog);
    }

    // Retrieve all logs (Admin only)
    public List<AuditLog> getAllLogs() {
        return auditLogRepository.findAll();
    }
        public Page<AuditLog> getLogs(String actionFilter, Pageable pageable) {
        if (actionFilter != null && !actionFilter.isEmpty()) {
            return auditLogRepository.findByActionContainingIgnoreCase(actionFilter, pageable);
        }
        return auditLogRepository.findAll(pageable);
    }

}
