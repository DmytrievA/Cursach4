<#import "parts/common.ftl" as com>

<@com.page>
<h2>Give Task to user <strong>${user}</strong></h2>
<form method="post" enctype="multipart/form-data"class="form-horizontal" role="form">
    <div class="form-group">
        <#if mes??>
            <h3 style="color: darkred">Дата конца должна быть после даты начала!</h3>
        </#if>
        <label>Дата начала: </label>
        <div class="col-sm-5">
            <input type="datetime-local" name="dateStart"/><br/>
    </div></div>
    <div class="form-group">
        <label>Дата конца: </label>
        <div class="col-sm-5">
            <input type="datetime-local" name="dateFinish"/><br/>
    </div></div>
    <div class="form-group">
        <div class="col-sm-5">
            <input type="text" name="title" placeholder="Введите название"/><br>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-5">
            <input type="text" name="description" placeholder="Введите описание"/><br>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-5">
            <input type="text" name="comments" placeholder="Введите коментарии"/><br>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-5">
            <input type="file" name="taskDock" /><br>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-8">
            <select name="taskRate" class="form-control" multiple>
                <#list states as state>
                    <option value="${state.id}">${state.name}</option>
                </#list>
            </select>
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button type="submit" class="btn btn-primary">Добавить</button>
</form>
</@com.page>