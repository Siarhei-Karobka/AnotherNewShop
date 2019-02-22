<%@ page import="java.util.List" %>
<%@ page import="app.entities.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product List</title>
</head>
<body>
<h3>Product List</h3>



<%
    List<Product> products = (List<Product>) request.getAttribute("productList");

    if (products != null && !products.isEmpty()) {
        out.println("<ui>");
        for (Product product : products) {
            out.println("<tr>");
            out.print("<td>" + product.getCode() + "</td>");
            out.print("<td>" + product.getName() + "</td>");
            out.print("<td>" + product.getPrice() + "</td>");
            out.println("</tr>");
        }
        out.println("</ui>");
    } else out.println("<p>There are no users yet!</p>");
%>

<a href="createProduct">Create Product</a>
</body>
</html>
