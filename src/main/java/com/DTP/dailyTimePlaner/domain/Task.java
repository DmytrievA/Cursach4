package com.DTP.dailyTimePlaner.domain;


import javax.persistence.*;
import java.util.Date;

@Entity(name="tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_task")
    private Integer idTask;
    @Column(name="users_id_user")
    private Integer userID;
    @Column(name="description")
    private String description;
    @Column(name="date_time")
    private Date dateTime;
    @Column(name="name")
    private String name;
    @Column(name="state_task_id_state_task")
    private String taskState;

    public Task() {
    }

    public Task(Integer userID, String description, Date dateTime, String name, String taskState) {
        this.userID = userID;
        this.description = description;
        this.dateTime = dateTime;
        this.name = name;
        this.taskState = taskState;
    }

    public Integer getIdTask() {
        return idTask;
    }

    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaskState() {
        return taskState;
    }

    public void setTaskState(String taskState) {
        this.taskState = taskState;
    }
}
