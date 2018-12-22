package com.DTP.dailyTimePlaner.controller;

import com.DTP.dailyTimePlaner.domain.GivenTasks;
import com.DTP.dailyTimePlaner.domain.UserType;
import com.DTP.dailyTimePlaner.repos.GivenTasksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class NewTaskController {

    @Autowired
    private GivenTasksRepo givenTasksRepo;

    @GetMapping("/newtask")
    public String showPage(HttpSession session,
                           Map<String,Object> model)
    {
        UserType currentUser = (UserType)session.getAttribute("currentUser");
        List<GivenTasks> tasks = givenTasksRepo.findByUserAndTaskStatusNameOrderByDateDesc(currentUser,"Ожидает");
        model.put("tasks",tasks);
        return "newtask";
    }
}
