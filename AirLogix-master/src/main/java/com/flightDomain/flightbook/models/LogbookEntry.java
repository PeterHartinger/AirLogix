package com.flightDomain.flightbook.models;

import javafx.beans.property.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class LogbookEntry {
    private SimpleIntegerProperty flightID;
    private SimpleStringProperty flightFrom;
    private SimpleStringProperty flightTo;
    private SimpleStringProperty airplane;
    private SimpleStringProperty engineType;
    private ObjectProperty<LocalTime> departureTime;
    private ObjectProperty<LocalTime> arrivalTime;
    private SimpleIntegerProperty numLandingsNight;
    private SimpleIntegerProperty numLandingsDay;
    private ObjectProperty<LocalTime> ifrTime;
    private ObjectProperty<LocalTime> nightTime;
    private SimpleStringProperty pilotInCommand;
    private SimpleStringProperty pilotFunction;
    private ObjectProperty<Duration> pilotFunctionTime; // geändert zu Duration
    private SimpleStringProperty flightTime;
    private SimpleStringProperty remark;
    private ObjectProperty<LocalDate> flightDate; // Hinzugefügt

    public LogbookEntry() {
        this.flightID = new SimpleIntegerProperty();
        this.flightFrom = new SimpleStringProperty();
        this.flightTo = new SimpleStringProperty();
        this.airplane = new SimpleStringProperty();
        this.engineType = new SimpleStringProperty();
        this.departureTime = new SimpleObjectProperty<>();
        this.arrivalTime = new SimpleObjectProperty<>();
        this.numLandingsNight = new SimpleIntegerProperty();
        this.numLandingsDay = new SimpleIntegerProperty();
        this.ifrTime = new SimpleObjectProperty<>();
        this.nightTime = new SimpleObjectProperty<>();
        this.pilotInCommand = new SimpleStringProperty();
        this.pilotFunction = new SimpleStringProperty();
        this.pilotFunctionTime = new SimpleObjectProperty<>(); // geändert zu Duration
        this.flightTime = new SimpleStringProperty();
        this.remark = new SimpleStringProperty();
        this.flightDate = new SimpleObjectProperty<>(); // Hinzugefügt
    }

    public LocalDate getFlightDate() {
        return flightDate.get();
    }

    public void setFlightDate(LocalDate flightDate) {
        this.flightDate.set(flightDate);
    }

    public ObjectProperty<LocalDate> flightDateProperty() {
        return flightDate;
    }

    public String getFlightTo() {
        return flightTo.get();
    }

    public SimpleStringProperty flightToProperty() {
        return flightTo;
    }

    public void setFlightTo(String flightTo) {
        this.flightTo.set(flightTo);
    }

    public String getAirplane() {
        return airplane.get();
    }

    public SimpleStringProperty airplaneProperty() {
        return airplane;
    }

    public void setAirplane(String airplane) {
        this.airplane.set(airplane);
    }

    public String getEngineType() {
        return engineType.get();
    }

    public SimpleStringProperty engineTypeProperty() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType.set(engineType);
    }

    public LocalTime getDepartureTime() {
        return departureTime.get();
    }

    public ObjectProperty<LocalTime> departureTimeProperty() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime.set(departureTime);
    }

    public LocalTime getArrivalTime() {
        return arrivalTime.get();
    }

    public ObjectProperty<LocalTime> arrivalTimeProperty() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime.set(arrivalTime);
    }

    public int getNumLandingsNight() {
        return numLandingsNight.get();
    }

    public SimpleIntegerProperty numLandingsNightProperty() {
        return numLandingsNight;
    }

    public void setNumLandingsNight(int numLandingsNight) {
        this.numLandingsNight.set(numLandingsNight);
    }

    public int getNumLandingsDay() {
        return numLandingsDay.get();
    }

    public SimpleIntegerProperty numLandingsDayProperty() {
        return numLandingsDay;
    }

    public void setNumLandingsDay(int numLandingsDay) {
        this.numLandingsDay.set(numLandingsDay);
    }

    public LocalTime getIfrTime() {
        return ifrTime.get();
    }

    public ObjectProperty<LocalTime> ifrTimeProperty() {
        return ifrTime;
    }

    public void setIfrTime(LocalTime ifrTime) {
        this.ifrTime.set(ifrTime);
    }

    public LocalTime getNightTime() {
        return nightTime.get();
    }

    public ObjectProperty<LocalTime> nightTimeProperty() {
        return nightTime;
    }

    public void setNightTime(LocalTime nightTime) {
        this.nightTime.set(nightTime);
    }

    public String getPilotInCommand() {
        return pilotInCommand.get();
    }

    public SimpleStringProperty pilotInCommandProperty() {
        return pilotInCommand;
    }

    public void setPilotInCommand(String pilotInCommand) {
        this.pilotInCommand.set(pilotInCommand);
    }

    public String getPilotFunction() {
        return pilotFunction.get();
    }

    public SimpleStringProperty pilotFunctionProperty() {
        return pilotFunction;
    }

    public void setPilotFunction(String pilotFunction) {
        this.pilotFunction.set(pilotFunction);
    }

    public Duration getPilotFunctionTime() {
        return pilotFunctionTime.get();
    }

    public ObjectProperty<Duration> pilotFunctionTimeProperty() {
        return pilotFunctionTime;
    }

    public void setPilotFunctionTime(Duration pilotFunctionTime) {
        this.pilotFunctionTime.set(pilotFunctionTime);
    }

    public String getFlightTime() {
        return flightTime.get();
    }

    public SimpleStringProperty flightTimeProperty() {
        return flightTime;
    }

    public void setFlightTime(String flightTime) {
        this.flightTime.set(flightTime);
    }

    public String getRemark() {
        return remark.get();
    }

    public SimpleStringProperty remarkProperty() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark.set(remark);
    }

    public int getFlightID() {
        return flightID.get();
    }

    public String getFlightFrom() {
        return flightFrom.get();
    }

    public SimpleStringProperty flightFromProperty() {
        return flightFrom;
    }

    public void setFlightFrom(String flightFrom) {
        this.flightFrom.set(flightFrom);
    }

    public SimpleIntegerProperty flightIDProperty() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID.set(flightID);
    }
}
