package app.dao;

import app.database.DBItem;
import app.entities.Page;
import app.entities.PageRequest;
import app.entities.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements DAO<Product> {

    static final String BASE = "SELECT * FROM product";
    static final String COUNT = "SELECT COUNT(*) FROM product";

    @Override
    public Page<Product> getAll(PageRequest request) {

        String limits = " LIMIT " + ((request.getPage())) * request.getSize() + "," + request.getSize();

        List<Product> list = new ArrayList<Product>();

        ResultSet countResult = DBItem.executeSelect(COUNT);
        ResultSet resultSet = DBItem.executeSelect(BASE + limits);

        Page<Product> page = new Page<>();

        try {

            if (countResult.next()) {
                if (countResult.getLong(1) % request.getSize() > 0) {
                    page.setTotal(countResult.getLong(1) / request.getSize());
                } else {
                    page.setTotal((countResult.getLong(1) / request.getSize()) - 1);
                }
            }

            page.setContent(list);
//            page.setRequest(request);  //// TODO: 18.04.2019 подумать зачем

            while (resultSet.next()) {
                String code = resultSet.getString("CODE");
                String name = resultSet.getString("NAME");
                float price = resultSet.getFloat("PRICE");
                Product product = new Product();
                product.setCode(code);
                product.setName(name);
                product.setPrice(price);
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return page;
    }

    @Override
    public Product getOneById(String code) {
        String sql = "Select a.Code, a.Name, a.Price from Product a where a.Code= \"" + code + "\"";

        ResultSet rs = DBItem.executeSelect(sql);

        try {
            if (rs.next()) {
                String name = rs.getString("Name");
                float price = rs.getFloat("Price");
                return new Product(code, name, price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createOne(Product product) {
        String code = product.getCode();
        String name = product.getName();
        Float price = product.getPrice();
        String sql;

        if (code.equals("")) {
            sql = "INSERT INTO product (NAME,PRICE) VALUES (\"" + name + "\",\"" + price + "\")";
        } else {
            sql = "Replace into Product(Code, Name, Price) values (\"" + code + "\",\"" + name + "\"," + price + ")";
        }

        DBItem.executeUpdate(sql);
    }

    @Override
    public void deleteOneById(String id) {
        String sql = "Delete from Product where Code= \"" + id + "\"";
        DBItem.executeUpdate(sql);
    }


}
