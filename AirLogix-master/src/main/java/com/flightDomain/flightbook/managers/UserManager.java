package com.flightDomain.flightbook.managers;



import com.flightDomain.flightbook.dao.UserDAO;
import com.flightDomain.flightbook.models.User;

import java.sql.Connection;
import java.util.List;

public class UserManager {
    private UserDAO userDAO;

    public UserManager(Connection conn) {
        this.userDAO = new UserDAO(conn);
    }

    public boolean addUser(User user) {
        return userDAO.insertUser(user);
    }

    public List<User> getUsers() {
        return userDAO.getAllUsers();
    }

    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }

    public boolean deleteUser(int userId) {
        return userDAO.deleteUser(userId);
    }
}

