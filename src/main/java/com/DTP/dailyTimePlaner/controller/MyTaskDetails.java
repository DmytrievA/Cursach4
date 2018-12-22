package com.DTP.dailyTimePlaner.controller;

import com.DTP.dailyTimePlaner.domain.TaskType;
import com.DTP.dailyTimePlaner.repos.StatusTypeRepo;
import com.DTP.dailyTimePlaner.repos.TaskTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.Map;

@Controller
public class MyTaskDetails {
    @Autowired
    private TaskTypeRepo taskTypeRepo;
    @Autowired
    private StatusTypeRepo statusTypeRepo;

    @GetMapping("/mytaskdetails")
    public String showPage(@RequestParam String task,
                           Map<String,Object> model)
    {
        model.put("task",taskTypeRepo.findById(Integer.parseInt(task)).get());
        model.put("statuses",statusTypeRepo.findAll());
        return "mytaskdetails";
    }

    @PostMapping("/mytaskdetails")
    public String changeTask(@RequestParam String task,
                             @RequestParam String newStatus,
                             @RequestParam String description,
                             @RequestParam String action,
                             @RequestParam(required = false) String date,
                             @RequestParam(required = false) String timeFinish,
                             @RequestParam(required = false) String timeStart) throws ParseException {
        TaskType curTask = taskTypeRepo.findById(Integer.parseInt(task)).get();
        curTask.setDescription(description);
        curTask.setTaskStatus(statusTypeRepo.findById(Integer.parseInt(newStatus)).get());
        if(action.equals("Paste"))
        {
            curTask.setDateTimeDuration(date,timeStart,timeFinish);
        }
        taskTypeRepo.save(curTask);
        return "redirect:/usertask";
    }
}
