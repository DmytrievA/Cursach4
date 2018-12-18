package com.DTP.dailyTimePlaner.repos;

import com.DTP.dailyTimePlaner.domain.GroupRoleType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRoleTypeRepo extends JpaRepository<GroupRoleType,Integer> {

    GroupRoleType findByName(String name);
}
