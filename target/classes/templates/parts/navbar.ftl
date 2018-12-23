<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Home</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/usertask">My tasks</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/groups">Groups </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/newtask">New tasks </a>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0" action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-outline-success my-2 my-sm-0">Log out</button>
        </form>
    </div>
</nav>


