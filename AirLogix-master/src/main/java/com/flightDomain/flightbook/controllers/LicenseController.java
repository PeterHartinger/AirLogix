package com.flightDomain.flightbook.controllers;

import com.flightDomain.flightbook.MainApplication;
import com.flightDomain.flightbook.db.DatabaseManager;
import com.flightDomain.flightbook.managers.LicenseManager;
import com.flightDomain.flightbook.models.License;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.sql.Connection;
import java.time.LocalDate;

public class LicenseController {

    @FXML
    private TextField typeField;
    @FXML
    private DatePicker dateOfIssuePicker;
    @FXML
    private TextField numberField;
    @FXML
    private TextField authorityField;
    @FXML
    private TableView<License> licenseTable;
    @FXML
    private TableColumn<License, String> typeColumn;
    @FXML
    private TableColumn<License, LocalDate> dateOfIssueColumn;
    @FXML
    private TableColumn<License, String> numberColumn;
    @FXML
    private TableColumn<License, String> authorityColumn;
    @FXML
    private TableColumn<License, Void> actionsColumn;

    private LicenseManager licenseManager;
    private ObservableList<License> licenseList;

    @FXML
    public void initialize() {
        Connection conn = DatabaseManager.getConnection();
        licenseManager = new LicenseManager(conn);
        licenseList = FXCollections.observableArrayList();

        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        dateOfIssueColumn.setCellValueFactory(cellData -> cellData.getValue().dateOfIssueProperty());
        numberColumn.setCellValueFactory(cellData -> cellData.getValue().numberProperty());
        authorityColumn.setCellValueFactory(cellData -> cellData.getValue().authorityProperty());

        actionsColumn.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Bearbeiten");
            private final Button deleteButton = new Button("Löschen");

            {
                editButton.setOnAction(event -> {
                    License license = getTableView().getItems().get(getIndex());
                    populateFields(license);
                });
                deleteButton.setOnAction(event -> {
                    License license = getTableView().getItems().get(getIndex());
                    licenseManager.deleteLicense(license.getLicenseID());
                    licenseList.remove(license);
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

        licenseTable.setItems(licenseList);
        loadLicenses();
    }

    @FXML
    private void saveLicense() {
        License license = new License(
                typeField.getText(),
                dateOfIssuePicker.getValue(),
                numberField.getText(),
                authorityField.getText()
        );
        licenseManager.addLicense(license);
        licenseList.add(license);
        clearFields();
    }

    @FXML
    private void updateLicense() {
        License selectedLicense = licenseTable.getSelectionModel().getSelectedItem();
        if (selectedLicense != null) {
            selectedLicense.setType(typeField.getText());
            selectedLicense.setDateOfIssue(dateOfIssuePicker.getValue());
            selectedLicense.setNumber(numberField.getText());
            selectedLicense.setAuthority(authorityField.getText());
            licenseManager.updateLicense(selectedLicense);
            licenseTable.refresh();
            clearFields();
        }
    }

    private void loadLicenses() {
        licenseList.setAll(licenseManager.getLicenses());
    }

    private void populateFields(License license) {
        typeField.setText(license.getType());
        dateOfIssuePicker.setValue(license.getDateOfIssue());
        numberField.setText(license.getNumber());
        authorityField.setText(license.getAuthority());
    }

    private void clearFields() {
        typeField.clear();
        dateOfIssuePicker.setValue(null);
        numberField.clear();
        authorityField.clear();
    }

    @FXML
    private void handleBackToMainView() {
        MainApplication.showMainView();
    }
}

