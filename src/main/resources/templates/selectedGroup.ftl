<#import "parts/common.ftl" as com>
<#import "parts/shortgrouptaskdetails.ftl" as sgtd>

<@com.page>
<div style="width: 50%; float: right">
    <figure>
        <figcaption>Статистика заданий по группе</figcaption>
        <#list data as row>
        <svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" class="chart" width="420" height="20" aria-labelledby="title" role="img">
            <title id="title">Статистика заданий по группе</title>
                <g class="bar">
                    <rect width=" ${row.getKey()}%" height="19"></rect>
                </g>

        </svg><label>${row.getKey()}% ${row.getValue()}</label>
        </#list>
    </figure>
</div>

        <h3>${currentGroupName?if_exists}</h3><br>
    <#if userRole == "admin">
        <a href="/addgroupuser">Add new user</a>
    </#if><br/>
    <#list users as user>
        <strong>${user.role.name}   ${user.raiting}</strong><br>
        <form method="post">
            ${user.user.email}<br>
            <input type="hidden" name="userName", value="${user.user.email}" readonly="readonly"/>

            <#if userRole == "admin">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="submit" value="Delete User" /><br>
                <input type="submit" formmethod="get" formaction="/changegroupmember" value="Change"/>
                <input type="submit" formaction="/givetask" formmethod="get" value="Give Task"/><br>
            </#if>
        </form>
    </#list>
    <div class="row">
    <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
    <table class="table table-hover">
        <thead>
            <tr><th>
                Ожидает:${percents[0][0]}
            </th></tr>
        </thead>
        <tbody>
        <#list waiting as task>
            <tr>
                <td>
                    <#if task.user.email == userName>
                        <@sgtd.groupTask task ,"mygrouptaskdetails"/>
                    <#else >
                        <@sgtd.groupTask task ,path/>
                    </#if>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
    </div>
    <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
    <table class="table table-hover">
        <thead>
            <tr><th>
            В процессе:${percents[1][0]}
            </th></tr>
        </thead>
        <tbody>
            <#list processing as task>
                <tr>
                    <td>
                        <#if task.user.email == userName>
                            <@sgtd.groupTask task ,"mygrouptaskdetails"/>
                        <#else >
                            <@sgtd.groupTask task ,path/>
                        </#if>
                    </td>
                </tr>
            </#list>
        </tbody>
    </table>
    </div>
    <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
    <table class="table table-hover">
        <thead>
            <tr><th>
            Готово:${percents[2][0]}
            </th></tr>
        </thead>
        <tbody>
            <#list done as task>
                <tr>
                    <td>
                        <@sgtd.groupTask task ,path/>
                    </td>
                </tr>
            </#list>
        </tbody>
    </table>
    </div>
    <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
    <table class="table table-hover">
        <thead>
            <tr><th>
            Провалено:${percents[3][0]}
            </th></tr>
        </thead>
        <tbody>
            <#list failed as task>
                <tr>
                    <td>
                        <@sgtd.groupTask task ,path/>
                    </td>
                </tr>
            </#list>
        </tbody>
    </table>
    </div>
    <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
    <table class="table table-hover">
        <thead>
            <tr><th>
            Отклонено: ${percents[4][0]}
            </th></tr>
        </thead>
        <tbody>
        <#list refused as  task>
            <tr>
                <td>
                    <@sgtd.groupTask task ,path/>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
    </div>
</div>
</@com.page>