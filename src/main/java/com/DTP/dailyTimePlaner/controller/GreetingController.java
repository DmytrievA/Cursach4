package com.DTP.dailyTimePlaner.controller;

import com.DTP.dailyTimePlaner.XML.org.itroi.task.TaskType;
import com.DTP.dailyTimePlaner.XML.org.itroi.user.UserType;
import com.DTP.dailyTimePlaner.repos.StatusTypeRepo;
import com.DTP.dailyTimePlaner.repos.TaskTypeRepo;
import com.DTP.dailyTimePlaner.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.datatype.DatatypeConfigurationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Controller
public class GreetingController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TaskTypeRepo taskTypeRepo;
    @Autowired
    private StatusTypeRepo statusTypeRepo;
    @GetMapping("/greeting")
    public String greeting(Map<String, Object> model)
    {
        List<TaskType> tasks = taskTypeRepo
                .findByUserEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        model.put("tasks", tasks);
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String,Object> model) {

        return "main";
    }

    @PostMapping("/greeting")
    public String add(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String date,
            @RequestParam String timeFinish,
            @RequestParam String timeStart,
            @RequestParam String taskState,
            Map<String,Object> model) throws DatatypeConfigurationException {
        date = date.replace('T',' ');
        UserType user;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            user = userRepo.findByEmail(currentUserName);
        }
        else
            return "redirect:/error";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
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
        task.setTaskState(statusTypeRepo.findById(Integer.parseInt(taskState)).get());
        task.setUser(user);

        taskTypeRepo.save(task);
        Iterable<TaskType> tasks = taskTypeRepo.findByUserEmail(user.getEmail());
        model.put("tasks", tasks);
        return "greeting";
    }

    @RequestMapping(value = {"", "/", "index.html"})
    public String index(@AuthenticationPrincipal UserType user) {
        return "index";
    }

}
