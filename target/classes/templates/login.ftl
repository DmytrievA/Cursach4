<#import "parts/common.ftl" as com>

<a href="/">Main page</a><br>
<a href="/registration">Create new account</a>
<form action="/login" method="post">
    <div><label> User Email* : <input type="email" name="username"/> </label></div>
    <div><label> Password* : <input type="password" name="password"/> </label></div>
    <div><input type="submit" value="Sign In"/></div>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
</form>