<#import "parts/common.ftl" as com>

<@com.page>
<div>
    <form method="post">
        <input type="date" name="date"/><br>
        <input type="time" name="timeStart"/><br>
        <input type="time" name="timeFinish"/><br>
        <input type="text" name="name" placeholder="Введите название"/><br>
        <input type="text" name="description" placeholder="Введите описание"/><br>
        <select name="taskState">
            <#list states as state>
            <option value="${state.id}">${state.name}</option>
            </#list>
        </select>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">Добавить</button>
    </form>
</div>

<div>
    <div>Список заданий</div>
    <table style="width: 50%; alignment: left; border: 1px solid black; border-collapse: collapse; float: left">
        <tr>
            <th colspan="2">Личные задания</th>
        </tr>
        <#list tasks as task>
            <tr>
                <td>Task Title</td>
                <td>${task.title}</td>
            </tr>
            <tr>
                <td>Description:</td>
                <td>${task.description}</td>
            </tr>
            <tr>
                <td>Date</td>
                <td>${task.date}</td>
            </tr>
            <tr>
                <td>Time start</td>
                <td>${task.time}</td>
            </tr>
            <tr>
                <td>Duration</td>
                <td>${task.duration}</td>
            </tr>
            <tr>
                <td>State</td>
                <td>${task.taskStatus.name}</td>
            </tr>
            <tr><td colspan="2">
                <form action="/mytaskdetails" method="get">
                    <input type="hidden" name="task" value="${task.id}"/>
                    <input type="submit" value="Details"/>
                </form>
            </td></tr>
        </#list>
    </table>
    <table style="width: 50%; alignment: left; border: 1px solid black; border-collapse: collapse">
        <tr>
            <th colspan="2">Групповые задания</th>
        </tr>
        <#list groupTasks as task>
            <tr>
                <td>Task Title</td>
                <td>${task.title}</td>
            </tr>
            <tr>
                <td>Description:</td>
                <td>${task.description}</td>
            </tr>
            <tr>
                <td>Date</td>
                <td>${task.date}</td>
            </tr>
            <tr>
                <td>Finish date</td>
                <td>${task.finishDate}</td>
            </tr>
            <tr>
                <td>State</td>
                <td>${task.taskStatus.name}</td>
            </tr>
            <tr>
                <td> Mentor</td>
                <td>${task.mentor.email}</td>
            </tr>
            <tr>
                <td>Level</td>
                <td>${task.level.name}</td>
            </tr>
            <tr>
                <td colspan="2">
                <form action="/mygrouptaskdetails" method="get">
                    <input type="hidden" name="task" value="${task.id}"/>
                    <input type="submit" value="Details"/>
                </form>
                </td>
            </tr>
            <#else >
            <tr>
                <td colspan="2">У вас нет групповых заданий</td>
            </tr>
        </#list>
    </table>
</div>
</@com.page>