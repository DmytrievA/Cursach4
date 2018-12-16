package com.DTP.dailyTimePlaner.controller;

import com.DTP.dailyTimePlaner.XML.org.itroi.group.GroupType;
import com.DTP.dailyTimePlaner.XML.org.itroi.group.GroupUserType;
import com.DTP.dailyTimePlaner.XML.org.itroi.user.GroupRoleType;
import com.DTP.dailyTimePlaner.XML.org.itroi.user.UserType;
import com.DTP.dailyTimePlaner.repos.GroupRoleTypeRepo;
import com.DTP.dailyTimePlaner.repos.GroupUserTypeRepo;
import com.DTP.dailyTimePlaner.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class MakeAdminController {
    @Autowired
    private GroupUserTypeRepo groupUserTypeRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private GroupRoleTypeRepo groupRoleTypeRepo;

    @PostMapping("/makeadmin")
    public String makeAdmin(@RequestParam String userName,
                            HttpSession session)
    {
        GroupType group = (GroupType)session.getAttribute("currentGroup");
        GroupRoleType groupRoleType = groupRoleTypeRepo.findByName("admin");
        GroupUserType groupUserType = groupUserTypeRepo.findByGroup_IdAndUserEmail(group.getId(),userName);
        groupUserType.setRole(groupRoleType);
        groupUserTypeRepo.save(groupUserType);

        return "redirect:/selectedGroup";
    }
}
