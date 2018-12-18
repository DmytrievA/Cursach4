package com.DTP.dailyTimePlaner.repos;

import com.DTP.dailyTimePlaner.domain.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleTypeRepo extends JpaRepository<RoleType,Integer> {
    RoleType findByName(String name);
}
