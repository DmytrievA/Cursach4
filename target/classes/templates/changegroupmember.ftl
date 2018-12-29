<#import "parts/common.ftl" as com>

<@com.page>
<form method="post" >
    <input type="text", name="user" readonly="readonly" value="${user}"/>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <select name="newRole" class="form-control" multiple>
        <#list allRoles as role>
            <option value="${role.id}">${role.name}</option>
        </#list>
    </select><br>
    <input type="submit" value="Save changes"/>
</form>
</@com.page>