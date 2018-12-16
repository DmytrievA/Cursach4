package com.DTP.dailyTimePlaner.XML.org.itroi.group;

import com.DTP.dailyTimePlaner.XML.org.itroi.entity.Entity;
import com.DTP.dailyTimePlaner.XML.org.itroi.user.GroupRoleType;
import com.DTP.dailyTimePlaner.XML.org.itroi.user.UserType;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
}
