package app.servlets;

import app.database.DBConnection;
import app.database.DBUtils;
import app.entities.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


public class CreateProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Product product = null;

        String code = req.getParameter("code");

        if (code != null){
            product = DBUtils.findProduct(code);
        }
        req.setAttribute("product", product);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/createProduct.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String code = req.getParameter("code");
        String name = req.getParameter("name");
        String priceStr = req.getParameter("price");
        float price = 0;
        try {
            price = Float.parseFloat(priceStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Product product = new Product(code, name, price);

        String errorString = null;

        // Кодом продукта является строка [a-zA-Z_0-9]
        // Имеет минимум 1 символ.
        String regex = "\\w+";

        if (code == null || !code.matches(regex)) {
            errorString = "Product Code invalid!";
        }

        DBUtils.insertProduct(product);

        // Сохранить информацию в request attribute перед тем как forward к views.
        req.setAttribute("product", product);

        //перенаправляемся на список продуктов.
        resp.sendRedirect(req.getContextPath() + "/productList");
    }
}
