<#macro groupTask task>
    <table class="table table-striped table-hover">
    <tr>
    <td>
        Название
    </td>
    <td>
    ${task.title}
    </td></tr>
    <tr>
    <td>
        Ментор
    </td>
    <td>
    ${task.mentor.email}
    </td></tr>
    <tr>
    <td>
        Группа
    </td>
    <td>
    ${task.group.name}
    </td></tr>
    <tr>
    <td>
        Описание
    </td>
    <td>
    ${task.description}
    </td></tr>
    <tr>
    <td>
        Дата начала
    </td>
    <td>
    ${task.date}
    </td></tr>
    <tr>
    <td>
        Дата завершения
    </td>
    <td>
    ${task.finishDate}
    </td></tr>
    <tr>
    <td>
        Комментарии
    </td>
    <td>
    ${task.comments}
    </td></tr>
    <tr>
    <td>
        Файл с заданием
    </td>
    <td>
    <#if task.taskDoc??>
        <a href="/uploadedfiles/${task.taskDoc}" download>FILE</a>
    <#else >
        Файл не прикреплен
    </#if>
    </td></tr style="border-bottom: darkgrey 2px solid">
    </table>
</#macro>