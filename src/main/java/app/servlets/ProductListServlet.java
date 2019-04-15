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
import java.util.Optional;

@WebServlet(urlPatterns = {"/home"})
public class ProductListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int page = 1;
        int size = 10;

        List<Product> list = DBUtils.queryProduct((page-1)*size, size);
        request.setAttribute("productList", list);
        request.setAttribute("currentPage", page);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/productList.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            final String method = request.getParameter("method");
            if ("_delete".equals(method)) {
                DBUtils.deleteProduct(request.getParameter("code"));
            }
            if ("_post".equals(method)){
                int page = Optional.ofNullable(request.getParameter("page")).map(Integer::parseInt).orElse(0);
                int size = Integer.parseInt(request.getParameter("size"));
                if (request.getParameter("size") == null) {
                    size = 10;
                } else {
                    size = Integer.parseInt(request.getParameter("size"));
                }

                List<Product> list = DBUtils.queryProduct((page-1)*size, size);
                request.setAttribute("productList", list);
                request.setAttribute("currentPage", page);

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/productList.jsp");
                requestDispatcher.forward(request, response);

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
