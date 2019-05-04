<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>

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
<body>

    <h1>Add commodity</h1>
    <form action="/commodities/addCommodity?${_csrf.parameterName}=${_csrf.token}" method="post" name="commodity" enctype="multipart/form-data">
        <table id="table">
            <tr><td>name</td></tr>
            <tr>
                <td><input type="text" name="name"/></td>
            </tr>
            <tr><td>price</td></tr>
            <tr>
                <td><input type="number" name="price"/></td>
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
            <tr id="buttonTr">
                <td><a href="#buttonTr" onclick="addTag()">Add new tag</a</td>
            </tr>
            <tr><td>description</td></tr>
            <tr>
                <td><textarea name="description" style="width:200px;height:200px;"></textarea></td>
            </tr>
            <tr>
                <td><input type="submit" value="Create new commodity"/></td>
            </tr>
        </table>

        <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
    </form>
</body>
</html>