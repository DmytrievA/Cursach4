package com.DTP.dailyTimePlaner.repos;

import com.DTP.dailyTimePlaner.XML.org.itroi.user.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleTypeRepo extends JpaRepository<RoleType,Integer> {
    RoleType findByName(String name);
}
