package com.DTP.dailyTimePlaner.repos;

import com.DTP.dailyTimePlaner.domain.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepo extends CrudRepository<Task,Long> {
}
