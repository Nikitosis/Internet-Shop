

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <form name="user" action="/login" method="post">
        <table>
            <tr>
                <td>username</td>
                <td><input title="username" type="text" name="username"/></td>
            </tr>
            <tr>
                <td>password</td>
                <td><input title="password" type="password" name="password"/></td>
            </tr>
            <tr>
                <td><input type="submit"></td>
            </tr>
        </table>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</body>
</html>