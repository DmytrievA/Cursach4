package com.DTP.dailyTimePlaner.controller;

import com.DTP.dailyTimePlaner.domain.GroupType;
import com.DTP.dailyTimePlaner.domain.GroupUserType;
import com.DTP.dailyTimePlaner.domain.UserType;
import com.DTP.dailyTimePlaner.repos.*;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.security.Principal;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
public class GroupsController {

    @Autowired
    private GroupTypeRepo groupTypeRepo;
    @Autowired
    private GroupUserTypeRepo groupUserTypeRepo;
    @Autowired
    private GivenTasksRepo givenTasksRepo;
    @Autowired
    private StatusTypeRepo statusTypeRepo;

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
        if(group ==null || (groupId!=null && group.getId() != Integer.parseInt(groupId))) {
            group = new GroupType();
            group.setName(groupName);
            group.setId(Integer.parseInt(groupId));
            session.setAttribute("currentGroup",group);
        }
        model.put("currentGroupName",group.getName());
        if(principal ==null)
            return "redirect:/logout";
        List<Object[]> info = givenTasksRepo.selectValuesForPieChart(Integer.parseInt(groupId));
        model.put("data", showStatistik(info,model));
        String userName = principal.getName();
        String userRole = groupUserTypeRepo.findUserGroupRole(group.getId(),userName);
        model.put("userRole",userRole);
        if("admin".equals(userRole))
            showAdminData(model,group.getId());
        else
            showUserData(model,group.getId(),userName);
        List<GroupUserType> users = groupUserTypeRepo.findByGroup_Id(group.getId());
        for (GroupUserType user:
                users) {
            user.getRaiting((UserType)session.getAttribute("currentUser"),givenTasksRepo,statusTypeRepo);
        }
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

    @PostMapping("/leavegroup")
    public String leveGroup(@RequestParam String groupId,
                            final Principal principal)
    {
        if(principal.getName().isEmpty())
            return "redirect:/logout";
        String userName = principal.getName();
        groupUserTypeRepo.deleteByUserEmailAndGroupId(userName,Integer.parseInt(groupId));
        return "redirect:/groups";
    }

    private List<Pair<Double, String>> showStatistik(List<Object[]> obj, Map<String,Object> model) {
        LinkedList<Pair<Double, String>> res = new LinkedList<>();
        Double sum = 1d;
        for (int i=0;i<obj.size();i++){
            res.add(new Pair<>(Double.parseDouble(obj.get(i)[0].toString()),obj.get(i)[1].toString()));
            model.put("percents",obj);
            sum+=res.get(i).getKey();
        }
        DecimalFormat decimalFormat = new DecimalFormat(".");
        for (int i=0;i<obj.size();i++) {
            res.set(i, new Pair<>(Double.parseDouble(decimalFormat.format(res.get(i).getKey()*100d/sum))
                    ,res.get(i).getValue()));
        }
        return res;
    }

    private void showAdminData(Map<String,Object> model, Integer groupId)
    {
        model.put("waiting",givenTasksRepo.findByGroupIdAndTaskStatusName(groupId,"Ожидает"));
        model.put("processing",givenTasksRepo.findByGroupIdAndTaskStatusName(groupId,"В обработке"));
        model.put("done",givenTasksRepo.findByGroupIdAndTaskStatusName(groupId,"Готов"));
        model.put("refused",givenTasksRepo.findByGroupIdAndTaskStatusName(groupId,"Отклонен"));
        model.put("failed",givenTasksRepo.findByGroupIdAndTaskStatusName(groupId,"Провален"));
    }

    private void showUserData(Map<String,Object> model, Integer groupId,String email)
    {
        model.put("waiting",givenTasksRepo.findByGroupIdAndUserEmailAndTaskStatusName(groupId,"Ожидает",email));
        model.put("processing",givenTasksRepo.findByGroupIdAndUserEmailAndTaskStatusName(groupId,"В обработке",email));
        model.put("done",givenTasksRepo.findByGroupIdAndUserEmailAndTaskStatusName(groupId,"Готов",email));
        model.put("refused",givenTasksRepo.findByGroupIdAndUserEmailAndTaskStatusName(groupId,"Отклонен",email));
        model.put("failed",givenTasksRepo.findByGroupIdAndUserEmailAndTaskStatusName(groupId,"Провален",email));
    }
}
