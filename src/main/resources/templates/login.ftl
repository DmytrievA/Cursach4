<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>DTP</title>
</head>
<body >
<nav class="navbar navbar-expand-lg navbar-light bg-light" xmlns="http://www.w3.org/1999/html">
    <a class="navbar-brand" href="/">Home</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Main page</a>
                </li>
            <li class="nav-item">
                <a class="nav-link" href="/registration">Create new account</a>
                </li>
        </ul>
    </div>
</nav>
<div class="container mt-3">
    <form action="/login" method="post" class="form-horizontal" role="form">
        <div class="form-group"><label> User Email* :</label>
            <div class="col-sm-offset-2 col-sm-5">
            <input type="email" name="username"/> </div></div>
        <div class="form-group"><label> Password* :</label>
            <div class="col-sm-offset-2 col-sm-5">
            <input type="password" name="password"/> </div></div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-5">
            <button class="btn btn-primary" type="submit" >Sign In</button></div></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    </form>
</div>
</body>
</html>
