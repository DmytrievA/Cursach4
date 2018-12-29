<#import "parts/common.ftl" as com>
<#import "parts/shortgrouptaskdetails.ftl" as sgtd>

<@com.page>
    <div class="row mb-2" xmlns="http://www.w3.org/1999/html">
    <div class="col-lg-5 col-md-5 col-sm-5">
        <h3>${currentGroupName?if_exists}</h3><br>
        <#if userRole == "admin">
            <a href="/addgroupuser">Add new user</a>
        </#if><br/>
        <#list users as user>
            <strong>${user.role.name}   ${user.raiting}</strong><br>
            <form method="post" class="form-horizontal" role="form">
            ${user.user.email}<br>
            <input type="hidden" name="userName", value="${user.user.email}" readonly="readonly"/>

            <#if userRole == "admin">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button class="btn btn-primary" type="submit" >Delete User</button>
                <button class="btn btn-primary" type="submit" formaction="/changegroupmember" formmethod="get" >Change</button>
                <button class="btn btn-primary" type="submit" formaction="/givetask" formmethod="get">Give Task</button>
            </#if>
            </form>
        </#list>
    </div>

    <div class="col-lg-7 col-md-7 col-sm-7">
    <figure>
        <figcaption>Статистика заданий по группе</figcaption>
        <#list data as row>
        <svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" class="chart" width="420" height="20" aria-labelledby="title" role="img">
            <title id="title">Статистика заданий по группе</title>
                <g class="bar">
                    <rect width=" ${row.getPercentShow()}%" height="19"></rect>
                </g>
        </svg><label>${row.getPercentText()}% ${row.getCategoryName()}</label>
        </#list>
    </figure>
</div>
    </div>
    <div class="row row-eq-height" style="font-size: 85%">
    <div class="mx-auto col-lg-2 col-md-2 col-sm-2">
    <table>
    <tr>
       <th colspan="2"><h4>Ожидает:${percents[0][0]}</h4></th>
    </tr>
    <tr>
    <#list waiting as task>
        <#if task.user.email == userName>
            <@sgtd.groupTask task ,"mygrouptaskdetails"/>
        <#else >
            <@sgtd.groupTask task ,path/>
        </#if>
    </#list>
    </tr>
    </table>
    </div>
    <div class="mx-auto col-lg-2 col-md-2 col-sm-2">
    <table>
    <tr>
    <th colspan="2"><h4>В процессе:${percents[1][0]}</h4>
    </tr>
    <tr>
        <#list processing as task>
            <#if task.user.email == userName>
                <@sgtd.groupTask task ,"mygrouptaskdetails"/>
            <#else >
                <@sgtd.groupTask task ,path/>
            </#if>
        </#list>
    </tr>
    </table>
    </div>
    <div class="mx-auto col-lg-2 col-md-2 col-sm-2">
    <table>
    <tr>
    <th colspan="2"><h4>Готово:${percents[2][0]}</h4>
    </tr>
    <tr>
            <#list done as task>
                        <@sgtd.groupTask task ,path/>
            </#list>
    </tr>
    </table>
    </div>
    <div class="mx-auto col-lg-2 col-md-2 col-sm-2" style="height: 100%">
    <table>
    <tr>
    <th colspan="2"><h4>Провалено:${percents[3][0]}</h4>
    </tr>
    <tr>
            <#list failed as task>
                        <@sgtd.groupTask task ,path/>
            </#list>
    </tr>
    </table>
    </div>
    <div class="mx-auto col-lg-2 col-md-2 col-sm-2" style="height: 100%">
    <table>
    <tr>
    <th colspan="2"><h4>Отклонено: ${percents[4][0]}</h4>
    </tr>
    <tr>
        <#list refused as  task>
                    <@sgtd.groupTask task ,path/>
        </#list>
        </tr>
        </table>
    </div>
</div>
</@com.page>