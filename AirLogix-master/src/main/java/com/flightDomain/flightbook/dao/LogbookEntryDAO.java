package com.flightDomain.flightbook.dao;

import com.flightDomain.flightbook.models.LogbookEntry;
import com.flightDomain.flightbook.utils.TimeManager;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LogbookEntryDAO {
    private Connection conn;

    public LogbookEntryDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insertLogbookEntry(LogbookEntry logbookEntry) {
        String sql = "INSERT INTO LogbookEntry (FlightFrom, FlightTo, Airplane, EngineType, DepartureTime, ArrivalTime, NumLandingsNight, NumLandingsDay, IFRTime, NightTime, PilotInCommand, PilotFunction, PilotFunctionTime, FlightTime, Remark, FlightDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, logbookEntry.getFlightFrom());
            pstmt.setString(2, logbookEntry.getFlightTo());
            pstmt.setString(3, logbookEntry.getAirplane());
            pstmt.setString(4, logbookEntry.getEngineType());
            pstmt.setString(5, logbookEntry.getDepartureTime() != null ? logbookEntry.getDepartureTime().format(DateTimeFormatter.ofPattern("HH:mm")) : null);
            pstmt.setString(6, logbookEntry.getArrivalTime() != null ? logbookEntry.getArrivalTime().format(DateTimeFormatter.ofPattern("HH:mm")) : null);
            pstmt.setInt(7, logbookEntry.getNumLandingsNight());
            pstmt.setInt(8, logbookEntry.getNumLandingsDay());
            pstmt.setString(9, logbookEntry.getIfrTime() != null ? logbookEntry.getIfrTime().format(DateTimeFormatter.ofPattern("HH:mm")) : null);
            pstmt.setString(10, logbookEntry.getNightTime() != null ? logbookEntry.getNightTime().format(DateTimeFormatter.ofPattern("HH:mm")) : null);
            pstmt.setString(11, logbookEntry.getPilotInCommand());
            pstmt.setString(12, logbookEntry.getPilotFunction());
            pstmt.setString(13, logbookEntry.getPilotFunctionTime() != null ? TimeManager.formatDuration(logbookEntry.getPilotFunctionTime()) : null); // convert Duration to String
            pstmt.setString(14, logbookEntry.getFlightTime());
            pstmt.setString(15, logbookEntry.getRemark());
            pstmt.setString(16, logbookEntry.getFlightDate() != null ? logbookEntry.getFlightDate().format(DateTimeFormatter.ISO_LOCAL_DATE) : null); // Hinzugefügt
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<LogbookEntry> getAllLogbookEntries() {
        List<LogbookEntry> logbookEntries = new ArrayList<>();
        String sql = "SELECT * FROM LogbookEntry";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                LogbookEntry logbookEntry = new LogbookEntry();
                logbookEntry.setFlightID(rs.getInt("FlightID"));
                logbookEntry.setFlightFrom(rs.getString("FlightFrom"));
                logbookEntry.setFlightTo(rs.getString("FlightTo"));
                logbookEntry.setAirplane(rs.getString("Airplane"));
                logbookEntry.setEngineType(rs.getString("EngineType"));
                logbookEntry.setDepartureTime(rs.getString("DepartureTime") != null ? LocalTime.parse(rs.getString("DepartureTime"), DateTimeFormatter.ofPattern("HH:mm")) : null);
                logbookEntry.setArrivalTime(rs.getString("ArrivalTime") != null ? LocalTime.parse(rs.getString("ArrivalTime"), DateTimeFormatter.ofPattern("HH:mm")) : null);
                logbookEntry.setNumLandingsNight(rs.getInt("NumLandingsNight"));
                logbookEntry.setNumLandingsDay(rs.getInt("NumLandingsDay"));
                logbookEntry.setIfrTime(rs.getString("IFRTime") != null ? LocalTime.parse(rs.getString("IFRTime"), DateTimeFormatter.ofPattern("HH:mm")) : null);
                logbookEntry.setNightTime(rs.getString("NightTime") != null ? LocalTime.parse(rs.getString("NightTime"), DateTimeFormatter.ofPattern("HH:mm")) : null);
                logbookEntry.setPilotInCommand(rs.getString("PilotInCommand"));
                logbookEntry.setPilotFunction(rs.getString("PilotFunction"));
                logbookEntry.setPilotFunctionTime(rs.getString("PilotFunctionTime") != null ? TimeManager.parseTime(rs.getString("PilotFunctionTime")) : null); // convert String to Duration
                logbookEntry.setFlightTime(rs.getString("FlightTime"));
                logbookEntry.setRemark(rs.getString("Remark"));
                logbookEntry.setFlightDate(rs.getString("FlightDate") != null ? LocalDate.parse(rs.getString("FlightDate"), DateTimeFormatter.ISO_LOCAL_DATE) : null); // Hinzugefügt
                logbookEntries.add(logbookEntry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logbookEntries;
    }

    public boolean updateLogbookEntry(LogbookEntry logbookEntry) {
        String sql = "UPDATE LogbookEntry SET FlightFrom = ?, FlightTo = ?, Airplane = ?, EngineType = ?, DepartureTime = ?, ArrivalTime = ?, NumLandingsNight = ?, NumLandingsDay = ?, IFRTime = ?, NightTime = ?, PilotInCommand = ?, PilotFunction = ?, PilotFunctionTime = ?, FlightTime = ?, Remark = ?, FlightDate = ? WHERE FlightID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, logbookEntry.getFlightFrom());
            pstmt.setString(2, logbookEntry.getFlightTo());
            pstmt.setString(3, logbookEntry.getAirplane());
            pstmt.setString(4, logbookEntry.getEngineType());
            pstmt.setString(5, logbookEntry.getDepartureTime() != null ? logbookEntry.getDepartureTime().format(DateTimeFormatter.ofPattern("HH:mm")) : null);
            pstmt.setString(6, logbookEntry.getArrivalTime() != null ? logbookEntry.getArrivalTime().format(DateTimeFormatter.ofPattern("HH:mm")) : null);
            pstmt.setInt(7, logbookEntry.getNumLandingsNight());
            pstmt.setInt(8, logbookEntry.getNumLandingsDay());
            pstmt.setString(9, logbookEntry.getIfrTime() != null ? logbookEntry.getIfrTime().format(DateTimeFormatter.ofPattern("HH:mm")) : null);
            pstmt.setString(10, logbookEntry.getNightTime() != null ? logbookEntry.getNightTime().format(DateTimeFormatter.ofPattern("HH:mm")) : null);
            pstmt.setString(11, logbookEntry.getPilotInCommand());
            pstmt.setString(12, logbookEntry.getPilotFunction());
            pstmt.setString(13, logbookEntry.getPilotFunctionTime() != null ? TimeManager.formatDuration(logbookEntry.getPilotFunctionTime()) : null); // convert Duration to String
            pstmt.setString(14, logbookEntry.getFlightTime());
            pstmt.setString(15, logbookEntry.getRemark());
            pstmt.setString(16, logbookEntry.getFlightDate() != null ? logbookEntry.getFlightDate().format(DateTimeFormatter.ISO_LOCAL_DATE) : null); // Hinzugefügt
            pstmt.setInt(17, logbookEntry.getFlightID());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean deleteLogbookEntry(int logbookEntryId) {
        String sql = "DELETE FROM LogbookEntry WHERE FlightID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, logbookEntryId);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Set<String> getUniqueAirports() {
        Set<String> airports = new HashSet<>();
        String sql = "SELECT DISTINCT FlightFrom AS Airport FROM LogbookEntry UNION SELECT DISTINCT FlightTo AS Airport FROM LogbookEntry";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                airports.add(rs.getString("Airport"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airports;
    }

    public boolean clearLogbookEntryTable() {
        String sql = "DELETE FROM LogbookEntry";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            stmt.executeUpdate("DELETE FROM sqlite_sequence WHERE name='LogbookEntry'");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
