<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>Registration12</h1>
    <form method="post" action="/registration" name="user">
        <table>
            <tr>
                <td>username: </td>
                <td><input type="text" name="username"/></td>
            </tr>
            <tr>
                <td>password: </td>
                <td><input type="password" name="password"/> </td>
            </tr>
            <tr>
                <input type="submit" value="Register"/>
            </tr>
        </table>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>

</body>
</html>