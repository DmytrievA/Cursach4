<#macro groupTask task>
    ${task.title}<br/>
    ${task.mentor.email}<br/>
    ${task.group.name}<br/>
    ${task.description}<br/>
    ${task.date}<br/>
    ${task.finishDate}<br/>
    ${task.comments}<br/>
    <#if task.taskDoc??>
        <a href="/uploadedfiles/${task.taskDoc}" download>FILE</a>
    <#else >
        <p>Файл не прикреплен</p>
    </#if>

</#macro>