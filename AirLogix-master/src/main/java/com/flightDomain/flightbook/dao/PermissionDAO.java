package com.flightDomain.flightbook.dao;

import com.flightDomain.flightbook.models.Permission;
import com.flightDomain.flightbook.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PermissionDAO {
    private Connection conn;

    public PermissionDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insertPermission(Permission permission) {
        String sql = "INSERT INTO Permission (Rating, DateOfTest, ValidUntil, Signature) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, permission.getRating());
            pstmt.setDate(2, Date.valueOf(permission.getDateOfTest()));
            pstmt.setDate(3, Date.valueOf(permission.getValidUntil()));
            pstmt.setString(4, permission.getSignature());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Permission> getAllPermissions() {
        List<Permission> permissions = new ArrayList<>();
        String sql = "SELECT * FROM Permission";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {

                Permission permission = new Permission(
                        rs.getInt("PermissionID"),
                        rs.getString("Rating"),
                        rs.getDate("DateOfTest").toLocalDate(),
                        rs.getDate("ValidUntil").toLocalDate(),
                        rs.getString("Signature")
                );
                permissions.add(permission);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return permissions;
    }

    public boolean updatePermission(Permission permission) {
        String sql = "UPDATE Permission SET Rating = ?, DateOfTest = ?, ValidUntil = ?, Signature = ? WHERE PermissionID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, permission.getRating());
            pstmt.setDate(2, Date.valueOf(permission.getDateOfTest()));
            pstmt.setDate(3, Date.valueOf(permission.getValidUntil()));
            pstmt.setString(4, permission.getSignature());
            pstmt.setInt(5, permission.getPermissionID());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletePermission(int permissionId) {
        String sql = "DELETE FROM Permission WHERE PermissionID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, permissionId);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
