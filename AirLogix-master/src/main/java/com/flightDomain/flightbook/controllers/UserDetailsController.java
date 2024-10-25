package com.flightDomain.flightbook.controllers;

import com.flightDomain.flightbook.MainApplication;
import com.flightDomain.flightbook.db.DatabaseManager;
import com.flightDomain.flightbook.managers.UserManager;
import com.flightDomain.flightbook.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.sql.Connection;

public class UserDetailsController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField addressField;
    @FXML
    private DatePicker birthdatePicker;
    @FXML
    private TextField birthplaceField;
    @FXML
    private TextField nationalityField;
    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> nameColumn;
    @FXML
    private TableColumn<User, String> addressColumn;
    @FXML
    private TableColumn<User, String> birthdateColumn;
    @FXML
    private TableColumn<User, String> birthplaceColumn;
    @FXML
    private TableColumn<User, String> nationalityColumn;
    @FXML
    private TableColumn<User, Void> actionsColumn;

    private UserManager userManager;
    private ObservableList<User> userList;



    @FXML
    public void initialize() {
        Connection conn = DatabaseManager.getConnection();
        userManager = new UserManager(conn);
        userList = FXCollections.observableArrayList();
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        birthdateColumn.setCellValueFactory(cellData -> cellData.getValue().birthdateProperty().asString());
        birthplaceColumn.setCellValueFactory(cellData -> cellData.getValue().birthplaceProperty());
        nationalityColumn.setCellValueFactory(cellData -> cellData.getValue().nationalityProperty());

        actionsColumn.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Bearbeiten");
            private final Button deleteButton = new Button("Löschen");

            {
                editButton.setOnAction(event -> {
                    User user = getTableView().getItems().get(getIndex());
                    populateFields(user);
                });
                deleteButton.setOnAction(event -> {
                    User user = getTableView().getItems().get(getIndex());
                    userManager.deleteUser(user.getId());
                    userList.remove(user);
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

        userTable.setItems(userList);
        loadUsers();
    }



    @FXML
    private void saveUser() {
        User user = new User(
                nameField.getText(),
                addressField.getText(),
                birthdatePicker.getValue(),
                birthplaceField.getText(),
                nationalityField.getText()
        );
        userManager.addUser(user);
        userList.add(user);
        clearFields();
    }

    @FXML
    private void updateUser() {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            selectedUser.setName(nameField.getText());
            selectedUser.setAddress(addressField.getText());
            selectedUser.setBirthdate(birthdatePicker.getValue());
            selectedUser.setBirthplace(birthplaceField.getText());
            selectedUser.setNationality(nationalityField.getText());
            userManager.updateUser(selectedUser);
            userTable.refresh();
            clearFields();
        }
    }

    private void loadUsers() {
        userList.setAll(userManager.getUsers());
    }

    private void populateFields(User user) {
        nameField.setText(user.getName());
        addressField.setText(user.getAddress());
        birthdatePicker.setValue(user.getBirthdate());
        birthplaceField.setText(user.getBirthplace());
        nationalityField.setText(user.getNationality());
    }

    private void clearFields() {
        nameField.clear();
        addressField.clear();
        birthdatePicker.setValue(null);
        birthplaceField.clear();
        nationalityField.clear();
    }

    @FXML
    private void handleBackToMainView() {
        MainApplication.showMainView();
    }
}
