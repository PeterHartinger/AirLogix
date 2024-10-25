package com.flightDomain.flightbook.managers;

import com.flightDomain.flightbook.dao.LogbookEntryDAO;
import com.flightDomain.flightbook.models.LogbookEntry;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

public class LogbookEntryManager {
    private LogbookEntryDAO logbookEntryDAO;

    public LogbookEntryManager(Connection conn) {
        this.logbookEntryDAO = new LogbookEntryDAO(conn);
    }

    public boolean addLogbookEntry(LogbookEntry entry) {
        return logbookEntryDAO.insertLogbookEntry(entry);
    }

    public List<LogbookEntry> getLogbookEntries() {
        return logbookEntryDAO.getAllLogbookEntries();
    }

    public boolean updateLogbookEntry(LogbookEntry entry) {
        return logbookEntryDAO.updateLogbookEntry(entry);
    }

    public boolean deleteLogbookEntry(int flightID) {
        return logbookEntryDAO.deleteLogbookEntry(flightID);
    }
    public Set<String> getUniqueAirports() {
        return logbookEntryDAO.getUniqueAirports();
    }

}
