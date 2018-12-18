package com.DTP.dailyTimePlaner.repos;

import com.DTP.dailyTimePlaner.domain.ReminderType;
import com.DTP.dailyTimePlaner.domain.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReminderTypeRepo extends JpaRepository<ReminderType,Integer> {
    List<ReminderType> findByTaskId(Integer id);
    List<ReminderType> findByTask(TaskType task);
}
