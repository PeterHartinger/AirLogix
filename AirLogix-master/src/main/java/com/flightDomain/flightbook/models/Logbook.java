package com.flightDomain.flightbook.models;

import javafx.beans.property.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Logbook {
    private final SimpleStringProperty totalFlightTime;
    private final SimpleIntegerProperty totalLandings;
    private final SimpleIntegerProperty totalLandingsDay;
    private final SimpleIntegerProperty totalLandingsNight;
    private final SimpleStringProperty totalIfrTime;
    private final SimpleStringProperty totalNightTime;
    private final SimpleStringProperty totalTimeAsPic;
    private final SimpleStringProperty totalTimeAsDual;
    private final SimpleStringProperty totalTimeAsFI;
    private final ArrayList<LogbookEntry> flightEntries;
    private final SimpleStringProperty totalTimeDay;
    private final SimpleStringProperty totalTimeNight;
    private final SimpleObjectProperty<LocalDate> startedOn;
    private final SimpleObjectProperty<LocalDate> finishedOn;
    private final SimpleObjectProperty<User> owner;
    private final Map<String, String> engineTypeTimes;

    public Logbook(ArrayList<LogbookEntry> flightEntries) {
        this.flightEntries = flightEntries;
        this.totalFlightTime = new SimpleStringProperty();
        this.totalLandings = new SimpleIntegerProperty();
        this.totalLandingsDay = new SimpleIntegerProperty();
        this.totalLandingsNight = new SimpleIntegerProperty();
        this.totalIfrTime = new SimpleStringProperty();
        this.totalNightTime = new SimpleStringProperty();
        this.totalTimeAsPic = new SimpleStringProperty();
        this.totalTimeAsDual = new SimpleStringProperty();
        this.totalTimeAsFI = new SimpleStringProperty();
        this.totalTimeDay = new SimpleStringProperty();
        this.totalTimeNight = new SimpleStringProperty();
        this.startedOn = new SimpleObjectProperty<>();
        this.finishedOn = new SimpleObjectProperty<>();
        this.owner = new SimpleObjectProperty<>();
        this.engineTypeTimes = new HashMap<>();
    }

    // Getter and Setter methods
    public String getTotalFlightTime() {
        return totalFlightTime.get();
    }

    public void setTotalFlightTime(String totalFlightTime) {
        this.totalFlightTime.set(totalFlightTime);
    }

    public SimpleStringProperty totalFlightTimeProperty() {
        return totalFlightTime;
    }

    public int getTotalLandings() {
        return totalLandings.get();
    }

    public void setTotalLandings(int totalLandings) {
        this.totalLandings.set(totalLandings);
    }

    public SimpleIntegerProperty totalLandingsProperty() {
        return totalLandings;
    }

    public int getTotalLandingsDay() {
        return totalLandingsDay.get();
    }

    public void setTotalLandingsDay(int totalLandingsDay) {
        this.totalLandingsDay.set(totalLandingsDay);
    }

    public SimpleIntegerProperty totalLandingsDayProperty() {
        return totalLandingsDay;
    }

    public int getTotalLandingsNight() {
        return totalLandingsNight.get();
    }

    public void setTotalLandingsNight(int totalLandingsNight) {
        this.totalLandingsNight.set(totalLandingsNight);
    }

    public SimpleIntegerProperty totalLandingsNightProperty() {
        return totalLandingsNight;
    }

    public String getTotalIfrTime() {
        return totalIfrTime.get();
    }

    public void setTotalIfrTime(String totalIfrTime) {
        this.totalIfrTime.set(totalIfrTime);
    }

    public SimpleStringProperty totalIfrTimeProperty() {
        return totalIfrTime;
    }

    public String getTotalNightTime() {
        return totalNightTime.get();
    }

    public void setTotalNightTime(String totalNightTime) {
        this.totalNightTime.set(totalNightTime);
    }

    public SimpleStringProperty totalNightTimeProperty() {
        return totalNightTime;
    }

    public String getTotalTimeAsPic() {
        return totalTimeAsPic.get();
    }

    public void setTotalTimeAsPic(String totalTimeAsPic) {
        this.totalTimeAsPic.set(totalTimeAsPic);
    }

    public SimpleStringProperty totalTimeAsPicProperty() {
        return totalTimeAsPic;
    }

    public String getTotalTimeAsDual() {
        return totalTimeAsDual.get();
    }

    public void setTotalTimeAsDual(String totalTimeAsDual) {
        this.totalTimeAsDual.set(totalTimeAsDual);
    }

    public SimpleStringProperty totalTimeAsDualProperty() {
        return totalTimeAsDual;
    }

    public String getTotalTimeAsFI() {
        return totalTimeAsFI.get();
    }

    public void setTotalTimeAsFI(String totalTimeAsFI) {
        this.totalTimeAsFI.set(totalTimeAsFI);
    }

    public SimpleStringProperty totalTimeAsFIProperty() {
        return totalTimeAsFI;
    }

    public ArrayList<LogbookEntry> getFlightEntries() {
        return flightEntries;
    }

    public String getTotalTimeDay() {
        return totalTimeDay.get();
    }

    public void setTotalTimeDay(String totalTimeDay) {
        this.totalTimeDay.set(totalTimeDay);
    }

    public SimpleStringProperty totalTimeDayProperty() {
        return totalTimeDay;
    }

    public String getTotalTimeNight() {
        return totalTimeNight.get();
    }

    public void setTotalTimeNight(String totalTimeNight) {
        this.totalTimeNight.set(totalTimeNight);
    }

    public SimpleStringProperty totalTimeNightProperty() {
        return totalTimeNight;
    }

    public LocalDate getStartedOn() {
        return startedOn.get();
    }

    public void setStartedOn(LocalDate startedOn) {
        this.startedOn.set(startedOn);
    }

    public SimpleObjectProperty<LocalDate> startedOnProperty() {
        return startedOn;
    }

    public LocalDate getFinishedOn() {
        return finishedOn.get();
    }

    public void setFinishedOn(LocalDate finishedOn) {
        this.finishedOn.set(finishedOn);
    }

    public SimpleObjectProperty<LocalDate> finishedOnProperty() {
        return finishedOn;
    }

    public User getOwner() {
        return owner.get();
    }

    public void setOwner(User owner) {
        this.owner.set(owner);
    }

    public SimpleObjectProperty<User> ownerProperty() {
        return owner;
    }

    public Map<String, String> getEngineTypeTimes() {
        return engineTypeTimes;
    }

    public void setEngineTypeTimes(Map<String, String> engineTypeTimes) {
        this.engineTypeTimes.clear();
        this.engineTypeTimes.putAll(engineTypeTimes);
    }
}
