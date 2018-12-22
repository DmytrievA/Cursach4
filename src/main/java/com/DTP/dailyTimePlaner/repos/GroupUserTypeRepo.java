package com.DTP.dailyTimePlaner.repos;

import com.DTP.dailyTimePlaner.domain.GroupUserType;
import com.DTP.dailyTimePlaner.domain.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface GroupUserTypeRepo extends JpaRepository<GroupUserType,Integer> {
    List<GroupUserType> findByUser(String user);

    List<GroupUserType> findByGroup_Id(Integer group);

    @Query(value = "SELECT gu.id FROM group_user_test AS gu WHERE gu.user=?2 AND gu.group=?1",nativeQuery = true)
    Integer findByGroup_IdAndUserEmail(Integer id, String email);

    @Query(value = "SELECT g.name FROM " +
            "(group_user_test gu INNER JOIN group_test g ON gu.group = g.id) " +
            "INNER JOIN user_test u ON gu.user = u.email WHERE gu.user = ?1", nativeQuery = true)
    List<String> findGroupNameByUserEmail(String userEmail);

    @Query(value = "SELECT gu.user FROM group_user_test gu WHERE gu.group = ?1", nativeQuery = true)
    List<String> findUsersByGroupId(Integer groupId);

    @Query(value = "SELECT gr.name FROM group_role AS gr LEFT JOIN group_user_test AS gu ON gr.id = gu.role " +
            "WHERE gu.group = ?1 AND gu.user = ?2", nativeQuery = true)
    String findUserGroupRole(Integer groupId, String userName);

    @Query(value = "SELECT u.email FROM group_user_test gu LEFT JOIN user_test u ON u.email = gu.user " +
            "WHERE gu.group = ?1", nativeQuery = true)
    List<String> findUserEmailByGroupId(Integer Id);

    @Query(value = "SELECT u.email FROM user_test u " +
            "WHERE u.email NOT IN (SELECT gr.user FROM group_user_test gr WHERE gr.group = ?1) " +
            "AND u.email <> ?2", nativeQuery = true)
    List<String> findUserEmailNotInGroupById(Integer Id,String userEmail);

    @Transactional
    void deleteByUserEmailAndGroupId(String userName, Integer groupId);
}
