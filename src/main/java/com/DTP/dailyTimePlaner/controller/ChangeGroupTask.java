package com.DTP.dailyTimePlaner.controller;

import com.DTP.dailyTimePlaner.domain.GivenTasks;
import com.DTP.dailyTimePlaner.domain.GroupType;
import com.DTP.dailyTimePlaner.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.UUID;

import static com.DTP.dailyTimePlaner.controller.GiveTaskController.getFileName;

@Controller
public class ChangeGroupTask {
    @Value("${upload.path}")
    private String path;
    @Autowired
    GroupUserTypeRepo groupUserTypeRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    StatusTypeRepo statusTypeRepo;
    @Autowired
    GivenTasksRepo givenTasksRepo;
    @Autowired
    RateLevelRepo rateLevelRepo;

    @GetMapping("/changegrouptask")
    public String showPage(@RequestParam String taskId,
                           @RequestParam(required = false) String mes,
                           HttpSession session,
                           Map<String,Object> model)
    {
        model.put("mes",mes);
        GivenTasks task = givenTasksRepo.findById(Integer.parseInt(taskId)).get();
        model.put("task",givenTasksRepo.findById(Integer.parseInt(taskId)).get());
        model.put("states",statusTypeRepo.findAll());
        model.put("taskLevels",rateLevelRepo.findAll());
        model.put("users",groupUserTypeRepo.findUserEmailByGroupId(
                ((GroupType)session.getAttribute("currentGroup")).getId()));
        return "changegrouptask";
    }

    @PostMapping("/changegrouptask")
    public String changeTask(@RequestParam String taskId,
                             @RequestParam String dateStart,
                             @RequestParam String dateFinish,
                             @RequestParam String newUser,
                             @RequestParam String taskLevel,
                             @RequestParam String taskState,
                             @RequestParam String comments,
                             @RequestParam(required = false) MultipartFile taskDock) throws IOException, ParseException {
        GivenTasks task = givenTasksRepo.findById(Integer.parseInt(taskId)).get();
        task.setDate((new SimpleDateFormat("yyyy-MM-dd'T'hh:mm")).parse(dateStart));
        task.setFinishDate((new SimpleDateFormat("yyyy-MM-dd'T'hh:mm")).parse(dateFinish));
        if(task.getFinishDate().before(task.getDate())|| dateStart.equals(dateFinish))
            return "redirect:/changegrouptask?taskId="+taskId+"&mes=true";
        addFileToGroupTask(taskDock, task, path);
        task.setUser(userRepo.findByEmail(newUser));
        task.setLevel(rateLevelRepo.findById(Integer.parseInt(taskLevel)).get());
        task.setTaskStatus(statusTypeRepo.findById(Integer.parseInt(taskState)).get());
        task.setComments(comments);
        givenTasksRepo.save(task);
        return "redirect:/selectedGroup";
    }

    static void addFileToGroupTask( MultipartFile taskDock, GivenTasks task, String path) throws IOException {
        if(taskDock != null && !taskDock.getOriginalFilename().isEmpty())
        {
            File uploadDir = new File(path);
            if(!uploadDir.exists())
                uploadDir.mkdir();
            String namePrefix = UUID.randomUUID().toString();
            String resultFileName = namePrefix + "." +
                    getFileName(taskDock.getOriginalFilename());

            taskDock.transferTo(new File(path + "/" + resultFileName));
            task.setTaskDoc(resultFileName);
        }
    }
}
