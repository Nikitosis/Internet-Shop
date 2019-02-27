<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>User Basket</h1>
    <table>
        <tr>
            <td>id</td>
            <td>name</td>
            <td>price</td>
        </tr>
        <#list commoditiesInBasket as commodity>
            <tr>
                <td>${commodity.id}</td>
                <td>${commodity.name}</td>
                <td>${commodity.price}</td>
            </tr>
        </#list>
    </table>
    <br>
    <a href="/basket/confirmBuy">Confirm buy</a>
</body>
</html>