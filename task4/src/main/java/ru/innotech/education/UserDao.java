package ru.innotech.education;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao {

    private final Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public User getUserById(long id) throws SQLException {
        String SQL_QUERY = "select * from users where id=?";
        User user = null;
        PreparedStatement pst = connection.prepareStatement(SQL_QUERY);
        pst.setLong(1, id);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            user = new User();
            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("username"));
        }
        return user;
    }

    public List<User> getUsers() throws SQLException {
        String SQL_QUERY = "select * from users";
        List<User> listUser = new ArrayList<>();
        User user;
        PreparedStatement pst = connection.prepareStatement(SQL_QUERY);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            user = new User();
            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("username"));
            listUser.add(user);
        }
        return listUser;
    }

    public void putUser(long id, String username) throws SQLException {
        String SQL_QUERY = "Insert into users values (?,?)";
        PreparedStatement pst = connection.prepareStatement(SQL_QUERY);
        pst.setLong(1, id);
        pst.setString(2, username);
        pst.execute();
    }

    public void updateUser(long id, String username) throws SQLException {
        String SQL_QUERY = "Update users set username=? where id=?";
        PreparedStatement pst = connection.prepareStatement(SQL_QUERY);
        pst.setLong(2, id);
        pst.setString(1, username);
        pst.execute();
    }

    public void deleteUser(long id) throws SQLException {
        String SQL_QUERY = "delete from users where id=?";
        PreparedStatement pst = connection.prepareStatement(SQL_QUERY);
        pst.setLong(1, id);
        pst.execute();
    }
}

