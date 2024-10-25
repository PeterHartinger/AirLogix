package com.flightDomain.flightbook.controllers;

import com.flightDomain.flightbook.MainApplication;
import com.flightDomain.flightbook.db.DatabaseManager;
import com.flightDomain.flightbook.managers.*;
import com.flightDomain.flightbook.models.Airplane;
import com.flightDomain.flightbook.models.LogbookEntry;
import com.flightDomain.flightbook.models.User;
import com.flightDomain.flightbook.utils.TimeManager;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;

import java.sql.Connection;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LogbookEntryController {
    @FXML
    private DatePicker flightDatePicker;
    @FXML
    private ComboBox<String> flightFromComboBox;
    @FXML
    private ComboBox<String> flightToComboBox;
    @FXML
    private ComboBox<Airplane> airplaneComboBox;
    @FXML
    private TextField engineTypeField;
    @FXML
    private ComboBox<User> pilotInCommandComboBox;
    @FXML
    private ComboBox<String> pilotFunctionComboBox;
    @FXML
    private TextField departureTimeField;
    @FXML
    private TextField arrivalTimeField;
    @FXML
    private TextField numLandingsNightField;
    @FXML
    private TextField numLandingsDayField;
    @FXML
    private TextField ifrTimeField;
    @FXML
    private TextField nightTimeField;
    @FXML
    private TextField pilotFunctionTimeField;
    @FXML
    private TextField flightTimeField;
    @FXML
    private TextField remarkField;
    @FXML
    private TableView<LogbookEntry> logbookEntryTable;
    @FXML
    private TableColumn<LogbookEntry, String> flightDateColumn;
    @FXML
    private TableColumn<LogbookEntry, String> flightFromColumn;
    @FXML
    private TableColumn<LogbookEntry, String> flightToColumn;
    @FXML
    private TableColumn<LogbookEntry, String> airplaneColumn;
    @FXML
    private TableColumn<LogbookEntry, String> engineTypeColumn;
    @FXML
    private TableColumn<LogbookEntry, String> departureTimeColumn;
    @FXML
    private TableColumn<LogbookEntry, String> arrivalTimeColumn;
    @FXML
    private TableColumn<LogbookEntry, Void> actionsColumn;
    @FXML
    private TableColumn<LogbookEntry, Integer> numLandingsNightColumn;
    @FXML
    private TableColumn<LogbookEntry, Integer> numLandingsDayColumn;
    @FXML
    private TableColumn<LogbookEntry, String> ifrTimeColumn;
    @FXML
    private TableColumn<LogbookEntry, String> nightTimeColumn;
    @FXML
    private TableColumn<LogbookEntry, String> pilotInCommandColumn;
    @FXML
    private TableColumn<LogbookEntry, String> pilotFunctionColumn;
    @FXML
    private TableColumn<LogbookEntry, String> pilotFunctionTimeColumn;
    @FXML
    private TableColumn<LogbookEntry, String> flightTimeColumn;
    @FXML
    private TableColumn<LogbookEntry, String> remarkColumn;

    private LogbookEntryManager logbookEntryManager;
    private AirplaneManager airplaneManager;
    private AirportManager airportManager;
    private UserManager userManager;
    private ObservableList<LogbookEntry> logbookEntryList;

    @FXML
    public void initialize() {
        Connection conn = DatabaseManager.getConnection();
        logbookEntryManager = new LogbookEntryManager(conn);
        airplaneManager = new AirplaneManager(conn);
        airportManager = new AirportManager(conn);
        userManager = new UserManager(conn);
        logbookEntryList = FXCollections.observableArrayList();

        // Flughäfen für flightFromComboBox und flightToComboBox setzen
        Set<String> allAirports = new HashSet<>(airportManager.getAirports());
        allAirports.addAll(logbookEntryManager.getUniqueAirports()); // Bestehende Flughäfen aus Logbuch

        flightFromComboBox.setItems(FXCollections.observableArrayList(allAirports));
        flightToComboBox.setItems(FXCollections.observableArrayList(allAirports));

        airplaneComboBox.setConverter(new StringConverter<Airplane>() {
            @Override
            public String toString(Airplane airplane) {
                return airplane != null ? airplane.getType() : "";
            }

            @Override
            public Airplane fromString(String type) {
                for (Airplane airplane : airplaneManager.getAirplanes()) {
                    if (airplane.getType().equals(type)) {
                        return airplane;
                    }
                }
                return null;
            }
        });

        airplaneComboBox.setItems(FXCollections.observableArrayList(airplaneManager.getAirplanes()));
        pilotFunctionComboBox.setItems(FXCollections.observableArrayList("PIC", "Dual", "FI"));

        setupTimeField(departureTimeField);
        setupTimeField(arrivalTimeField);
        setupIntegerField(numLandingsNightField);
        setupIntegerField(numLandingsDayField);
        setupOptionalTimeField(ifrTimeField);
        setupOptionalTimeField(nightTimeField);
        setupRequiredTimeField(pilotFunctionTimeField);

        flightFromColumn.setCellValueFactory(cellData -> cellData.getValue().flightFromProperty());
        flightToColumn.setCellValueFactory(cellData -> cellData.getValue().flightToProperty());
        airplaneColumn.setCellValueFactory(cellData -> cellData.getValue().airplaneProperty());
        engineTypeColumn.setCellValueFactory(cellData -> cellData.getValue().engineTypeProperty());
        departureTimeColumn.setCellValueFactory(cellData -> cellData.getValue().departureTimeProperty().asString());
        arrivalTimeColumn.setCellValueFactory(cellData -> cellData.getValue().arrivalTimeProperty().asString());
        numLandingsNightColumn.setCellValueFactory(cellData -> cellData.getValue().numLandingsNightProperty().asObject());
        numLandingsDayColumn.setCellValueFactory(cellData -> cellData.getValue().numLandingsDayProperty().asObject());
        ifrTimeColumn.setCellValueFactory(cellData -> cellData.getValue().ifrTimeProperty().asString());
        nightTimeColumn.setCellValueFactory(cellData -> cellData.getValue().nightTimeProperty().asString());
        pilotInCommandColumn.setCellValueFactory(cellData -> cellData.getValue().pilotInCommandProperty());
        pilotFunctionColumn.setCellValueFactory(cellData -> cellData.getValue().pilotFunctionProperty());
        pilotFunctionTimeColumn.setCellValueFactory(cellData -> cellData.getValue().pilotFunctionTimeProperty().asString());
        flightTimeColumn.setCellValueFactory(cellData -> cellData.getValue().flightTimeProperty());
        remarkColumn.setCellValueFactory(cellData -> cellData.getValue().remarkProperty());
        flightDateColumn.setCellValueFactory(cellData -> cellData.getValue().flightDateProperty().asString()); // Hinzugefügt

        actionsColumn.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Bearbeiten");
            private final Button deleteButton = new Button("Löschen");

            {
                editButton.setOnAction(event -> {
                    LogbookEntry entry = getTableView().getItems().get(getIndex());
                    populateFields(entry);
                });
                deleteButton.setOnAction(event -> {
                    LogbookEntry entry = getTableView().getItems().get(getIndex());
                    logbookEntryManager.deleteLogbookEntry(entry.getFlightID());
                    logbookEntryList.remove(entry);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox buttons = new HBox(editButton, deleteButton);
                    setGraphic(buttons);
                }
            }
        });

        logbookEntryTable.setItems(logbookEntryList);
        loadLogbookEntries();

        // Benutzer für pilotInCommandComboBox setzen
        List<User> users = userManager.getUsers();
        pilotInCommandComboBox.setItems(FXCollections.observableArrayList(users));
        pilotInCommandComboBox.setConverter(new StringConverter<User>() {
            @Override
            public String toString(User user) {
                return user != null ? user.getName() : "";
            }

            @Override
            public User fromString(String name) {
                for (User user : users) {
                    if (user.getName().equals(name)) {
                        return user;
                    }
                }
                return null;
            }
        });

        // Listener für airplaneComboBox hinzufügen
        airplaneComboBox.valueProperty().addListener((obs, oldAirplane, newAirplane) -> {
            if (newAirplane != null) {
                String engineType = newAirplane.getEngineType();
                if (engineType != null && !engineType.isEmpty()) {
                    engineTypeField.setText(engineType);
                } else {
                    // Verwenden Sie Platform.runLater, um die Änderung der Auswahl außerhalb des Listeners durchzuführen
                    Platform.runLater(() -> {
                        showAlert("Kein Motortyp", "Das ausgewählte Flugzeug hat keinen Motortyp. Bitte wählen Sie ein anderes Flugzeug.");
                        airplaneComboBox.getSelectionModel().clearSelection();
                        engineTypeField.clear();
                    });
                }
            } else {
                engineTypeField.clear();
            }
        });

        // Listener für departureTimeField und arrivalTimeField hinzufügen
        departureTimeField.textProperty().addListener((obs, oldTime, newTime) -> calculateAndDisplayFlightTime());
        arrivalTimeField.textProperty().addListener((obs, oldTime, newTime) -> calculateAndDisplayFlightTime());
    }

    private void setupTimeField(TextField timeField) {
        timeField.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            // Erlaube Teileingaben und prüfe später die vollständige Eingabe
            if (newText.matches("^([0-2]?[0-9]?)?(:[0-5]?[0-9]?)?$") || newText.isEmpty()) {
                return change;
            }
            return null;
        }));

        // Optional: Set a prompt text to guide the user
        timeField.setPromptText("HH:mm");

        // Füge einen Fokus-Listener hinzu, um die Validierung bei Fokusverlust vorzunehmen
        timeField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) { // Fokus verloren
                String text = timeField.getText();
                if (!text.matches("^([01]?\\d|2[0-3]):[0-5]\\d$")) {
                    if (timeField != ifrTimeField && timeField != nightTimeField) { // Ausnahme für ifrTimeField und nightTimeField
                        timeField.setStyle("-fx-border-color: red;"); // Markiere das Feld rot, wenn die Eingabe ungültig ist
                    }
                } else {
                    timeField.setStyle(""); // Entferne die Markierung, wenn die Eingabe gültig ist
                }
            }
        });
    }

    private void setupOptionalTimeField(TextField timeField) {
        timeField.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            // Erlaube Teileingaben und prüfe später die vollständige Eingabe
            if (newText.matches("^([0-2]?[0-9]?)?(:[0-5]?[0-9]?)?$") || newText.isEmpty()) {
                return change;
            }
            return null;
        }));

        // Optional: Set a prompt text to guide the user
        timeField.setPromptText("HH:mm");

        // Füge einen Fokus-Listener hinzu, um die Validierung bei Fokusverlust vorzunehmen
        timeField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            String text = timeField.getText();
            if (!newVal && !text.isEmpty() && !text.matches("^([01]?\\d|2[0-3]):[0-5]\\d$")) {
                timeField.setStyle("-fx-border-color: red;"); // Markiere das Feld rot, wenn die Eingabe ungültig ist
            } else {
                timeField.setStyle(""); // Entferne die Markierung, wenn die Eingabe gültig ist oder leer ist
            }
        });
    }

    private void setupRequiredTimeField(TextField timeField) {
        timeField.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            // Erlaube Teileingaben und prüfe später die vollständige Eingabe
            if (newText.matches("^([0-2]?[0-9]?)?(:[0-5]?[0-9]?)?$") || newText.isEmpty()) {
                return change;
            }
            return null;
        }));

        // Optional: Set a prompt text to guide the user
        timeField.setPromptText("HH:mm");

        // Füge einen Fokus-Listener hinzu, um die Validierung bei Fokusverlust vorzunehmen
        timeField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            String text = timeField.getText();
            if (!newVal && (text.isEmpty() || !text.matches("^([01]?\\d|2[0-3]):[0-5]\\d$"))) {
                timeField.setStyle("-fx-border-color: red;"); // Markiere das Feld rot, wenn die Eingabe ungültig ist oder leer ist
            } else {
                timeField.setStyle(""); // Entferne die Markierung, wenn die Eingabe gültig ist
            }
        });
    }

    private void setupIntegerField(TextField integerField) {
        integerField.setTextFormatter(new TextFormatter<>(c -> {
            if (c.getControlNewText().matches("\\d*")) {
                return c;
            }
            return null;
        }));
    }

    @FXML
    private void saveLogbookEntry() {
        LogbookEntry entry = new LogbookEntry();
        entry.setFlightFrom(flightFromComboBox.getValue());
        entry.setFlightTo(flightToComboBox.getValue());
        entry.setAirplane(airplaneComboBox.getValue().getType());
        entry.setEngineType(engineTypeField.getText());
        entry.setDepartureTime(LocalTime.parse(departureTimeField.getText(), DateTimeFormatter.ofPattern("HH:mm")));
        entry.setArrivalTime(LocalTime.parse(arrivalTimeField.getText(), DateTimeFormatter.ofPattern("HH:mm")));
        entry.setFlightDate(flightDatePicker.getValue()); // Hinzugefügt

        // Set default value if fields are empty
        entry.setNumLandingsNight(numLandingsNightField.getText().isEmpty() ? 0 : Integer.parseInt(numLandingsNightField.getText()));
        entry.setNumLandingsDay(numLandingsDayField.getText().isEmpty() ? 0 : Integer.parseInt(numLandingsDayField.getText()));

        entry.setIfrTime(ifrTimeField.getText().isEmpty() ? null : LocalTime.parse(ifrTimeField.getText(), DateTimeFormatter.ofPattern("HH:mm")));
        entry.setNightTime(nightTimeField.getText().isEmpty() ? null : LocalTime.parse(nightTimeField.getText(), DateTimeFormatter.ofPattern("HH:mm")));
        entry.setPilotInCommand(pilotInCommandComboBox.getValue().getName());
        entry.setPilotFunction(pilotFunctionComboBox.getValue());

        // Handle Duration for pilotFunctionTime
        entry.setPilotFunctionTime(pilotFunctionTimeField.getText().isEmpty() ? Duration.ZERO : TimeManager.parseTime(pilotFunctionTimeField.getText()));

        entry.setFlightTime(flightTimeField.getText());
        entry.setRemark(remarkField.getText());

        logbookEntryManager.addLogbookEntry(entry);
        logbookEntryList.add(entry);
        clearFields();
    }

    @FXML
    private void updateLogbookEntry() {
        LogbookEntry selectedEntry = logbookEntryTable.getSelectionModel().getSelectedItem();
        if (selectedEntry != null) {
            selectedEntry.setFlightFrom(flightFromComboBox.getValue());
            selectedEntry.setFlightTo(flightToComboBox.getValue());
            selectedEntry.setAirplane(airplaneComboBox.getValue().getType());
            selectedEntry.setEngineType(engineTypeField.getText());
            selectedEntry.setDepartureTime(LocalTime.parse(departureTimeField.getText(), DateTimeFormatter.ofPattern("HH:mm")));
            selectedEntry.setArrivalTime(LocalTime.parse(arrivalTimeField.getText(), DateTimeFormatter.ofPattern("HH:mm")));
            selectedEntry.setFlightDate(flightDatePicker.getValue()); // Hinzugefügt

            // Set default value if fields are empty
            selectedEntry.setNumLandingsNight(numLandingsNightField.getText().isEmpty() ? 0 : Integer.parseInt(numLandingsNightField.getText()));
            selectedEntry.setNumLandingsDay(numLandingsDayField.getText().isEmpty() ? 0 : Integer.parseInt(numLandingsDayField.getText()));

            selectedEntry.setIfrTime(ifrTimeField.getText().isEmpty() ? null : LocalTime.parse(ifrTimeField.getText(), DateTimeFormatter.ofPattern("HH:mm")));
            selectedEntry.setNightTime(nightTimeField.getText().isEmpty() ? null : LocalTime.parse(nightTimeField.getText(), DateTimeFormatter.ofPattern("HH:mm")));
            selectedEntry.setPilotInCommand(pilotInCommandComboBox.getValue().getName());
            selectedEntry.setPilotFunction(pilotFunctionComboBox.getValue());

            // Handle Duration for pilotFunctionTime
            selectedEntry.setPilotFunctionTime(pilotFunctionTimeField.getText().isEmpty() ? Duration.ZERO : TimeManager.parseTime(pilotFunctionTimeField.getText()));

            selectedEntry.setFlightTime(flightTimeField.getText());
            selectedEntry.setRemark(remarkField.getText());

            logbookEntryManager.updateLogbookEntry(selectedEntry);
            logbookEntryTable.refresh();
            clearFields();
        }
    }

    private void loadLogbookEntries() {
        logbookEntryList.setAll(logbookEntryManager.getLogbookEntries());
    }

    private void populateFields(LogbookEntry entry) {
        flightFromComboBox.setValue(entry.getFlightFrom());
        flightToComboBox.setValue(entry.getFlightTo());
        airplaneComboBox.setValue(airplaneManager.getAirplaneByType(entry.getAirplane()));
        engineTypeField.setText(entry.getEngineType());
        departureTimeField.setText(entry.getDepartureTime() != null ? entry.getDepartureTime().format(DateTimeFormatter.ofPattern("HH:mm")) : "");
        arrivalTimeField.setText(entry.getArrivalTime() != null ? entry.getArrivalTime().format(DateTimeFormatter.ofPattern("HH:mm")) : "");
        numLandingsNightField.setText(String.valueOf(entry.getNumLandingsNight()));
        numLandingsDayField.setText(String.valueOf(entry.getNumLandingsDay()));
        ifrTimeField.setText(entry.getIfrTime() != null ? entry.getIfrTime().format(DateTimeFormatter.ofPattern("HH:mm")) : "");
        nightTimeField.setText(entry.getNightTime() != null ? entry.getNightTime().format(DateTimeFormatter.ofPattern("HH:mm")) : "");
        flightDatePicker.setValue(entry.getFlightDate()); // Hinzugefügt
        // Pilot in Command in der ComboBox anzeigen
        for (User user : pilotInCommandComboBox.getItems()) {
            if (user.getName().equals(entry.getPilotInCommand())) {
                pilotInCommandComboBox.setValue(user);
                break;
            }
        }
        pilotFunctionComboBox.setValue(entry.getPilotFunction());
        pilotFunctionTimeField.setText(entry.getPilotFunctionTime() != null ? TimeManager.formatDuration(entry.getPilotFunctionTime()) : "");
        flightTimeField.setText(entry.getFlightTime());
        remarkField.setText(entry.getRemark());
    }

    private void clearFields() {
        flightFromComboBox.setValue(null);
        flightToComboBox.setValue(null);
        airplaneComboBox.setValue(null);
        engineTypeField.clear();
        departureTimeField.clear();
        arrivalTimeField.clear();
        numLandingsNightField.clear();
        numLandingsDayField.clear();
        ifrTimeField.clear();
        nightTimeField.clear();
        pilotInCommandComboBox.setValue(null);
        pilotFunctionComboBox.setValue(null);
        pilotFunctionTimeField.clear();
        flightTimeField.clear();
        remarkField.clear();
        flightDatePicker.setValue(null); // Hinzugefügt
    }

    private void calculateAndDisplayFlightTime() {
        try {
            LocalTime departureTime = LocalTime.parse(departureTimeField.getText(), DateTimeFormatter.ofPattern("HH:mm"));
            LocalTime arrivalTime = LocalTime.parse(arrivalTimeField.getText(), DateTimeFormatter.ofPattern("HH:mm"));
            Duration flightDuration = TimeManager.calculateDuration(departureTime, arrivalTime);
            flightTimeField.setText(TimeManager.formatDuration(flightDuration));
        } catch (Exception e) {
            flightTimeField.clear();
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void handleBackToMainView() {
        MainApplication.showMainView();
    }
}
