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

    List<GivenTasks> findByUserAndTaskStatusNameOrderByDateDesc(UserType user,String statusName );

    Optional<GivenTasks> findById(Integer id);

    List<GivenTasks> findByMentor(UserType mentor);

    List<GivenTasks> findByMentorAndTaskStatus(UserType mentor,StatusType status);

    @Query(value = "SELECT COUNT(gt.id),st.`name` FROM state_task st LEFT JOIN (SELECT gt.id, gt.id_state_task FROM give_task gt WHERE gt.from_group = ?1) gt ON st.id = gt.id_state_task GROUP BY st.id",
            nativeQuery = true)
    List<Object[]> selectValuesForPieChart(Integer groupId);

    List<GivenTasks> findByGroupIdAndTaskStatusName(Integer groupId,String status);

    List<GivenTasks> findByGroupIdAndUserEmailAndTaskStatusName(Integer groupId,String email, String status);
}
