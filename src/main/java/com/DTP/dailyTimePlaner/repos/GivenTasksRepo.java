package com.DTP.dailyTimePlaner.repos;

import com.DTP.dailyTimePlaner.XML.org.itroi.task.GivenTasks;
import com.DTP.dailyTimePlaner.XML.org.itroi.user.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GivenTasksRepo extends JpaRepository<GivenTasks,Integer> {

    List<GivenTasks> findByUserOrderByDateDesc(UserType user);
}
