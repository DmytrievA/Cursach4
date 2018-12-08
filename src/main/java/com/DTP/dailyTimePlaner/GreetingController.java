package com.DTP.dailyTimePlaner;

import com.DTP.dailyTimePlaner.XML.org.itroi.task.TaskType;
import com.DTP.dailyTimePlaner.repos.TaskRepo;
import com.DTP.dailyTimePlaner.repos.TaskTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.DTP.dailyTimePlaner.domain.Task;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
public class GreetingController {

    @Autowired
    private TaskTypeRepo taskRepo;
    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String name, Map<String, Object> model)
    {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping
    public String main(Map<String,Object> model) {
        Iterable<TaskType> tasks = taskRepo.findAll();

        model.put("tasks", tasks);

        return "main";
    }

    @PostMapping
    public String add(@RequestParam String name,
                      @RequestParam String description,
                      @RequestParam String date,
                      @RequestParam String timeFinish,
                      @RequestParam String timeStart,
                      @RequestParam Integer taskState,
                      Map<String,Object> model) throws DatatypeConfigurationException {

        date = date.replace('T',' ');
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        java.util.Date time = new Date(0,0,0);
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

        task.setTitle(name);
        task.setDescription(description);

        taskRepo.save(task);
        Iterable<TaskType> tasks = taskRepo.findAll();
        model.put("tasks", tasks);
        return "greeting";
    }

}
