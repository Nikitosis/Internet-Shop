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
                <td>
                    <form action="/addToBasket" method="post">
                        <input type="hidden" name="commodity_id" value="${commodity.id}"/>
                        <input type="submit" value="AddToBasket"/>

                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </td>
            </tr>
        </#list>
    </table>

    <a href="/">Back</a>
</body>
</html>