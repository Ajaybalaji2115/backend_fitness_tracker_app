
package com.example.demo.service;

import com.example.demo.model.ProgressHistory;
import com.example.demo.repository.ProgressHistoryRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProgressHistoryService {

    private final ProgressHistoryRepository repository;

    public ProgressHistoryService(ProgressHistoryRepository repository) {
        this.repository = repository;
    }

    public List<ProgressHistory> getProgressByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    public List<ProgressHistory> getProgressByUserIdAndDays(Long userId, Integer days) {
        if (days != null && days > 0) {
            LocalDate cutoffDate = LocalDate.now().minusDays(days);
            return repository.findByUserIdAndDateAfter(userId, cutoffDate);
        }
        return repository.findByUserId(userId);
    }

    public ProgressHistory addProgress(ProgressHistory progress) {
        return repository.save(progress);
    }
    public Optional<ProgressHistory> getProgressById(Long historyId) {
    return repository.findById(historyId);
}

}
