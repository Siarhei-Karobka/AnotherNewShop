package app.servlets;

import app.dao.ProductDaoImpl;
import app.entities.Page;
import app.entities.PageRequest;
import app.entities.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = {"/home"})
public class ProductListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PageRequest pageRequest = new PageRequest();
        pageRequest.setSize(10);
        pageRequest.setSort((String) request.getAttribute("searchField"));

        if (request.getAttribute("currentPage") == null) {
            pageRequest.setPage(0);
        } else {
            pageRequest.setPage((Integer) request.getAttribute("currentPage"));
        }

        Page<Product> page = new ProductDaoImpl().getAll(pageRequest);

        List<Product> list = page.getContent();
        request.setAttribute("productList", list);
        request.setAttribute("totalPages", page.getTotal());

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/productList.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String method = request.getParameter("method");
        if ("_delete".equals(method)) {
            ProductDaoImpl productDao = new ProductDaoImpl();
            productDao.deleteOneById(request.getParameter("code"));
        }

        if ("_post".equals(method)) {
            int currentPage = Optional.ofNullable(request.getParameter("currentPage")).map(Integer::parseInt).orElse(0);
            request.setAttribute("currentPage", currentPage);
            doGet(request, response);
        }

        if ("_search".equals(method)) {
            String searchField = request.getParameter("searchField");
            request.setAttribute("searchField", searchField);
            doGet(request, response);
        }
        doGet(request, response);
    }

    @Override
    protected void doDelete(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        ProductDaoImpl productDao = new ProductDaoImpl();
        productDao.deleteOneById(request.getQueryString().split("=")[1]);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/productList.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doOptions(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        super.doOptions(req, resp);
    }
}
