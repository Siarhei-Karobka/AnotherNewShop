<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Product</title>
</head>
<body>
<h3>Create Product</h3>

<form method="POST" action="${pageContext.request.contextPath}/createProduct">
    <table border="0">
        <tr>
            <td>Code</td>
            <td><input type="text" name="code" value="${product.code}"/></td>
            <td>if this field empty you'll add new product or replace exist</td>
        </tr>
        <tr>
            <td>Name</td>
            <td><input type="text" name="name" value="${product.name}"/></td>
        </tr>
        <tr>
            <td>Price</td>
            <td><input type="text" name="price" value="${product.price}"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit"/>
                <a href="productList">Cancel</a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
