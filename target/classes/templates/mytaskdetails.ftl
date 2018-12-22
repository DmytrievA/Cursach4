<#import "parts/common.ftl" as com>
<#import "parts/mytaskdetails.ftl" as t>
<@com.page>
    <@t.myTask task/>
    <form method="post">
        <input type="text" multiple="multiple" name="description"/>
        <select name = "newStatus">
            <#list statuses as status>
                <option value="${status.id}">${status.name}</option>
            </#list>
        </select>
        <input type="hidden" name="_csrf" value="${_csrf.token}}"/>
        <input type="submit" name = "submitAction" value="Accept"/>
    </form>
</@com.page>