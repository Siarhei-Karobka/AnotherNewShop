package app.servlets;

import app.database.DBUtils;
import app.entities.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/home"})
public class ProductListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> list = DBUtils.queryProduct();
        req.setAttribute("productList", list);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/productList.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            final String method = request.getParameter("method");
            if ("_delete".equals(method)) {
                DBUtils.deleteProduct(request.getParameter("code"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        doGet(request, response);
    }

    @Override
    protected void doDelete(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        try {
            DBUtils.deleteProduct(req.getQueryString().split("=")[1]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/productList.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doOptions(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        super.doOptions(req, resp);
    }
}
