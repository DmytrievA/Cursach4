package com.DTP.dailyTimePlaner.repos;

import com.DTP.dailyTimePlaner.XML.org.itroi.task.StatusType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StatusTypeRepo extends CrudRepository<StatusType,Integer> {
    StatusType findByName(String name);
}
