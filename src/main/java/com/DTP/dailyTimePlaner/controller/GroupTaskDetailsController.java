package com.DTP.dailyTimePlaner.controller;


import com.DTP.dailyTimePlaner.domain.GivenTasks;
import com.DTP.dailyTimePlaner.domain.StatusType;
import com.DTP.dailyTimePlaner.repos.GivenTasksRepo;
import com.DTP.dailyTimePlaner.repos.StatusTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
public class GroupTaskDetailsController {
    @Autowired
    private GivenTasksRepo givenTasksRepo;
    @Autowired
    private StatusTypeRepo statusTypeRepo;


    @GetMapping("/mygrouptaskdetails")
    public String showMyPage(@RequestParam String taskId,
                           @RequestParam(required = false) String message,
                           Map<String,Object> model)
    {
        GivenTasks curTask = givenTasksRepo.findById(Integer.parseInt(taskId)).get();
        model.put("mes",message);
        model.put("task",curTask);
        return "mygrouptaskdetails";
    }

    @GetMapping("/grouptaskdetails")
    public String showPage(@RequestParam String taskId,
                           Map<String, Object> model)
    {
        GivenTasks curTask = givenTasksRepo.findById(Integer.parseInt(taskId)).get();
        model.put("task",curTask);
        return "grouptaskdetails";
    }

    @PostMapping("/grouptaskdetails")
    public String changeTask(@RequestParam String task,
                             @RequestParam String submitAction,
                             @RequestParam(required = false) String result,
                             @RequestParam(required = false) String comments,
                             @RequestParam(required = false) MultipartFile file)
    {
        GivenTasks curTask = givenTasksRepo.findById(Integer.parseInt(task)).get();
        switch (submitAction)
        {
            case "Done":
                if((result.isEmpty() || result == null) && (file==null || file.isEmpty()))
                    return "redirect:/mygrouptaskdetails?task="+task+"&message=true";
                curTask.setTaskStatus(statusTypeRepo.findByName("Готов"));
                curTask.setResult(result);
                givenTasksRepo.save(curTask);
                return "redirect:/selectedGroup";
            case "Accept":
                curTask.setTaskStatus(statusTypeRepo.findByName("В обработке"));
                givenTasksRepo.save(curTask);
                return "redirect:/usertask";
            case "Refuse":
                curTask.setTaskStatus(statusTypeRepo.findByName("Отклонен"));
                curTask.setComments(comments);
                givenTasksRepo.save(curTask);
                return "redirect:/usertask";
        }
        return "redirect:/logout";
    }
}
