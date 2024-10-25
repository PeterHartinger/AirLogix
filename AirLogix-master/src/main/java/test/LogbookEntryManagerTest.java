//package test;
//
//import com.flightDomain.flightbook.dao.LogbookEntryDAO;
//import com.flightDomain.flightbook.managers.LogbookEntryManager;
//import com.flightDomain.flightbook.models.LogbookEntry;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class LogbookEntryManagerTest {
//
//    private LogbookEntryManager logbookEntryManager;
//    private ArrayList<LogbookEntry> testEntries;
//
//    @BeforeEach
//    public void setUp() {
//        // Mock Connection object or initialize with real connection
//        Connection conn = null; // Replace with your actual database connection
//
//        logbookEntryManager = new LogbookEntryManager(conn);
//        testEntries = new ArrayList<>();
//
//        LogbookEntry entry1 = new LogbookEntry();
//        entry1.setFlightID(1);
//        entry1.setFlightFrom("JFK");
//        entry1.setFlightTo("LAX");
//        entry1.setDepartureTime(LocalTime.of(8, 0));
//        entry1.setArrivalTime(LocalTime.of(10, 0));
//        entry1.setNumLandingsDay(1);
//        entry1.setNumLandingsNight(0);
//        entry1.setPilotFunction("PIC");
//        entry1.setPilotFunctionTime(120);
//        entry1.setFlightTime("02:00");
//        entry1.setNightTime(LocalTime.of(0, 0));
//        entry1.setRemark("Test entry 1");
//        testEntries.add(entry1);
//
//        LogbookEntry entry2 = new LogbookEntry();
//        entry2.setFlightID(2);
//        entry2.setFlightFrom("LAX");
//        entry2.setFlightTo("SFO");
//        entry2.setDepartureTime(LocalTime.of(12, 0));
//        entry2.setArrivalTime(LocalTime.of(14, 0));
//        entry2.setNumLandingsDay(1);
//        entry2.setNumLandingsNight(1);
//        entry2.setPilotFunction("Dual");
//        entry2.setPilotFunctionTime(120);
//        entry2.setFlightTime("02:00");
//        entry2.setNightTime(LocalTime.of(0, 30));
//        entry2.setRemark("Test entry 2");
//        testEntries.add(entry2);
//    }
//
//    @Test
//    public void testAddLogbookEntry() throws SQLException {
//        LogbookEntry newEntry = new LogbookEntry();
//        newEntry.setFlightID(3);
//        newEntry.setFlightFrom("SFO");
//        newEntry.setFlightTo("SEA");
//        newEntry.setDepartureTime(LocalTime.of(16, 0));
//        newEntry.setArrivalTime(LocalTime.of(18, 0));
//        newEntry.setNumLandingsDay(1);
//        newEntry.setNumLandingsNight(0);
//        newEntry.setPilotFunction("PIC");
//        newEntry.setPilotFunctionTime(120);
//        newEntry.setFlightTime("02:00");
//        newEntry.setNightTime(LocalTime.of(0, 0));
//        newEntry.setRemark("Test entry 3");
//
//        assertTrue(logbookEntryManager.addLogbookEntry(newEntry));
//
//        // Check if the entry was added by retrieving all entries and asserting the presence
//        List<LogbookEntry> entries = logbookEntryManager.getLogbookEntries();
//        assertTrue(entries.contains(newEntry));
//    }
//
//    @Test
//    public void testGetLogbookEntries() {
//        List<LogbookEntry> entries = logbookEntryManager.getLogbookEntries();
//        assertNotNull(entries);
//        assertFalse(entries.isEmpty());
//        assertTrue(entries.containsAll(testEntries));
//    }
//
//    @Test
//    public void testUpdateLogbookEntry() throws SQLException {
//        LogbookEntry entryToUpdate = testEntries.get(0);
//        entryToUpdate.setFlightTo("LHR");
//
//        assertTrue(logbookEntryManager.updateLogbookEntry(entryToUpdate));
//
//        // Check if the entry was updated by retrieving it and asserting the updated fields
//        List<LogbookEntry> entries = logbookEntryManager.getLogbookEntries();
//        LogbookEntry updatedEntry = entries.stream()
//                .filter(e -> e.getFlightID() == entryToUpdate.getFlightID())
//                .findFirst()
//                .orElse(null);
//        assertNotNull(updatedEntry);
//        assertEquals("LHR", updatedEntry.getFlightTo());
//    }
//
//    @Test
//    public void testDeleteLogbookEntry() throws SQLException {
//        LogbookEntry entryToDelete = testEntries.get(1);
//
//        assertTrue(logbookEntryManager.deleteLogbookEntry(entryToDelete.getFlightID()));
//
//        // Check if the entry was deleted by retrieving all entries and asserting absence
//        List<LogbookEntry> entries = logbookEntryManager.getLogbookEntries();
//        assertFalse(entries.contains(entryToDelete));
//    }
//}
