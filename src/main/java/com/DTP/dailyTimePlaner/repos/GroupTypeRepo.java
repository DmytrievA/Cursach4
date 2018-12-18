package com.DTP.dailyTimePlaner.repos;

import com.DTP.dailyTimePlaner.domain.GroupType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupTypeRepo extends JpaRepository<GroupType,Integer> {
    List<GroupType> findByNameIn(List<String> name);

    GroupType findFirstByNameOrderByIdDesc(String name);
}
