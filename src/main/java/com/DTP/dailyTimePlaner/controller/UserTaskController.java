package com.DTP.dailyTimePlaner.controller;

import com.DTP.dailyTimePlaner.XML.org.itroi.task.StatusType;
import com.DTP.dailyTimePlaner.XML.org.itroi.task.TaskType;
import com.DTP.dailyTimePlaner.XML.org.itroi.user.UserType;
import com.DTP.dailyTimePlaner.repos.StatusTypeRepo;
import com.DTP.dailyTimePlaner.repos.TaskTypeRepo;
import com.DTP.dailyTimePlaner.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.xml.datatype.DatatypeConfigurationException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Controller
public class UserTaskController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TaskTypeRepo taskTypeRepo;
    @Autowired
    private StatusTypeRepo statusTypeRepo;
    @GetMapping("/usertask")
    public String greeting(Map<String, Object> model,
                           final Principal principal,
                           HttpSession session)
    {
        String userName ="";
        if(null != principal)
            userName = principal.getName();

        UserType currentUser = userRepo.findByEmail(userName);
        session.setAttribute("currentUser",currentUser);


        model.put("states",statusTypeRepo.findAll());
        List<TaskType> tasks = taskTypeRepo
                .findByUserEmailOrderByDateDesc(userName);
        model.put("tasks", tasks);
        return "usertask";
    }

    @PostMapping("/usertask")
    public String addTask(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String date,
            @RequestParam String timeFinish,
            @RequestParam String timeStart,
            @RequestParam String taskState,
            HttpSession session,
            Map<String,Object> model) throws DatatypeConfigurationException {
        UserType user = (UserType) session.getAttribute("currentUser");

        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        java.util.Date time;
        TaskType task = new TaskType();
        try {
            time = dateFormat.parse(date);
            task.setDate(time);
            dateFormat = new SimpleDateFormat("hh:mm");
            time = dateFormat.parse(timeStart);
            task.setTime(time);
            time = dateFormat.parse(timeFinish);
            task.setDuration(time);
        } catch (ParseException e) {
            date = "oops";
            model.put("message", date);
            model.put("time",timeStart);
            return "greeting";
        }
        model.put("message", date);

        task.setTitle(name);
        task.setDescription(description);
        task.setTaskStatus(statusTypeRepo.findById(Integer.parseInt(taskState)).get());
        task.setUser(user);

        taskTypeRepo.save(task);
        Iterable<TaskType> tasks = taskTypeRepo.findByUserEmailOrderByDateDesc(user.getEmail());
        model.put("tasks", tasks);
        return "redirect:/usertask";
    }
}
