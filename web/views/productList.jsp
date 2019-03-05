<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Product List</title>
</head>
<body>
<h3>Product List</h3>

<table border="1" cellpadding="5" cellspacing="1">
    <tr>
        <th>Code</th>
        <th>Name</th>
        <th>Price</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${productList}" var="product">
        <tr>
            <td>${product.code}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>
                <a href="createProduct?code=${product.code}">Edit</a>
            </td>
            <td>
                <form method="post">
                    <input type="hidden" name="method" value="_delete">
                    <input type="hidden" name="code" value="${product.code}">
                    <input type="submit" class="delete-button" data-id="${product.code}" value="Delete">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="createProduct">Create Product</a>

<%--<script type="text/javascript">
    var buttons  = document.getElementsByClassName("delete-button");
    for (var btn in buttons) {
        if (buttons.hasOwnProperty(btn)) {
            var target = buttons[btn];
            target.addEventListener('click', function (event) {
                var xhr = new XMLHttpRequest();
                var params = 'code=' + event.target.getAttribute('data-id');
                xhr.open("DELETE", '/productList?' + params, true);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState == 4 && xhr.status == "200") {
                        console.table(xhr.responseText);
                        location.reload();
                    } else {
                        console.error(xhr.responseText);
                    }
                };
                xhr.send();
            })
        }
    }
</script>--%>

</body>
</html>
