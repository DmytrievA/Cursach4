<#import "parts/common.ftl" as com/>
<#import "parts/grouptaskdetails.ftl" as gtd/>


<@com.page>
    <@gtd.groupTask task/>

    <form method="post" enctype="multipart/form-data">
    <#if mes??>
        <h3 style="color: darkred">Дата конца должна быть после даты начала!</h3>
    </#if>
        <label>Дата начала: <input type="datetime-local" name="dateStart"/><br/></label>
        <label>Дата конца: <input type="datetime-local" name="dateFinish"/><br/></label>
        <input type="text" name="comments" placeholder="Введите коментарии"/><br>
        <input type="file" name="taskDock" /><br>
        <select name="taskState" class="form-control" multiple>
        <#list states as state >
            <option value="${state.id}">${state.name}</option>
        </#list>
        </select>
        <select name="taskLevel" class="form-control" multiple>
        <#list taskLevels as level>
            <option value="${level.id}">${level.name}</option>
        </#list>
        </select>
        <select name="newUser" class="form-control" multiple>
            <#list users as user>
                <option value="${user}">${user}</option>
            </#list>
        </select>
        <input type="hidden" name="task" value="${task.id}"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="submit" value="Change"/>
    <form/>
</@com.page>