package com.DTP.dailyTimePlaner.domain;

import javax.persistence.*;
import javax.persistence.Entity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(name = "give_task")
public class GivenTasks extends TaskPrefab {

    @Column(name="finishDate", columnDefinition = "DATETIME")
    protected Date finishDate;

    @ManyToOne
    @JoinColumn(name="mentorEmail",
            referencedColumnName = "email",
            nullable = false)
    protected UserType mentor;

    @Transient
    @Column(name = "result_file", columnDefinition = "TEXT")
    protected String file;

    @Column(name = "result", columnDefinition = "TEXT")
    protected String result;

    @Column(name = "comments", columnDefinition = "TEXT")
    protected String comments;

    @Column(name = "task_doc",columnDefinition = "TEXT")
    protected String taskDoc;

    @ManyToOne
    @JoinColumn(name = "level", referencedColumnName = "id", nullable = false)
    protected RateLevel level;

    @ManyToOne
    @JoinColumn(name = "from_group", referencedColumnName = "id" , nullable = false)
    protected GroupType group;

    public GivenTasks() {
    }

    public UserType getMentor() {
        return mentor;
    }

    public void setMentor(UserType mentor) {
        this.mentor = mentor;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public void setFinishTime(String finishTime) throws ParseException {
        this.finishDate = (new SimpleDateFormat("hh:mm")).parse(finishTime);
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getTaskDoc() {
        return taskDoc;
    }

    public void setTaskDoc(String taskDoc) {
        this.taskDoc = taskDoc;
    }

    public RateLevel getLevel() {
        return level;
    }

    public void setLevel(RateLevel level) {
        this.level = level;
    }

    public GroupType getGroup() {
        return group;
    }

    public void setGroup(GroupType group) {
        this.group = group;
    }
}
