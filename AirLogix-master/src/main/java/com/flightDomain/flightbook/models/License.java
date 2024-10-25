package com.flightDomain.flightbook.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class License {
    private SimpleIntegerProperty licenseID;
    private StringProperty type;
    private SimpleObjectProperty<LocalDate> dateOfIssue;
    private StringProperty number;
    private StringProperty authority;

    public License(int licenseId, String type, LocalDate dateOfIssue, String number, String authority) {
        this.licenseID = new SimpleIntegerProperty(licenseId);
        this.type = new SimpleStringProperty(type);
        this.dateOfIssue = new SimpleObjectProperty<>(dateOfIssue);
        this.number = new SimpleStringProperty(number);
        this.authority = new SimpleStringProperty(authority);
    }
    public License(String type, LocalDate dateOfIssue, String number, String authority){
        this(0,type,dateOfIssue,number,authority);
    }
    public int getLicenseID() {
        return licenseID.get();
    }
    public void setLicenseID(int licenseID) {
        this.licenseID.set(licenseID);
    }
    public SimpleIntegerProperty licenseIDProperty() {
        return licenseID;
    }
    // Type property
    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public StringProperty typeProperty() {
        return type;
    }

    // DateOfIssue property
    public LocalDate getDateOfIssue() {
        return dateOfIssue.get();
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue.set(dateOfIssue);
    }

    public SimpleObjectProperty<LocalDate> dateOfIssueProperty() {
        return dateOfIssue;
    }

    // Number property
    public String getNumber() {
        return number.get();
    }

    public void setNumber(String number) {
        this.number.set(number);
    }

    public StringProperty numberProperty() {
        return number;
    }

    // Signature property
    public String getAuthority() {
        return authority.get();
    }

    public void setAuthority(String authority) {
        this.authority.set(authority);
    }

    public StringProperty authorityProperty() {
        return authority;
    }

    @Override
    public String toString() {
        return "Licence{" +
                "type=" + type +
                ", dateOfIssue=" + dateOfIssue +
                ", number=" + number +
                ", authority=" + authority +
                '}';
    }


}
