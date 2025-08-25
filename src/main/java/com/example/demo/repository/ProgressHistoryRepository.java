
package com.example.demo.repository;

import com.example.demo.model.ProgressHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ProgressHistoryRepository extends JpaRepository<ProgressHistory, Long> {
 List<ProgressHistory> findByUserId(Long userId);

    List<ProgressHistory> findByUserIdAndDateAfter(Long userId, LocalDate date);
}
