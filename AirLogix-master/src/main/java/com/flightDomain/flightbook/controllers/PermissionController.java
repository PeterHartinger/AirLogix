package com.flightDomain.flightbook.controllers;


import com.flightDomain.flightbook.MainApplication;
import com.flightDomain.flightbook.db.DatabaseManager;
import com.flightDomain.flightbook.managers.PermissionManager;
import com.flightDomain.flightbook.models.Permission;
import com.flightDomain.flightbook.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.sql.Connection;
import java.time.LocalDate;

public class PermissionController {

    @FXML
    private TextField ratingField;
    @FXML
    private DatePicker dateOfTestPicker;
    @FXML
    private DatePicker validUntilPicker;
    @FXML
    private TextField signatureField;
    @FXML
    private TableView<Permission> permissionTable;
    @FXML
    private TableColumn<Permission, String> ratingColumn;
    @FXML
    private TableColumn<Permission, LocalDate> dateOfTestColumn;
    @FXML
    private TableColumn<Permission, LocalDate> validUntilColumn;
    @FXML
    private TableColumn<Permission, String> signatureColumn;

    @FXML
    private TableColumn<Permission, Void> actionsColumn;

    private PermissionManager permissionManager;
    private ObservableList<Permission> permissionList;

    @FXML
    public void initialize() {
        Connection conn = DatabaseManager.getConnection();
        permissionManager = new PermissionManager(conn);
        permissionList = FXCollections.observableArrayList();
        ratingColumn.setCellValueFactory(cellData -> cellData.getValue().ratingProperty());
        dateOfTestColumn.setCellValueFactory(cellData -> cellData.getValue().dateOfTestProperty());
        validUntilColumn.setCellValueFactory(cellData -> cellData.getValue().validUntilProperty());
        signatureColumn.setCellValueFactory(cellData -> cellData.getValue().signatureProperty());


        actionsColumn.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Bearbeiten");
            private final Button deleteButton = new Button("Löschen");

            {
                editButton.setOnAction(event -> {
                    Permission permission = getTableView().getItems().get(getIndex());
                    populateFields(permission);
                });
                deleteButton.setOnAction(event -> {
                    Permission permission = getTableView().getItems().get(getIndex());
                    permissionManager.deletePermission(permission.getPermissionID());
                    permissionList.remove(permission);
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

        permissionTable.setItems(permissionList);
        loadPermissions();
    }

    @FXML
    private void savePermission() {
        Permission permission = new Permission(
                ratingField.getText(),
                dateOfTestPicker.getValue(),
                validUntilPicker.getValue(),
                signatureField.getText()
        );
        permissionManager.addPermission(permission);
        permissionList.add(permission);
        clearFields();
    }

    @FXML
    private void updatePermission() {
        Permission selectedPermission = permissionTable.getSelectionModel().getSelectedItem();
        if (selectedPermission != null) {
            selectedPermission.setRating(ratingField.getText());
            selectedPermission.setDateOfTest(dateOfTestPicker.getValue());
            selectedPermission.setValidUntil(validUntilPicker.getValue());
            selectedPermission.setSignature(signatureField.getText());
            permissionManager.updatePermission(selectedPermission);
            permissionTable.refresh();
            clearFields();
        }
    }

    private void loadPermissions() {
        permissionList.setAll(permissionManager.getPermissions());
    }

    private void populateFields(Permission permission) {
        ratingField.setText(permission.getRating());
        dateOfTestPicker.setValue(permission.getDateOfTest());
        validUntilPicker.setValue(permission.getValidUntil());
        signatureField.setText(permission.getSignature());
    }

    private void clearFields() {
        ratingField.clear();
        dateOfTestPicker.setValue(null);
        validUntilPicker.setValue(null);
        signatureField.clear();
    }

    @FXML
    private void handleBackToMainView() {
        MainApplication.showMainView();
    }
}
