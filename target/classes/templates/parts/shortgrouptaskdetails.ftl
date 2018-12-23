<#macro groupTask task path>
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
        <tr style="border-bottom: darkgrey 2px solid">
    </table>
    <form method="get" action="${path}">
        <input type="hidden" name="taskId" value="${task.id}"/>
        <button type="submit" class="btn btn-primary">Details</button>
    </form>
</#macro>