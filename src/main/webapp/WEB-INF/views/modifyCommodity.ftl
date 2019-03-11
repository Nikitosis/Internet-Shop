<!DOCTYPE html>
<html lang="en">

<#setting date_format="yyyy-MM-dd">
<#setting locale="en_US">
<#setting time_zone="UTC">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>Modify commodity</h1>
    <form action="/commodities/modifyCommodity" method="post" name="commodity">
        <table>
            <tr>
                <td><input type="text" name="name" value="${commodity.name}"/></td>
            </tr>
            <tr>
                <td><input type="number" name="price" value="${commodity.price}"/></td>
            </tr>
            <tr>
                <td><input type="date" name="creationDate" value="${commodity.creationDate}"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="Modify commodity"/></td>
            </tr>
        </table>
        <input type="hidden" name="id" value="${commodity.id}"/>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</body>
</html>