package com.DTP.dailyTimePlaner.controller;

import com.DTP.dailyTimePlaner.XML.org.itroi.group.GroupType;
import com.DTP.dailyTimePlaner.XML.org.itroi.group.GroupUserType;
import com.DTP.dailyTimePlaner.repos.GroupRoleTypeRepo;
import com.DTP.dailyTimePlaner.repos.GroupTypeRepo;
import com.DTP.dailyTimePlaner.repos.GroupUserTypeRepo;
import com.DTP.dailyTimePlaner.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Map;

@Controller
public class NewGroupController {
    @Autowired
    GroupTypeRepo groupTypeRepo;
    @Autowired
    GroupUserTypeRepo groupUserTypeRepo;
    @Autowired
    GroupRoleTypeRepo roleTypeRepo;
    @Autowired
    UserRepo userRepo;

    @GetMapping("/newgroup")
    public String newGroup(){
        return "newgroup";
    }

    @PostMapping("/newgroup")
    public String addGroup(@RequestParam String name,
                           final Principal principal)
    {
        if(null == principal)
            return "redirect:/error";

        GroupType group = new GroupType();
        group.setName(name);
        groupTypeRepo.save(group);
        GroupUserType groupUser = new GroupUserType();
        groupUser.setGroup(group);
        groupUser.setRole(roleTypeRepo.findByName("admin"));
        groupUser.setUser(userRepo.findByEmail(principal.getName()));

        groupUserTypeRepo.save(groupUser);
        return "redirect:/groups";
    }
}
