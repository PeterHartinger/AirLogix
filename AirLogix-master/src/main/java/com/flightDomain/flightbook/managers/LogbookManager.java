package com.flightDomain.flightbook.managers;

import com.flightDomain.flightbook.models.Logbook;
import com.flightDomain.flightbook.models.LogbookEntry;
import com.flightDomain.flightbook.utils.TimeManager;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LogbookManager {

    public static void updateLogbookTimes(Logbook logbook) {
        ArrayList<LogbookEntry> entries = logbook.getFlightEntries();

        Duration totalFlightTime = Duration.ZERO;
        int totalLandings = 0;
        int totalLandingsDay = 0;
        int totalLandingsNight = 0;
        Duration totalIfrTime = Duration.ZERO;
        Duration totalNightTime = Duration.ZERO;
        Duration totalTimeAsPic = Duration.ZERO;
        Duration totalTimeAsDual = Duration.ZERO;
        Duration totalTimeAsFI = Duration.ZERO;
        Map<String, Duration> engineTypeTimes = new HashMap<>();

        for (LogbookEntry entry : entries) {
            LocalTime departureTime = entry.getDepartureTime();
            LocalTime arrivalTime = entry.getArrivalTime();
            Duration flightDuration = TimeManager.calculateDuration(departureTime, arrivalTime);
            totalFlightTime = totalFlightTime.plus(flightDuration);

            totalLandings += entry.getNumLandingsDay() + entry.getNumLandingsNight();
            totalLandingsDay += entry.getNumLandingsDay();
            totalLandingsNight += entry.getNumLandingsNight();

            Duration ifrTime = entry.getIfrTime() != null ? TimeManager.parseLocalTime(entry.getIfrTime()) : Duration.ZERO;
            totalIfrTime = totalIfrTime.plus(ifrTime);

            Duration nightTime = entry.getNightTime() != null ? TimeManager.parseLocalTime(entry.getNightTime()) : Duration.ZERO;
            totalNightTime = totalNightTime.plus(nightTime);

            Duration pilotFunctionTime = entry.getPilotFunctionTime();

            if ("PIC".equalsIgnoreCase(entry.getPilotFunction())) {
                totalTimeAsPic = totalTimeAsPic.plus(pilotFunctionTime);
            }

            if ("Dual".equalsIgnoreCase(entry.getPilotFunction())) {
                totalTimeAsDual = totalTimeAsDual.plus(pilotFunctionTime);
            }

            if ("FI".equalsIgnoreCase(entry.getPilotFunction())) {
                totalTimeAsFI = totalTimeAsFI.plus(pilotFunctionTime);
            }

            String engineType = entry.getEngineType();
            if (engineType != null && !engineType.isEmpty()) {
                engineTypeTimes.put(engineType, engineTypeTimes.getOrDefault(engineType, Duration.ZERO).plus(flightDuration));
            }
        }

        logbook.setTotalFlightTime(TimeManager.formatDuration(totalFlightTime));
        logbook.setTotalLandings(totalLandings);
        logbook.setTotalLandingsDay(totalLandingsDay);
        logbook.setTotalLandingsNight(totalLandingsNight);
        logbook.setTotalIfrTime(TimeManager.formatDuration(totalIfrTime));
        logbook.setTotalNightTime(TimeManager.formatDuration(totalNightTime));
        logbook.setTotalTimeAsPic(TimeManager.formatDuration(totalTimeAsPic));
        logbook.setTotalTimeAsDual(TimeManager.formatDuration(totalTimeAsDual));
        logbook.setTotalTimeAsFI(TimeManager.formatDuration(totalTimeAsFI));

        // Konvertieren Sie die engineTypeTimes Map zu String
        Map<String, String> formattedEngineTypeTimes = new HashMap<>();
        for (Map.Entry<String, Duration> entry : engineTypeTimes.entrySet()) {
            formattedEngineTypeTimes.put(entry.getKey(), TimeManager.formatDuration(entry.getValue()));
        }
        logbook.setEngineTypeTimes(formattedEngineTypeTimes);
    }
}
