package com.DTP.dailyTimePlaner.repos;

import com.DTP.dailyTimePlaner.domain.StatusType;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface StatusTypeRepo extends CrudRepository<StatusType,Integer> {
    StatusType findByName(String name);

    List<StatusType> findByNameIn(Collection<String> names);
}
