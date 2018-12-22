<#import "parts/common.ftl" as com>

<@com.page>
    <div class="nav">
        <a href = "/">Main Page</a>
        <a href = "/groups">My Groups</a>
        <a href = "/newtask">New Tasks</a>
    </div>
    <form method="post">
        <select name="userEmail">
            <#list users as user>
                <option value="${user}">${user}</option>
            </#list>
        </select><br>
        <input type="hidden" name="_csrf" value="${_csrf.token}}"/>
        <input type="submit" value="Add User to group ${groupName}"/>
    </form>
</@com.page>