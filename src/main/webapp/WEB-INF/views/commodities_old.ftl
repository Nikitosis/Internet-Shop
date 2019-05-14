!DOCTYPE html>
<html lang="en">
<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
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
                <td>
                    <a href="/commodities/${commodity.id}">${commodity.name}</a>
                </td>
                <td>${commodity.price}</td>
                <td>
                    <form action="/addToBasket" method="post">
                        <input type="hidden" name="commodity_id" value="${commodity.id}"/>
                        <input type="submit" value="AddToBasket"/>

                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </td>
                <@security.authorize access="hasRole('ROLE_ADMIN')">
                    <td><a href="/commodities/modifyCommodity/${commodity.id}">Modify commodity</a></td>
                </@security.authorize>
                <@security.authorize access="hasRole('ROLE_ADMIN')">
                    <td>
                        <form action="/commodities/deleteCommodity" method="post">
                            <input type="hidden" name="id" value="${commodity.id}"/>
                            <input type="submit" value="Delete commodity"/>

                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                    </td>
                </@security.authorize>
            </tr>
        </#list>
    </table>

    <@security.authorize access="hasRole('ROLE_ADMIN')">
        <a href="/commodities/addCommodity">Add commodity</a>
        <br>
    </@security.authorize>
    <a href="/">Back</a>
    <form action="/commodities/search" method="get">
        <input type="number" name="startPrice"/>
        <input type="number" name="endPrice"/>
        <input type="text" name="searchName"/>
        <input type="submit" value="Search"/>
    </form>
</body>
</html>