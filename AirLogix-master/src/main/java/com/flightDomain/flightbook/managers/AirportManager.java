package com.flightDomain.flightbook.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class AirportManager {
    private Connection conn;

    public AirportManager(Connection conn) {
        this.conn = conn;
    }

    public Set<String> getAirports() {
        Set<String> airports = new HashSet<>();
        String sql = "SELECT AirportName FROM Airports";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                airports.add(rs.getString("AirportName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airports;
    }

    public boolean addAirport(String airportName) {
        String sql = "INSERT INTO Airports (AirportName) VALUES (?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, airportName);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            if (e.getErrorCode() == 19) { // SQLITE_CONSTRAINT error code for UNIQUE constraint
                System.out.println("Airport already exists.");
            } else {
                e.printStackTrace();
            }
            return false;
        }
    }

    public int getAirportId(String airportName) {
        String sql = "SELECT AirportID FROM Airports WHERE AirportName = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, airportName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("AirportID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
