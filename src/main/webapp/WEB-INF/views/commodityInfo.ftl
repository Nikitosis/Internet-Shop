<!DOCTYPE html>
<html lang="en">
<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>Current commodity</h1>
    <table>
        <tr>
            <td>id</td>
            <td>name</td>
            <td>price</td>
        </tr>
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
    </table>

    <h2>Comment section</h2>
    <#if (commodity.comments)??>
        <table>
            <#list commodity.comments as comment>
                <tr>
                    <td>${comment.user.username}</td>
                    <td>${comment.content}</td>
                    <td>${comment.date}</td>
                </tr>
            </#list>
        </table>
    </#if>
    <br>
    <h2>Add your comment</h2>
    <form action="/commodities/addComment" method="post" name="comment">
        <input type="text" name="content"/>

        <input type="submit" value="Add comment"/>

        <input type="hidden" name="commodity_id" value="${commodity.id}"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>

    <a href="/commodities">Back</a>
</body>
</html>