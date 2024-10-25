package com.flightDomain.flightbook.dao;

import com.flightDomain.flightbook.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection conn;

    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insertUser(User user) {
        String sql = "INSERT INTO User (Name, Address, DateOfBirth, Birthplace, Nationality) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getAddress());
            pstmt.setDate(3, Date.valueOf(user.getBirthdate()));
            pstmt.setString(4, user.getBirthplace());
            pstmt.setString(5, user.getNationality());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM User";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getDate("dateofbirth").toLocalDate(),
                        rs.getString("birthplace"),
                        rs.getString("nationality")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean updateUser(User user) {
        String sql = "UPDATE User SET Name = ?, Address = ?, DateOfBirth = ?, Birthplace = ?, Nationality = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getAddress());
            pstmt.setDate(3, Date.valueOf(user.getBirthdate()));
            pstmt.setString(4, user.getBirthplace());
            pstmt.setString(5, user.getNationality());
            pstmt.setInt(6, user.getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(int userId) {
        String sql = "DELETE FROM User WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Neue Methode zum Entleeren der Tabelle
    public boolean clearTable() {
        String sql = "DELETE FROM User";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            stmt.executeUpdate("DELETE FROM sqlite_sequence WHERE name='User'");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
