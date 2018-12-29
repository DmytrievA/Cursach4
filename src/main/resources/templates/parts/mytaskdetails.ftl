<#macro myTask task>

    <table class="table table-striped table-hover">
    <tr>
    <td>
        Название
    </td>
    <td>
    ${task.title}
    </td></tr>
    <td>
        Описание
    </td>
    <td>
    ${task.description}
    </td></tr>
    <tr>
    <td>
        День
    </td>
    <td>
    ${task.date}
    </td></tr>
    <tr>
    <td>
        Время
    </td>
    <td>
    ${task.time}
    </td></tr>
    <tr>
        <td>Продолжительность</td>
        <td>${task.duration}</td>
    </tr>
    </table>

</#macro>