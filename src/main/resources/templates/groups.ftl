
<#import "parts/common.ftl" as com>

<@com.page>
    <a href="/newgroup">
    <button class="btn btn-primary" type="button">
        Create new group
    </button></a>
    <div class="col-md-8 mx-auto">
    <#list groups as group>
        <form method="get" class="form-horizontal" role="form" action="/selectedGroup">
            <h4>${group.name}</h4>
            <input type="hidden" value="${group.name}" name="groupName" readonly="readonly"/>
            <input type="hidden" value="${group.id}" name="groupId" />
            <input class="btn btn-primary " type="submit" value="Show more">
            <input class="btn btn-primary " type="submit" value="Leave group" formmethod="post" formaction="/leavegroup">
        </form>
        <hr>
    </#list>
    </div>
</@com.page>
