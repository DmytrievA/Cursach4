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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class AddGroupUserController {
    @Autowired
    private GroupUserTypeRepo groupUserTypeRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private GroupRoleTypeRepo groupRoleTypeRepo;

    @GetMapping("/addgroupuser")
    public String showUsers(HttpSession session,
                            Map<String,Object> model)
    {
        UserType user = (UserType)session.getAttribute("currentUser");
        GroupType group = (GroupType) session.getAttribute("currentGroup");
        List<String> userEmails = groupUserTypeRepo.findUserEmailNotInGroupById(group.getId(),user.getEmail());
        model.put("groupName",group.getName());
        model.put("users", userEmails);
        return "addgroupuser";
    }

    @PostMapping("/addgroupuser")
    public String addGroupUser(@RequestParam String userEmail,
                               HttpSession session)
    {
        UserType user = userRepo.findByEmail(userEmail);
        GroupRoleType groupRoleType = groupRoleTypeRepo.findByName("user");
        GroupType group = (GroupType)session.getAttribute("currentGroup");

        GroupUserType groupUserType = new GroupUserType();
        groupUserType.setUser(user);
        groupUserType.setRole(groupRoleType);
        groupUserType.setGroup(group);
        groupUserTypeRepo.save(groupUserType);

        return "redirect:/addgroupuser";
    }
}
