<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>Commodities List</h1>
    <table>
        <tr>
            <td>id</td>
            <td>name</td>
            <td>price</td>
        </tr>
        <#list commodities as commodity>
            <tr>
                <td>${commodity.id}</td>
                <td>${commodity.name}</td>
                <td>${commodity.price}</td>
                <td><a href="/addToBasket/${commodity.id}">AddToBasket</a></td>
            </tr>
        </#list>
    </table>

    <a href="/">Back</a>
</body>
</html>