package com.DTP.dailyTimePlaner.controller;

import com.DTP.dailyTimePlaner.domain.GroupType;
import com.DTP.dailyTimePlaner.domain.GroupUserType;
import com.DTP.dailyTimePlaner.domain.GroupRoleType;
import com.DTP.dailyTimePlaner.repos.GroupRoleTypeRepo;
import com.DTP.dailyTimePlaner.repos.GroupUserTypeRepo;
import com.DTP.dailyTimePlaner.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class ChangeGroupMemberController {
    @Autowired
    private GroupUserTypeRepo groupUserTypeRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private GroupRoleTypeRepo groupRoleTypeRepo;

    @GetMapping("/changegroupmember")
    public String showMemberInfo(@RequestParam String userName,
                                 Map<String,Object> model)
    {
        model.put("user",userName);
        model.put("allRoles", groupRoleTypeRepo.findAll());
        return "changegroupmember";
    }

    @PostMapping("/changegroupmember")
    public String makeAdmin(@RequestParam String user,
                            @RequestParam(required = false) String newRole,
                            HttpSession session)
    {
        if(newRole == null)
            return "redirect:/changegroupmember";
        GroupType group = (GroupType)session.getAttribute("currentGroup");
        GroupRoleType groupRoleType = groupRoleTypeRepo.findById(Integer.parseInt(newRole)).get();
        if(groupRoleType == null)
            return "redirect:/error";
        Integer groupUserTypeID = groupUserTypeRepo.findByGroup_IdAndUserEmail(group.getId(),user);
        GroupUserType groupUserType = groupUserTypeRepo.findById(groupUserTypeID).get();
        if(groupUserType == null)
            return "redirect:/error";
        groupUserType.setRole(groupRoleType);
        groupUserTypeRepo.save(groupUserType);

        return "redirect:/selectedGroup";
    }
}
