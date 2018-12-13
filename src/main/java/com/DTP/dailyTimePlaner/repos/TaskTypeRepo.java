package com.DTP.dailyTimePlaner.repos;

import com.DTP.dailyTimePlaner.XML.org.itroi.task.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskTypeRepo extends JpaRepository<TaskType, Integer> {

    List<TaskType> findByUserEmail(String id);

}
