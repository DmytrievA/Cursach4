package com.DTP.dailyTimePlaner.domain;

import javax.persistence.*;

@javax.persistence.Entity(name = "reminders")
public class ReminderType
extends Entity {

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "task", referencedColumnName = "id", nullable = false)
    protected TaskType task;

    public TaskType getTask() {
        return task;
    }

    public void setTask(TaskType task) {
        this.task = task;
    }
}
