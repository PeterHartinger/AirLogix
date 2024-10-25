package com.flightDomain.flightbook.dao;

import com.flightDomain.flightbook.models.License;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LicenseDAO {
    private Connection conn;

    public LicenseDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insertLicense(License license) {
        String sql = "INSERT INTO License (Type, DateOfIssue, Number, Authority) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, license.getType());
            pstmt.setDate(2, Date.valueOf(license.getDateOfIssue()));
            pstmt.setString(3, license.getNumber());
            pstmt.setString(4, license.getAuthority());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<License> getAllLicenses() {
        List<License> licenses = new ArrayList<>();
        String sql = "SELECT * FROM License";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                License license = new License(
                        rs.getInt("LicenseID"),
                        rs.getString("Type"),
                        rs.getDate("DateOfIssue").toLocalDate(),
                        rs.getString("Number"),
                        rs.getString("Authority")
                );
                licenses.add(license);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return licenses;
    }

    public boolean updateLicense(License license) {
        String sql = "UPDATE License SET Type = ?, DateOfIssue = ?, Number = ?, Authority = ? WHERE LicenseID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, license.getType());
            pstmt.setDate(2, Date.valueOf(license.getDateOfIssue()));
            pstmt.setString(3, license.getNumber());
            pstmt.setString(4, license.getAuthority());
            pstmt.setInt(5, license.getLicenseID());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteLicense(int licenseId) {
        String sql = "DELETE FROM License WHERE LicenseID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, licenseId);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
