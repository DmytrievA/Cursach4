<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Login Page </title>
</head>
<body>
Hello new User!
<#if message??>
    <strong><h1>${message}</h1></strong>
</#if>
<div>
    <form action="/registration" method="post">
        <div><label> User Email* : <input type="email" name="email"/> </label></div>
        <div><label> Password* : <input type="password" name="password"/> </label></div>
        <div><label> Login: <input type="text" name="login"/> </label></div>
        <div><label> Name: <input type="text" name="name"/> </label></div>
        <div><label> Surname: <input type="text" name="surname"/> </label></div>
        <div>
            <label>Gender:
                <select name="gender">
                    <option value="male">male</option>
                    <option value="female">female</option>
                </select>
            </label>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div><input type="submit" value="Create account"/></div>
    </form>
</div>
</body>
</html>