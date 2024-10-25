package com.flightDomain.flightbook.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class EngineTypeManager {
    private Connection conn;

    public EngineTypeManager(Connection conn) {
        this.conn = conn;
    }

    public Set<String> getEngineTypes() {
        Set<String> engineTypes = new HashSet<>();
        String sql = "SELECT EngineType FROM EngineTypes";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                engineTypes.add(rs.getString("EngineType"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return engineTypes;
    }

    public boolean addEngineType(String engineType) {
        String sql = "INSERT INTO EngineTypes (EngineType) VALUES (?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, engineType);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            if (e.getErrorCode() == 19) { // SQLITE_CONSTRAINT error code for UNIQUE constraint
                System.out.println("EngineType already exists.");
            } else {
                e.printStackTrace();
            }
            return false;
        }
    }

    public int getEngineTypeId(String engineType) {
        String sql = "SELECT EngineTypeID FROM EngineTypes WHERE EngineType = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, engineType);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("EngineTypeID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
