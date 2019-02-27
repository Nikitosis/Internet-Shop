<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<html>
    <head>
    </head>

    <body>
        <h1>Home page</h1>

        <@security.authorize access="isAuthenticated()">
            <p>Welcome, <@security.authentication property="principal.username"/> </p>
            <br>
        </@security.authorize>

        <a href="/commodities">Go to commodities</a>
        <br>

        <@security.authorize access="isAuthenticated()">
            <a href="/userOrders">Show my orders</a>
            <br>
        </@security.authorize>

        <a href="/basket">Basket</a>
        <br>

        <@security.authorize access="!isAuthenticated()">
            <a href="/login">Login</a>
            <br>
            <a href="/registration">Register</a>
            <br>
        </@security.authorize>

        <@security.authorize access="isAuthenticated()">
            <a href="/logout">Logout</a>
            <br>
        </@security.authorize>

    </body>
</html>