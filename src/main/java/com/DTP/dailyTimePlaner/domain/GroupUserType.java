package com.DTP.dailyTimePlaner.domain;

import com.DTP.dailyTimePlaner.repos.GivenTasksRepo;
import com.DTP.dailyTimePlaner.repos.StatusTypeRepo;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.util.LinkedList;
import java.util.List;

@javax.persistence.Entity(name = "groupUserTest")
public class GroupUserType extends Entity {

    @ManyToOne
    @JoinColumn(name = "`group`",referencedColumnName = "id",nullable = false)
    protected GroupType group;

    @ManyToOne
    @JoinColumn(name = "`user`",referencedColumnName = "email",nullable = false)
    protected UserType user;

    @ManyToOne
    @JoinColumn(name = "role",referencedColumnName = "id",nullable = false)
    protected GroupRoleType role;

    @Transient
    protected Double raiting;

    public GroupType getGroup() {
        return group;
    }

    public void setGroup(GroupType group) {
        this.group = group;
    }

    public UserType getUser() {
        return user;
    }

    public void setUser(UserType user) {
        this.user = user;
    }

    public GroupRoleType getRole() {
        return role;
    }

    public void setRole(GroupRoleType role) {
        this.role = role;
    }

    public Double getRaiting() {
        return raiting;
    }

    public Double getRaiting(UserType mentor, GivenTasksRepo givenTasksRepo, StatusTypeRepo statusTypeRepo) {
        Integer done,failed;
        done = givenTasksRepo.countByMentorAndUserAndGroupAndTaskStatus(mentor,this.user,this.group,statusTypeRepo.findByName("Гтово"));
        List<String> statuses = new LinkedList<>();
        statuses.add("Готово");
        statuses.add("Ожидает");
        statuses.add("В обработке");
        failed = givenTasksRepo.countByMentorAndUserAndGroupAndTaskStatusNotIn(mentor,this.user,this.group,statusTypeRepo.findByNameIn(statuses));
        if(failed == 0d)
            failed = done;
        raiting = done.doubleValue()/failed.doubleValue();
        if(raiting.isNaN())
            raiting = 0d;
        return raiting;
    }

    public void setRaiting(Double raiting) {
        this.raiting = raiting;
    }
}
