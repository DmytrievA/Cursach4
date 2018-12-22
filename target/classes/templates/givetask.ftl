<#import "parts/common.ftl" as com>

<@com.page>
<h2>Give Task to user <strong>${user}</strong></h2>
<form method="post" enctype="multipart/form-data">
    <label>Дата начала: <input type="datetime-local" name="dateStart"/><br/></label>
    <label>Дата конца: <input type="datetime-local" name="dateFinish"/><br/></label>
    <input type="text" name="Title" placeholder="Введите название"/><br>
    <input type="text" name="description" placeholder="Введите описание"/><br>
    <input type="text" name="comments" placeholder="Введите коментарии"/><br>
    <input type="file" name="taskDock" /><br>
    <select name="taskRate">
        <#list states as state>
            <option value="${state.id}}">${state.name}</option>
        </#list>
    </select>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button type="submit">Добавить</button>
</form>
</@com.page>