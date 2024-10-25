package com.flightDomain.flightbook.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class Permission {
    private SimpleIntegerProperty permissionID;
    private SimpleStringProperty rating;
    private SimpleObjectProperty<LocalDate> dateOfTest;
    private SimpleObjectProperty<LocalDate> validUntil;
    private SimpleStringProperty signature;


    // Constructor with permissionID
    public Permission(int permissionID, String rating, LocalDate dateOfTest, LocalDate validUntil, String signature) {
        this.permissionID = new SimpleIntegerProperty(permissionID);
        this.rating = new SimpleStringProperty(rating);
        this.dateOfTest = new SimpleObjectProperty<>(dateOfTest);
        this.validUntil = new SimpleObjectProperty<>(validUntil);
        this.signature = new SimpleStringProperty(signature);

    }

    // Constructor without permissionID (for database-generated ID)
    public Permission(String rating, LocalDate dateOfTest, LocalDate validUntil, String signature) {
        this(0,rating,dateOfTest,validUntil,signature);
    }

    // Getter and Setter methods
    public int getPermissionID() {
        return permissionID.get();
    }

    public void setPermissionID(int permissionID) {
        this.permissionID.set(permissionID);
    }

    public SimpleIntegerProperty permissionIDProperty() {
        return permissionID;
    }

    public String getRating() {
        return rating.get();
    }

    public void setRating(String rating) {
        this.rating.set(rating);
    }

    public SimpleStringProperty ratingProperty() {
        return rating;
    }

    public LocalDate getDateOfTest() {
        return dateOfTest.get();
    }

    public void setDateOfTest(LocalDate dateOfTest) {
        this.dateOfTest.set(dateOfTest);
    }

    public SimpleObjectProperty<LocalDate> dateOfTestProperty() {
        return dateOfTest;
    }

    public LocalDate getValidUntil() {
        return validUntil.get();
    }

    public void setValidUntil(LocalDate validUntil) {
        this.validUntil.set(validUntil);
    }

    public SimpleObjectProperty<LocalDate> validUntilProperty() {
        return validUntil;
    }

    public String getSignature() {
        return signature.get();
    }

    public void setSignature(String signature) {
        this.signature.set(signature);
    }

    public SimpleStringProperty signatureProperty() {
        return signature;
    }

}
