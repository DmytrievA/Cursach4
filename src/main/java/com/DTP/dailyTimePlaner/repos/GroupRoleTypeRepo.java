package com.DTP.dailyTimePlaner.repos;

import com.DTP.dailyTimePlaner.XML.org.itroi.user.GroupRoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRoleTypeRepo extends JpaRepository<GroupRoleType,Integer> {

    List<GroupRoleType> findByName(String name);
}
