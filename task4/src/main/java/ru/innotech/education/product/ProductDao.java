package ru.innotech.education.product;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDao {

    private final Connection connection;

    public ProductDao(Connection connection) {
        this.connection = connection;
    }

    public List<Product> getAllUserProducts(long id) throws SQLException {
        String SQL_QUERY = "select p.id, p.bankaccount, p.balance, p.type " +
                "from users_products up left join products p ON p.id = up.id_product " +
                "where up.id_user=?";
        List<Product> result = new ArrayList<>();
        PreparedStatement pst = connection.prepareStatement(SQL_QUERY);
        pst.setLong(1, id);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getLong("id"));
            product.setBankAccount(rs.getLong("bankaccount"));
            product.setBalance((double) rs.getLong("balance"));
            product.setType(rs.getString("type"));
            result.add(product);
        }
        return result;
    }

    public Product getProductById(long id) throws SQLException {
        String SQL_QUERY = "select * from products where id=?";
        Product product = null;
        PreparedStatement pst = connection.prepareStatement(SQL_QUERY);
        pst.setLong(1, id);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            product = new Product();
            product.setId(rs.getLong("id"));
            product.setBankAccount(rs.getLong("bankaccount"));
            product.setBalance((double) rs.getLong("balance"));
            product.setType(rs.getString("type"));
        }
        return product;
    }
}
