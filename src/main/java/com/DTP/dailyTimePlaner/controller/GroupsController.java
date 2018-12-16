package com.DTP.dailyTimePlaner.controller;

import com.DTP.dailyTimePlaner.XML.org.itroi.group.GroupType;
import com.DTP.dailyTimePlaner.XML.org.itroi.group.GroupUserType;
import com.DTP.dailyTimePlaner.repos.GroupTypeRepo;
import com.DTP.dailyTimePlaner.repos.GroupUserTypeRepo;
import com.DTP.dailyTimePlaner.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
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
    public String allGroups(Map<String,Object> model,
                            final Principal principal,
                            HttpSession session)
    {
        if(session.getAttribute("currentGroup")!=null)
            session.removeAttribute("currentGroup");
        if(principal ==null)
            return "groups";
        String userName = principal.getName();

        List<String> groupNames = groupUserTypeRepo.findGroupNameByUserEmail(userName);
        List<GroupType> groups = groupTypeRepo.findByNameIn(groupNames);
        model.put("groups",groups);

        return "groups";
    }

    @Transactional
    @PostMapping("/groups")
    public String addGroup(Map<String,Object> model){
        return "groups";
    }

    @GetMapping("/selectedGroup")
    public String showGroupInfo(@RequestParam(required = false) String groupId,
                                @RequestParam(required = false) String groupName,
                                final Principal principal,
                                HttpSession session,
                                Map<String,Object> model)
    {
        GroupType group = (GroupType)session.getAttribute("currentGroup");
        if(group ==null) {
            group = new GroupType();
            group.setName(groupName);
            group.setId(Integer.parseInt(groupId));
            session.setAttribute("currentGroup",group);
        }

        if(principal ==null)
            return "redirect:/logout";

        String userName = principal.getName();
        String userRole = groupUserTypeRepo.findUserGroupRole(group.getId(),userName);
        boolean admin = "admin".equals(userRole);
        model.put("admin",admin);
        List<GroupUserType> users = groupUserTypeRepo.findByGroup_Id(group.getId());
        model.put("users",users);
        return "selectedGroup";
    }

    @PostMapping("/selectedGroup")
    public String deleteUser(@RequestParam String userName,
                             HttpSession session,
                             Map<String,Object> model)
    {
        GroupType group = (GroupType)session.getAttribute("currentGroup");
        model.put("currentGroupName",group.getName());
        groupUserTypeRepo.deleteByUserEmailAndGroupId(userName,group.getId());
        return "redirect:/selectedGroup";
    }
}
