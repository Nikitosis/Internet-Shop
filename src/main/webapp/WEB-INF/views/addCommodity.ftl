<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>Add commodity</h1>
    <form action="/commodities/addCommodity" method="post" name="commodity">
        <table>
            <tr>
                <td><input type="text" name="name"/></td>
            </tr>
            <tr>
                <td><input type="number" name="price"/></td>
            </tr>
            <tr>
                <td><input type="date" name="creationDate"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="Create new commodity"/></td>
            </tr>
        </table>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</body>
</html>