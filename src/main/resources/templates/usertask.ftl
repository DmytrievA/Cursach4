<#import "parts/common.ftl" as com>


<@com.page>
<div class="row">
    <div class="col-lg-6 col-md-6 col-sm-6">
    <form method="post" class="form-horizontal" role="form">
        <div class="form-group">
            <label>Дата задания</label>
        <div class="col-sm-5">
            <input type="date" name="date"/</div></div>
        <div class="form-group">
            <label>Время начала</label>
        <div class="col-sm-5">
                <input type="time" name="timeStart"/></div></div>
        <div class="form-group">
            <label>Время конца</label>
        <div class="col-sm-5">
                <input type="time" name="timeFinish"/></div></div>
        <div class="form-group">
            <label>Название</label>
        <div class="col-sm-5">
                <input type="text" name="name" placeholder="Введите название"/></div></div>
        <div class="form-group">
            <label >Описание</label>
        <div class="col-sm-5">
                <input type="text" name="description" placeholder="Введите описание"/></div></div>
        <div class="form-group">
            <label >Текущее состояние</label>
        <div class="col-sm-8">
                <select name="taskState" class="form-control" multiple>
                    <#list states as state>
                    <option value="${state.id}">${state.name}</option>
                    </#list>
                </select></div></div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-primary">Добавить</button>
            </div></div>
    </form>
    </div>
</div>
    <div class="row">
    <div class="table-responsive col-lg-6 col-md-6 col-sm-6">
    <table class="table table-hover table-striped">
    <thead>
        <tr>
            <th colspan="2">Личные задания</th>
        </tr>
    </thead>
    <tbody>
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
    </tbody>
    </table>
    </div>
    <div class="table-responsive col-lg-6 col-md-6 col-sm-6">
    <table class="table table-hover table-striped ">
    <thead>
        <tr>
            <th colspan="2">Групповые задания</th>
        </tr>
    </thead>
    <tbody>
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
    </tbody>
    </table>
    </div>
</div>
</@com.page>