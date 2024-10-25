package com.flightDomain.flightbook.controllers;

import com.flightDomain.flightbook.MainApplication;
import com.flightDomain.flightbook.db.DatabaseManager;
import com.flightDomain.flightbook.managers.AirplaneManager;
import com.flightDomain.flightbook.managers.EngineTypeManager;
import com.flightDomain.flightbook.models.Airplane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.sql.Connection;

public class AirplaneController {

    @FXML
    private TextField typeField;
    @FXML
    private TextField registrationField;
    @FXML
    private ComboBox<String> engineTypeComboBox;
    @FXML
    private TableView<Airplane> airplaneTable;
    @FXML
    private TableColumn<Airplane, String> typeColumn;
    @FXML
    private TableColumn<Airplane, String> registrationColumn;
    @FXML
    private TableColumn<Airplane, String> engineTypeColumn;
    @FXML
    private TableColumn<Airplane, Void> actionsColumn;

    private AirplaneManager airplaneManager;
    private EngineTypeManager engineTypeManager;
    private ObservableList<Airplane> airplaneList;

    @FXML
    public void initialize() {
        Connection conn = DatabaseManager.getConnection();
        airplaneManager = new AirplaneManager(conn);
        engineTypeManager = new EngineTypeManager(conn);
        airplaneList = FXCollections.observableArrayList();

        engineTypeComboBox.setItems(FXCollections.observableArrayList(engineTypeManager.getEngineTypes()));
        engineTypeComboBox.setEditable(true);
        setupAutoComplete();

        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        registrationColumn.setCellValueFactory(cellData -> cellData.getValue().registrationProperty());
        engineTypeColumn.setCellValueFactory(cellData -> cellData.getValue().engineTypeProperty());

        actionsColumn.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Bearbeiten");
            private final Button deleteButton = new Button("Löschen");

            {
                editButton.setOnAction(event -> {
                    Airplane airplane = getTableView().getItems().get(getIndex());
                    populateFields(airplane);
                });
                deleteButton.setOnAction(event -> {
                    Airplane airplane = getTableView().getItems().get(getIndex());
                    airplaneManager.deleteAirplane(airplane.getPlaneID());
                    airplaneList.remove(airplane);
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

        airplaneTable.setItems(airplaneList);
        loadAirplanes();
    }

    @FXML
    private void saveAirplane() {
        String engineType = engineTypeComboBox.getEditor().getText();
        if (!engineTypeManager.getEngineTypes().contains(engineType)) {
            engineTypeManager.addEngineType(engineType);
            engineTypeComboBox.getItems().add(engineType);
        }
        int engineTypeId = engineTypeManager.getEngineTypeId(engineType);
        if (engineTypeId != -1) {
            Airplane airplane = new Airplane(
                    typeField.getText(),
                    registrationField.getText(),
                    engineType
            );
            airplaneManager.addAirplane(airplane);
            airplaneList.add(airplane);
            clearFields();
        } else {
            System.out.println("Failed to retrieve EngineType ID.");
        }
    }

    @FXML
    private void updateAirplane() {
        Airplane selectedAirplane = airplaneTable.getSelectionModel().getSelectedItem();
        if (selectedAirplane != null) {
            selectedAirplane.setType(typeField.getText());
            selectedAirplane.setRegistration(registrationField.getText());
            selectedAirplane.setEngineType(engineTypeComboBox.getEditor().getText());
            airplaneManager.updateAirplane(selectedAirplane);
            airplaneTable.refresh();
            clearFields();
        }
    }

    private void loadAirplanes() {
        airplaneList.setAll(airplaneManager.getAirplanes());
    }

    private void populateFields(Airplane airplane) {
        typeField.setText(airplane.getType());
        registrationField.setText(airplane.getRegistration());
        engineTypeComboBox.setValue(airplane.getEngineType());
    }

    private void clearFields() {
        typeField.clear();
        registrationField.clear();
        engineTypeComboBox.setValue(null);
    }

    @FXML
    private void handleBackToMainView() {
        MainApplication.showMainView();
    }

    private void setupAutoComplete() {
        ComboBox<String> comboBox = new ComboBox<>();
        TextField textField = new TextField();

        // Bind the ComboBox items to some observable list
        ObservableList<String> items = FXCollections.observableArrayList("Item1", "Item2", "Item3");
        comboBox.setItems(items);

        // Add listener to text field
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Avoid recursive call by checking if the new value is different
            if (!comboBox.getValue().equals(newValue)) {
                comboBox.setValue(newValue);
            }
        });

        // Add listener to combo box
        comboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Avoid recursive call by checking if the new value is different
            if (!textField.getText().equals(newValue)) {
                textField.setText(newValue);
            }
        });
    }
}
