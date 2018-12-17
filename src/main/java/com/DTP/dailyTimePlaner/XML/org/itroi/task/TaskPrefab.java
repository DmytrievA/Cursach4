package com.DTP.dailyTimePlaner.XML.org.itroi.task;

import com.DTP.dailyTimePlaner.XML.org.itroi.entity.Entity;
import com.DTP.dailyTimePlaner.XML.org.itroi.user.UserType;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class TaskPrefab extends Entity
{
    @Column(name = "TASK_TITLE",
            nullable = false,
            columnDefinition = "VARCHAR(250)")
    protected String title;

    @Column(name = "TASK_DATE",
            nullable = false,
            columnDefinition = "DATE")
    protected Date date;

    @Column(name = "TASK_DESCRIPTION",
            columnDefinition = "TEXT",
            nullable = false)
    protected String description;

    @ManyToOne
    @JoinColumn(name = "id_state_task",
            referencedColumnName = "id",
            nullable = false)
    protected StatusType taskStatus;


    @ManyToOne
    @JoinColumn(name="userEmail",
            referencedColumnName = "email",
            nullable = false)
    protected com.DTP.dailyTimePlaner.XML.org.itroi.user.UserType user;

    @Transient
    protected RemindersType reminders;

    public String getTitle() {
    return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusType getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(StatusType taskStatus) {
        this.taskStatus = taskStatus;
    }

    public UserType getUser() {
        return user;
    }

    public void setUser(UserType user) {
        this.user = user;
    }

    public RemindersType getReminders() {
        return reminders;
    }

    public void setReminders(RemindersType reminders) {
        this.reminders = reminders;
    }
}
