package com.DTP.dailyTimePlaner;

import com.DTP.dailyTimePlaner.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.DTP.dailyTimePlaner.domain.Task;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

@Controller
public class GreetingController {

    @Autowired
    private TaskRepo taskRepo;
    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String name, Map<String, Object> model)
    {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping
    public String main(Map<String,Object> model) {
        Iterable<Task> tasks = taskRepo.findAll();

        model.put("tasks", tasks);

        return "main";
    }

    @PostMapping
    public String add(@RequestParam String name,
                      @RequestParam Integer userID,
                      @RequestParam String description,
                      @RequestParam String taskState,
                      @RequestParam String dateTime,
                      Map<String,Object> model)
    {

        dateTime = dateTime.replace('T',' ');
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm");
        java.util.Date taskTime = new java.util.Date();
        try {
            taskTime = dateFormat.parse(dateTime);
        } catch (ParseException e) {
            dateTime = "oops";
        }
        Task task = new Task(userID,description,taskTime, name, taskState);
        taskRepo.save(task);
        Iterable<Task> tasks = taskRepo.findAll();
        model.put("tasks", tasks);
        return "greeting";
    }

}
