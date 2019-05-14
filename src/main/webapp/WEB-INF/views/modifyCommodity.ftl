<#include "components/head_tags.ftl"/>

<!DOCTYPE html>
<html lang="en">

<#setting date_format="yyyy-MM-dd">
<#setting locale="en_US">
<#setting time_zone="UTC">
<head>
    <@head_tags/>

    <script>
        function addTag(){
            buttonTr=document.getElementById("buttonTr");
            buttonTrParent=buttonTr.parentElement;

            newTr=document.createElement("tr");

            tagNames=document.createElement("td");
            tagNames.innerHTML="<input type='text' name='tagNames'/>";

            newTr.appendChild(tagNames);

            tagValues=document.createElement("td");
            tagValues.innerHTML="<input type='text' name='tagValues'/>";

            newTr.appendChild(tagValues);

            buttonTrParent.insertBefore(newTr,buttonTr);
        }
    </script>
</head>
<body>
    <h1>Modify commodity</h1>
    <form action="/commodities/modifyCommodity?${_csrf.parameterName}=${_csrf.token}" method="post" name="commodity" enctype="multipart/form-data">
        <table id="table">
            <tr><td>name</td></tr>
            <tr>
                <td><input type="text" name="name" value="${commodity.name}"/></td>
            </tr>
            <tr><td>price</td></tr>
            <tr>
                <td><input type="number" name="price" value="${commodity.price?string["0"]}" step="any"/></td>
            </tr>
            <tr><td>mainPic</td></tr>
            <tr>
                <td><input type="file" name="mainImg" accept="image/x-png,image/jpeg,image/jpg"/></td>
            </tr>
            <tr><td>Pics</td></tr>
            <tr>
                <td><input type="file" name="imgs" accept="image/x-png,image/jpeg,image/jpg" multiple/></td>
            </tr>
            <tr><td>tags</td></tr>
            <#list commodity.categories as tag>
                <tr>
                    <td><input type='text' name='tagNames' value="${tag.name}"/></td>
                    <td><input type='text' name='tagValues' value="${tag.value}"/></td>
                </tr>
            </#list>
            <tr id="buttonTr">
                <td><a href="#buttonTr" onclick="addTag()">Add new tag</a</td>
            </tr>
            <tr><td>description</td></tr>
            <tr>
                <td><textarea name="description" style="width:200px;height:200px;">${commodity.description}</textarea></td>
            </tr>
            <tr>
                <td><input type="submit" value="Modify commodity"/></td>
            </tr>
        </table>
        <input type="hidden" name="id" value="${commodity.id}"/>

    <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
    </form>
</body>
</html>