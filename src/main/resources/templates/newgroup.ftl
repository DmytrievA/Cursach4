<#import "parts/common.ftl" as com>

<@com.page>
<form method="post" action="/newgroup">
    <input type="text" name="name" placeholder="Enter group Name"/><br/>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input type="submit" value="Add group"/>
</form>
</@com.page>