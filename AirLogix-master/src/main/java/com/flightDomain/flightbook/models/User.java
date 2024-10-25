package com.flightDomain.flightbook.models;


import java.time.LocalDate;
import javafx.beans.property.*;

public class User {
    private IntegerProperty id;
    private StringProperty name;
    private StringProperty address;
    private ObjectProperty<LocalDate> birthdate;
    private StringProperty birthplace;
    private StringProperty nationality;

    public User(int id, String name, String address, LocalDate birthdate, String birthplace, String nationality) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.birthdate = new SimpleObjectProperty<>(birthdate);
        this.birthplace = new SimpleStringProperty(birthplace);
        this.nationality = new SimpleStringProperty(nationality);
    }

    public User(String name, String address, LocalDate birthdate, String birthplace, String nationality) {
        this(0, name, address, birthdate, birthplace, nationality);
    }
    public User(String name){
        this.name = new SimpleStringProperty(name);

    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id.get() +
                ", name=" + name.get() +
                ", address=" + address.get() +
                ", birthdate=" + birthdate.get() +
                ", birthplace=" + birthplace.get() +
                ", nationality=" + nationality.get() +
                '}';
    }

    // Getter und Setter für alle Eigenschaften
    public int getId() { return id.get(); }
    public void setId(int id) { this.id.set(id); }
    public IntegerProperty idProperty() { return id; }

    public String getName() { return name.get(); }
    public void setName(String name) { this.name.set(name); }
    public StringProperty nameProperty() { return name; }

    public String getAddress() { return address.get(); }
    public void setAddress(String address) { this.address.set(address); }
    public StringProperty addressProperty() { return address; }

    public LocalDate getBirthdate() { return birthdate.get(); }
    public void setBirthdate(LocalDate birthdate) { this.birthdate.set(birthdate); }
    public ObjectProperty<LocalDate> birthdateProperty() { return birthdate; }

    public String getBirthplace() { return birthplace.get(); }
    public void setBirthplace(String birthplace) { this.birthplace.set(birthplace); }
    public StringProperty birthplaceProperty() { return birthplace; }

    public String getNationality() { return nationality.get(); }
    public void setNationality(String nationality) { this.nationality.set(nationality); }
    public StringProperty nationalityProperty() { return nationality; }
}