package com.flightDomain.flightbook.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Airplane {
    private SimpleIntegerProperty planeID;
    private StringProperty type;
    private StringProperty registration;
    private StringProperty engineType;

    public Airplane(int planeID, String type, String registration, String engineType) {
        this.planeID = new SimpleIntegerProperty(planeID);
        this.type = new SimpleStringProperty(type);
        this.registration = new SimpleStringProperty(registration);
        this.engineType = new SimpleStringProperty(engineType);
    }
    public Airplane(String type, String registration, String engineType) {
        this(0, type, registration, engineType);
    }

    // PlaneId property
    public int getPlaneID() {
        return planeID.get();
    }

    public void setPlaneID(int planeID) {
        this.planeID.set(planeID);
    }

    public SimpleIntegerProperty planeIDProperty() {
        return planeID;
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

    // Registration property
    public String getRegistration() {
        return registration.get();
    }

    public void setRegistration(String registration) {
        this.registration.set(registration);
    }

    public StringProperty registrationProperty() {
        return registration;
    }

    // EngineType property
    public String getEngineType() {
        return engineType.get();
    }

    public void setEngineType(String engineType) {
        this.engineType.set(engineType);
    }

    public StringProperty engineTypeProperty() {
        return engineType;
    }

    @Override
    public String toString() {
        return "Airplane{" +
                "planeId=" + planeID +
                ", type=" + type +
                ", registration=" + registration +
                ", engineType=" + engineType +
                '}';
    }


}
