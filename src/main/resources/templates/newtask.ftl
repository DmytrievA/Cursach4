<#import "parts/common.ftl" as com>

<@com.page>
        <#list tasks as task>
            <form method="get" action="/grouptaskdetails">
            ${task.title}<br/>
            ${task.mentor.email}<br/>
                <input type="hidden" name="mentor" value="${task.mentor.email}" readonly="readonly"/><br/>
            ${task.level.name}<br/>
            ${task.date}<br/>
            ${task.finishDate}<br/>
                <input type="hidden" name="taskId" value="${task.id}"/>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="submit" value="Details" name="btnName"/>
            </form>
        </#list>
</@com.page>