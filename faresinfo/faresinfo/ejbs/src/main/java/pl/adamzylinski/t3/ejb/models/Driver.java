package pl.adamzylinski.t3.ejb.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "Driver")
@NamedQueries({
        @NamedQuery(name = "Drivers.getAll", query = "from Driver")
})
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private int id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Surname")
    private String surname;
    @Column(name = "NameFurigana")
    private String nameFurigana;
    @Column(name = "SurnameFurigana")
    private String surnameFurigana;
    @Column(name = "Email")
    private String email;
    @Column(name = "VehicleType")
    private String vehicleType;
    @Column(name = "BaseFarePrice")
    private double baseFarePrice;
    @Column(name = "BaseFareDistance")
    private double baseFareDistance;
    @Column(name = "Cdate")
    private Timestamp createdTimestamp;
    @Column(name = "Mdate")
    private Timestamp modifiedTimestamp;

    // Self exlanatory getters&setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNameFurigana() {
        return nameFurigana;
    }

    public void setNameFurigana(String nameFurigana) {
        this.nameFurigana = nameFurigana;
    }

    public String getSurnameFurigana() {
        return surnameFurigana;
    }

    public void setSurnameFurigana(String surnameFurigana) {
        this.surnameFurigana = surnameFurigana;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public double getBaseFarePrice() {
        return baseFarePrice;
    }

    public void setBaseFarePrice(double baseFarePrice) {
        this.baseFarePrice = baseFarePrice;
    }

    public double getBaseFareDistance() {
        return baseFareDistance;
    }

    public void setBaseFareDistance(double baseFareDistance) {
        this.baseFareDistance = baseFareDistance;
    }

    public Timestamp getCreateTimestamp() {
        return createdTimestamp;
    }

    public void setCreateTimestamp(Timestamp createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public Timestamp getModifiedTimestamp() {
        return modifiedTimestamp;
    }

    public void setModifiedTimestamp(Timestamp modifiedTimestamp) {
        this.modifiedTimestamp = modifiedTimestamp;
    }

}
