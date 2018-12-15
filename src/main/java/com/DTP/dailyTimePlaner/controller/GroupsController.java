package com.DTP.dailyTimePlaner.controller;

import com.DTP.dailyTimePlaner.XML.org.itroi.group.GroupType;
import com.DTP.dailyTimePlaner.XML.org.itroi.group.GroupUserType;
import com.DTP.dailyTimePlaner.XML.org.itroi.user.UserType;
import com.DTP.dailyTimePlaner.repos.GroupTypeRepo;
import com.DTP.dailyTimePlaner.repos.GroupUserTypeRepo;
import com.DTP.dailyTimePlaner.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
public class GroupsController {

    @Autowired
    private GroupTypeRepo groupTypeRepo;

    @Autowired
    private GroupUserTypeRepo groupUserTypeRepo;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/groups")
    public String allGroups(Map<String,Object> model, final Principal principal){
        if(principal ==null)
            return "groups";
        String userName = principal.getName();

        List<String> groupNames = groupUserTypeRepo.findGroupNameByUserEmail(userName);
        List<GroupType> groups = groupTypeRepo.findByNameIn(groupNames);
        model.put("groups",groups);

        return "groups";
    }

    @PostMapping("/groups")
    public String showGroupInfo(@RequestParam String groupId,
                                Map<String,Object> model)
    {
        List<String> userEmails = groupUserTypeRepo.findUsersByGroupId(Integer.parseInt(groupId));
        List<UserType> users = userRepo.findByEmailIn(userEmails);
        model.put("users",userEmails);
        return "groups";
    }
}
