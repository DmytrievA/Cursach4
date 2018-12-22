<#macro groupTask task path>
    ${task.title}<br/>
    ${task.mentor.email}<br/>
    ${task.description}<br/>
    ${task.date}<br/>
    ${task.finishDate}<br/>
    <form method="get" action="${path}">
        <input type="hidden" name="task" value="${task.id}"/>
        <input type="submit" value="Details"/>
    </form>
</#macro>