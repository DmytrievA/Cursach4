package com.DTP.dailyTimePlaner.repos;

import com.DTP.dailyTimePlaner.domain.GroupType;
import com.DTP.dailyTimePlaner.domain.GivenTasks;
import com.DTP.dailyTimePlaner.domain.StatusType;
import com.DTP.dailyTimePlaner.domain.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GivenTasksRepo extends JpaRepository<GivenTasks,Integer> {

    Integer countByMentorAndUserAndGroupAndTaskStatus
            (UserType mentor, UserType user, GroupType group, StatusType status);

    Integer countByMentorAndUserAndGroupAndTaskStatusNotIn
            (UserType mentor, UserType user, GroupType group, List<StatusType> statuses);

    List<GivenTasks> findByUserOrderByDateDesc(UserType user);
}
