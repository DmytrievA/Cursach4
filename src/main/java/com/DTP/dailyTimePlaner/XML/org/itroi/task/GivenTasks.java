package com.DTP.dailyTimePlaner.XML.org.itroi.task;

import com.DTP.dailyTimePlaner.XML.org.itroi.user.UserType;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(name = "giveTask")
public class GivenTasks extends TaskPrefab {

    @Column(name="finishDate", columnDefinition = "DATETIME")
    protected Date finishDate;

    @ManyToOne
    @JoinColumn(name="mentorEmail",
            referencedColumnName = "email",
            nullable = false)
    protected com.DTP.dailyTimePlaner.XML.org.itroi.user.UserType mentor;

    @Column(name = "result_file", columnDefinition = "TEXT")
    protected String file;

    @Column(name = "result", columnDefinition = "TEXT")
    protected String result;

    @Column(name = "comments", columnDefinition = "TEXT")
    protected String comments;

    @Column(name = "task_doc",columnDefinition = "TEXT")
    protected String taskDoc;

    @ManyToOne
    @JoinColumn(name = "level", referencedColumnName = "id",nullable = false)
    protected RateLevel level;

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
}
