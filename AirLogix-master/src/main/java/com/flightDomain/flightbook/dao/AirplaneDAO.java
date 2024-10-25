package com.flightDomain.flightbook.dao;

import com.flightDomain.flightbook.models.Airplane;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AirplaneDAO {
    private Connection conn;

    public AirplaneDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insertAirplane(Airplane airplane) {
        String sql = "INSERT INTO Airplane (Type, Registration, EngineType) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, airplane.getType());
            pstmt.setString(2, airplane.getRegistration());
            pstmt.setString(3, airplane.getEngineType());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Airplane> getAllAirplanes() {
        List<Airplane> airplanes = new ArrayList<>();
        String sql = "SELECT * FROM Airplane";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Airplane airplane = new Airplane(
                        rs.getInt("PlaneID"),
                        rs.getString("Type"),
                        rs.getString("Registration"),
                        rs.getString("EngineType")
                );
                airplanes.add(airplane);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airplanes;
    }

    public boolean updateAirplane(Airplane airplane) {
        String sql = "UPDATE Airplane SET Type = ?, Registration = ?, EngineType = ? WHERE PlaneID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, airplane.getType());
            pstmt.setString(2, airplane.getRegistration());
            pstmt.setString(3, airplane.getEngineType());
            pstmt.setInt(4, airplane.getPlaneID());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAirplane(int airplaneId) {
        String sql = "DELETE FROM Airplane WHERE PlaneID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, airplaneId);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Airplane getAirplaneByType(String type) {
        String sql = "SELECT * FROM Airplane WHERE Type = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, type);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Airplane(
                        rs.getInt("PlaneID"),
                        rs.getString("Type"),
                        rs.getString("Registration"),
                        rs.getString("EngineType")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
