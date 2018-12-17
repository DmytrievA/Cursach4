package com.DTP.dailyTimePlaner.controller;


import com.DTP.dailyTimePlaner.XML.org.itroi.task.GivenTasks;
import com.DTP.dailyTimePlaner.XML.org.itroi.user.UserType;
import com.DTP.dailyTimePlaner.repos.GivenTasksRepo;
import com.DTP.dailyTimePlaner.repos.RateLevelRepo;
import com.DTP.dailyTimePlaner.repos.StatusTypeRepo;
import com.DTP.dailyTimePlaner.repos.UserRepo;
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

@Controller
public class GiveTaskController {

    @Value("${upload.path}")
    private String path;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RateLevelRepo levelRepo;
    @Autowired
    private StatusTypeRepo statusTypeRepo;
    @Autowired
    private GivenTasksRepo givenTasksRepo;

    @GetMapping("/givetask")
    public String showPage(@RequestParam String userName,
                           HttpSession session,
                           Map<String,Object> model)
    {
        session.setAttribute("user",userRepo.findByEmail(userName));
        model.put("states", levelRepo.findAll());
        model.put("user",userName);
        return "givetask";
    }

    @PostMapping("/givetask")
    public String addTask(@RequestParam String dateStart,
                          @RequestParam String dateFinish,
                          @RequestParam String Title,
                          @RequestParam String description,
                          @RequestParam String comments,
                          @RequestParam String taskRate,
                          @RequestParam(required = false) MultipartFile taskDock,
                          HttpSession session) throws IOException, ParseException {
        GivenTasks newTask = new GivenTasks();
        //dateStart = dateStart.replace('T', ' ');
        //dateFinish = dateFinish.replace('T',' ');
        if(taskDock != null && !taskDock.getOriginalFilename().isEmpty())
        {
            File uploadDir = new File(path);
            if(!uploadDir.exists())
                uploadDir.mkdir();
            String namePrefix = UUID.randomUUID().toString();
            String resultFileName = namePrefix + "." +
                    getFileName(taskDock.getOriginalFilename());

            taskDock.transferTo(new File(path + "/" + resultFileName));
            newTask.setTaskDoc(resultFileName);
        }
        newTask.setDate((new SimpleDateFormat("YYYY-MM-dd'T'hh:mm")).parse(dateStart));
        newTask.setFinishDate((new SimpleDateFormat("YYYY-MM-dd'T'hh:mm")).parse(dateFinish));
        newTask.setTitle(Title);
        newTask.setDescription(description);
        newTask.setMentor((UserType)session.getAttribute("currentUser"));
        newTask.setComments(comments);
        newTask.setLevel(levelRepo.findById(Integer.parseInt(taskRate)).get());
        newTask.setUser((UserType)session.getAttribute("user"));
        newTask.setTaskStatus(statusTypeRepo.findByName("w"));
        givenTasksRepo.save(newTask);
        return "redirect:/selectedGroup";
    }

    private String getFileName(String sourseName)
    {
        int point = sourseName.lastIndexOf('\\');
        return sourseName.substring(point+1);
    }
}