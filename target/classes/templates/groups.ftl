
<#import "parts/common.ftl" as com>

<@com.page>
<#list groups as group>
    <form method="get" action="/selectedGroup">
    ${group.name}
    <input type="hidden" value="${group.name}" name="groupName" readonly="readonly"/>
    <input type="hidden" value="${group.id}" name="groupId" />
    <input type="submit" value="Show more"><br>
    <input type="submit" value="Leave group" formmethod="post" formaction="/leavegroup">
    </form>
    <hr>
</#list>
</@com.page>
