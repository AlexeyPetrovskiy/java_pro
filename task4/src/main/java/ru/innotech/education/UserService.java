package ru.innotech.education;

import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUser(long id) throws SQLException {
        return userDao.getUserById(id);
    }

    public List<User> getUsers() throws SQLException {
        return userDao.getUsers();
    }

    public void deleteUser(long id) throws SQLException {
        userDao.deleteUser(id);
    }

    public void putUser(long id, String username) throws SQLException {
        userDao.putUser(id, username);
    }

    public void updateUser(long id, String username) throws SQLException {
        userDao.updateUser(id, username);
    }
}
