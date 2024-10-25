package com.flightDomain.flightbook.controllers;

import com.flightDomain.flightbook.MainApplication;
import com.flightDomain.flightbook.db.DatabaseManager;
import com.flightDomain.flightbook.managers.LogbookEntryManager;
import com.flightDomain.flightbook.managers.LogbookManager;
import com.flightDomain.flightbook.models.Logbook;
import com.flightDomain.flightbook.models.LogbookEntry;
import com.flightDomain.flightbook.utils.TimeManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainController {
    @FXML
    private Label totalFlightTimeLabel;
    @FXML
    private Label totalLandingsLabel;
    @FXML
    private Label totalLandingsDayLabel;
    @FXML
    private Label totalLandingsNightLabel;
    @FXML
    private Label totalIfrTimeLabel;
    @FXML
    private Label totalNightTimeLabel;
    @FXML
    private Label totalTimeAsPicLabel;
    @FXML
    private Label totalTimeAsDualLabel;
    @FXML
    private Label totalTimeAsFiLabel;
    @FXML
    private GridPane engineTypeTimesGrid;
    @FXML
    private Button plusButton;
    @FXML
    private Button startButton;
    @FXML
    private Button stopButton;
    @FXML
    private Button landingDayButton;
    @FXML
    private Button landingNightButton;
    
    private LogbookEntryManager logbookEntryManager;
    private LogbookEntry currentLogbookEntry;

    public void initialize() {
        Connection conn = DatabaseManager.getConnection();
        logbookEntryManager = new LogbookEntryManager(conn);
        updateLogbookData();
    }

    private void updateLogbookData() {
        List<LogbookEntry> entries = logbookEntryManager.getLogbookEntries();
        Logbook logbook = new Logbook(new ArrayList<>(entries));
        LogbookManager.updateLogbookTimes(logbook);

        totalFlightTimeLabel.setText(logbook.getTotalFlightTime());
        totalLandingsLabel.setText(String.valueOf(logbook.getTotalLandings()));
        totalLandingsDayLabel.setText(String.valueOf(logbook.getTotalLandingsDay()));
        totalLandingsNightLabel.setText(String.valueOf(logbook.getTotalLandingsNight()));
        totalIfrTimeLabel.setText(logbook.getTotalIfrTime());
        totalNightTimeLabel.setText(logbook.getTotalNightTime());
        totalTimeAsPicLabel.setText(logbook.getTotalTimeAsPic());
        totalTimeAsDualLabel.setText(logbook.getTotalTimeAsDual());
        totalTimeAsFiLabel.setText(logbook.getTotalTimeAsFI());

        engineTypeTimesGrid.getChildren().clear();
        int row = 0;
        for (Map.Entry<String, String> entry : logbook.getEngineTypeTimes().entrySet()) {
            Label engineTypeLabel = new Label(entry.getKey());
            Label engineTimeLabel = new Label(entry.getValue());
            engineTypeTimesGrid.add(engineTypeLabel, 0, row);
            engineTypeTimesGrid.add(engineTimeLabel, 1, row);
            row++;
        }
    }

    @FXML
    private void handleUserButton() {
        MainApplication.navigateTo("view/UserDetailsView.fxml");
    }

    @FXML
    private void handleLicenseButton() {
        MainApplication.navigateTo("view/LicenseDetailsView.fxml");
    }

    @FXML
    private void handlePermissionButton() {
        MainApplication.navigateTo("view/PermissionDetailsView.fxml");
    }

    @FXML
    private void handleLogbookEntryButton() {
        MainApplication.navigateTo("view/LogbookEntryDetailsView.fxml");
    }

    @FXML
    private void handleAirplaneButton() {
        MainApplication.navigateTo("view/AirplaneDetailsView.fxml");
    }

    @FXML
    private void handlePlusButton() {
        currentLogbookEntry = new LogbookEntry();
        currentLogbookEntry.setFlightDate(LocalDate.now());
        plusButton.setVisible(false);
        startButton.setVisible(true);
    }

    @FXML
    private void handleStartButton() {
        currentLogbookEntry.setDepartureTime(LocalTime.now());
        startButton.setVisible(false);
        stopButton.setVisible(true);
        landingDayButton.setVisible(true);
        landingNightButton.setVisible(true);
    }

    @FXML
    private void handleStopButton() {
        currentLogbookEntry.setArrivalTime(LocalTime.now());
        stopButton.setVisible(false);
        landingDayButton.setVisible(false);
        landingNightButton.setVisible(false);
        logbookEntryManager.addLogbookEntry(currentLogbookEntry);
        updateLogbookData();
        MainApplication.navigateTo("view/LogbookEntryDetailsView.fxml");
    }

    @FXML
    private void handleLandingDayButton() {
        currentLogbookEntry.setNumLandingsDay(currentLogbookEntry.getNumLandingsDay() + 1);
    }

    @FXML
    private void handleLandingNightButton() {
        currentLogbookEntry.setNumLandingsNight(currentLogbookEntry.getNumLandingsNight() + 1);
    }
}
