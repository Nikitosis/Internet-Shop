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
                <td>
                    <form action="/basket/removeCommodity" method="post">
                        <input type="hidden" name="id" value="${commodity.id}"/>
                        <input type="submit" value="Remove"/>

                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </td>
            </tr>
        </#list>
    </table>
    <br>
    <form action="/basket/confirmBuy" method="post">
        <input type="submit" value="Confirm Buy"/>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</body>
</html>