//package test;
//
//import com.flightDomain.flightbook.managers.LogbookManager;
//import com.flightDomain.flightbook.models.Logbook;
//import com.flightDomain.flightbook.models.LogbookEntry;
//import com.flightDomain.flightbook.utils.TimeManager;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalTime;
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class LogbookManagerTest {
//
//    private Logbook logbook;
//    private ArrayList<LogbookEntry> entries;
//
//    @BeforeEach
//    public void setUp() {
//        entries = new ArrayList<>();
//
//        LogbookEntry entry1 = new LogbookEntry();
//        entry1.setDepartureTime(LocalTime.of(8, 0));
//        entry1.setArrivalTime(LocalTime.of(10, 0));
//        entry1.setNumLandingsDay(1);
//        entry1.setNumLandingsNight(0);
//        entry1.setPilotFunction("PIC");
//        entry1.setPilotFunctionTime(120);
//        entry1.setFlightTime("02:00");
//        entry1.setNightTime(LocalTime.of(0, 0));
//        entries.add(entry1);
//
//        LogbookEntry entry2 = new LogbookEntry();
//        entry2.setDepartureTime(LocalTime.of(12, 0));
//        entry2.setArrivalTime(LocalTime.of(14, 0));
//        entry2.setNumLandingsDay(1);
//        entry2.setNumLandingsNight(1);
//        entry2.setPilotFunction("Dual");
//        entry2.setPilotFunctionTime(120);
//        entry2.setFlightTime("02:00");
//        entry2.setNightTime(LocalTime.of(0, 30));
//        entries.add(entry2);
//
//        logbook = new Logbook(entries);
//    }
//
//    @Test
//    public void testUpdateLogbookTimes() {
//        LogbookManager.updateLogbookTimes(logbook);
//
//        assertEquals("PT4H", logbook.getTotalFlightTime());
//        assertEquals(3, logbook.getTotalLandings());
//        assertEquals(1, logbook.getTotalLandingsT());
//        assertEquals("PT2H", logbook.getTotalTimeAsPic());
//        assertEquals("PT2H", logbook.getTotalTimeAsDual());
//        assertEquals("PT0S", logbook.getTotalTimeAsFI());
//        assertEquals("PT3H30M", logbook.getTotalTimeDay());
//        assertEquals("PT30M", logbook.getTotalTimeNight());
//    }
//}
