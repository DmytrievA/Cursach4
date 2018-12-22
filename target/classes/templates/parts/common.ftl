<#macro page>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>DTP</title>
</head>
<body >
    <div class="nav">
        <a href = "/">Main Page</a>
        <a href = "/groups">My Groups</a>
        <a href = "/newtask">New Tasks</a>
    </div>
    <a href="/login">Sign In</a>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="submit" value="Log out"/>
    </form>
<#nested >
</body>
    <style>
        body, html {
            padding:0;
            margin:0;
            height: auto;
            width: 100%;
        }
    </style>
</html>
</#macro>
