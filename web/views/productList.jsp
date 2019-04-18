<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Product List</title>
    <style>
        .col1 {
            float: left;
            min-height: 50px;
            width: 100%;
        }
        </style>
</head>
<body>
<h3>Product List</h3>

<div class="col1"> <%-- pagination buttons --%>
    <c:choose>
        <c:when test="${currentPage > 0}">
            <form method="post" style="float: left">
                <input type="hidden" name="method" value="_post">
                <input type="hidden" name="size" value="10">
                <input type="hidden" name="currentPage" value="${currentPage - 1}">
                <input type="submit" class="button" value="Previous" >
            </form>
        </c:when>
        <c:otherwise>
            <form style="float: left">
                <button disabled>Previous</button>
            </form>
        </c:otherwise>
    </c:choose>


    <form method="post" style="float: left">
        <input name="search" type="number" maxlength="3" size="3" value="${currentPage+1}" >
    </form>

    <c:choose>
        <c:when test="${currentPage != totalPages}">
            <form method="post" style="float: left">
                <input type="hidden" name="method" value="_post">
                <input type="hidden" name="size" value="10">
                <input type="hidden" name="currentPage" value="${currentPage + 1}">
                <input type="submit" class="button" value="Next page" >
            </form>
        </c:when>
        <c:otherwise>
            <form style="float: left">
                <button disabled >Next page</button>
            </form>
        </c:otherwise>
    </c:choose>

    <form style="float: right">
        <p><input type="searchField" placeholder="Поиск по сайту">
            <input type="submit" value="Найти"></p>
    </form>
</div>
<div>
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
                        <input type="submit" class="delete-button" value="Delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<div class="col1">
<a href="createProduct">Create Product</a>
</div>
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
