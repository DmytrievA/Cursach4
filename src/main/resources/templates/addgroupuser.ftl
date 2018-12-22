<#import "parts/common.ftl" as com>

<@com.page>
    <form method="post">
        <select name="userEmail" class="form-control" multiple>
            <#list users as user>
                <option value="${user}">${user}</option>
            </#list>
        </select><br>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="submit" value="Add User to group ${groupName}"/>
    </form>
</@com.page>