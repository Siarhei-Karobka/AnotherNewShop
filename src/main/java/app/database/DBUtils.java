package app.database;

import app.entities.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {
    public static List<Product> queryProduct(int page, int size) {
        String sql = "SELECT * FROM product";
        String limits = " LIMIT " + page + "," + size;

        List<Product> list = new ArrayList<Product>();

        ResultSet resultSet = DBItem.executeSelect(sql + limits);

        try {
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
        return list;
    }

    public static void insertProduct(Product product) {
        String code = product.getCode();
        String name = product.getName();
        Float price = product.getPrice();
        String sql;

        if (code.equals("")){
            sql = "INSERT INTO product (NAME,PRICE) VALUES (\"" + name + "\",\"" + price + "\")";
        } else {
            sql = "Replace into Product(Code, Name, Price) values (\"" + code + "\",\"" + name + "\"," + price + ")";
        }

        DBItem.executeUpdate(sql);
    }

    public static Product findProduct(String code) {
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

    public static void deleteProduct(String code) throws SQLException {
        String sql = "Delete from Product where Code= \"" + code + "\"";

        DBItem.executeUpdate(sql);
    }
}
