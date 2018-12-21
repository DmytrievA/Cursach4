package com.DTP.dailyTimePlaner.repos;

import com.DTP.dailyTimePlaner.domain.GivenTasks;
import com.DTP.dailyTimePlaner.domain.GroupType;
import com.DTP.dailyTimePlaner.domain.StatusType;
import com.DTP.dailyTimePlaner.domain.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface GivenTasksRepo extends JpaRepository<GivenTasks,Integer> {

    Integer countByMentorAndUserAndGroupAndTaskStatus
            (UserType mentor, UserType user, GroupType group, StatusType status);

    Integer countByMentorAndUserAndGroupAndTaskStatusNotIn
            (UserType mentor, UserType user, GroupType group, List<StatusType> statuses);

    List<GivenTasks> findByUserOrderByDateDesc(UserType user);

    Optional<GivenTasks> findById(Integer id);

    List<GivenTasks> findByMentor(UserType mentor);

    List<GivenTasks> findByMentorAndTaskStatus(UserType mentor,StatusType status);

    @Query(value = "SELECT COUNT(gt.id) AS 'count',gt.`name` FROM (SELECT st.`name`,gt.id, gt.from_group FROM state_task st LEFT JOIN give_task gt ON st.id = gt.id_state_task) gt LEFT JOIN \n" +
            "(SELECT gu.`group` FROM group_user_test gu WHERE gu.`group`=?1) gu ON gu.`group` = gt.from_group GROUP BY gt.`name`",
            nativeQuery = true)
    List<Object[]> selectValuesForPieChart(Integer groupId);
}
