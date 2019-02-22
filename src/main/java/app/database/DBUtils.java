package app.database;

import app.entities.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {

    public static List<Product> queryProduct(Connection connection) throws SQLException {
        String sql = "SELECT * FROM product";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();
        List<Product> list = new ArrayList<Product>();
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
        return list;
    }

    public static void insertProduct(Connection conn, Product product) throws SQLException {
        String sql = "Insert into Product(Code, Name,Price) values (?,?,?)";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, product.getCode());
        pstm.setString(2, product.getName());
        pstm.setFloat(3, product.getPrice());

        pstm.executeUpdate();
    }
}
