package com.DTP.dailyTimePlaner.repos;

import com.DTP.dailyTimePlaner.XML.org.itroi.group.GroupType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupTypeRepo extends JpaRepository<GroupType,Integer> {
    List<GroupType> findByNameIn(List<String> name);

}
