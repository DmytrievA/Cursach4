package com.DTP.dailyTimePlaner.repos;

import com.DTP.dailyTimePlaner.domain.Mesage;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Mesage,Long> {
}
