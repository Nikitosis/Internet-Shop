<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>User orders</h1>
    <table>
        <tr>
            <td>commodity</td>
            <td>date</td>
            <td>price</td>
        </tr>
        <#list orders as order>
            <tr>
                <td>${order.commodity.name}</td>
                <td>${order.date}</td>
                <td>${order.commodity.price}</td>
            </tr>
        </#list>
    </table>
</body>
</html>