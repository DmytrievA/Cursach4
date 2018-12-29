<#import "parts/common.ftl" as com>
<#import "parts/mytaskdetails.ftl" as t>
<@com.page>
    <div class="mx-auto col-lg-4 col-md-4 col-sm-4">
    <@t.myTask task/>
    <form method="post">
        <input type="text" multiple="multiple" name="description"/>
        <select name = "newStatus" class="form-control" multiple>
            <#list statuses as status>
                <option value="${status.id}">${status.name}</option>
            </#list>
        </select><br/>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <#if task.taskStatus.name == "Готов" >
        <input type="date" name="date"/><br>
        <input type="time" name="timeStart"/><br>
        <input type="time" name="timeFinish"/><br>
        <input type="submit" name = "action" value="Paste"/>
        <#else >
        <input type="submit" name = "action" value="Change"/>
    </#if>
    </form>
    </div>
</@com.page>