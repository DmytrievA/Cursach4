package com.DTP.dailyTimePlaner.repos;

import com.DTP.dailyTimePlaner.XML.org.itroi.group.GroupType;
import com.DTP.dailyTimePlaner.XML.org.itroi.group.GroupUserType;
import com.DTP.dailyTimePlaner.XML.org.itroi.user.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupUserTypeRepo extends JpaRepository<GroupUserType,Integer> {
    List<GroupUserType> findByUser(String user);

    List<GroupUserType> findByGroup(Integer group);

    @Query(value = "SELECT g.name FROM " +
            "(group_user_test gu INNER JOIN group_test g ON gu.group = g.id) " +
            "INNER JOIN user_test u ON gu.user = u.email WHERE gu.user = ?1", nativeQuery = true)
    List<String> findGroupNameByUserEmail(String userEmail);
}
