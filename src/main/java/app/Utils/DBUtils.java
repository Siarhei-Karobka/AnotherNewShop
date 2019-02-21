package app.Utils;

import app.entities.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by OK on 22.02.2019.
 */
public class DBUtils {

    public static List<Product> queryProduct (Connection connection) throws SQLException {
        String sql = "SELECT * FROM product";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();
        List<Product> list = new ArrayList<Product>();
        while (resultSet.next()){
            String code = resultSet.getString("Code");
            String name = resultSet.getString("Name");
            float price = resultSet.getFloat("Price");
            Product product = new Product();
            product.setCode(code);
            product.setName(name);
            product.setPrice(price);
            list.add(product);
        }
        return list;
    }
}
