package pl.adamzylinski.t3.ejb.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FaresHistory")
@NamedQueries({
        @NamedQuery(name = "FaresHistory.getAll", query = "from FaresHistory"),
        @NamedQuery(name = "FaresHistory.getForData", query = "from FaresHistory fh where fh.fareData = :faredata")
})
public class FaresHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @OneToOne
    @JoinColumn(name = "DriverId")
    private Driver driver;
    @Column(name = "FareData")
    private String fareData;
    @Column(name = "PersistedFare")
    private double persistedFare;
    @Column(name = "Mdate")
    private Timestamp modificationTimestamp;

    public FaresHistory() {
        // NO OP
    }

    public FaresHistory(Driver driver, String fareData, double persistedFare) {
        this.driver = driver;
        this.fareData = fareData;
        this.persistedFare = persistedFare;
    }

    // Self exlanatory getters&setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getFareData() {
        return fareData;
    }

    public void setFareData(String fareData) {
        this.fareData = fareData;
    }

    public double getPersistedFare() {
        return persistedFare;
    }

    public void setPersistedFare(double persistedFare) {
        this.persistedFare = persistedFare;
    }

    public Timestamp getModificationTimestamp() {
        return modificationTimestamp;
    }

    public void setModificationTimestamp(Timestamp modificationTimestamp) {
        this.modificationTimestamp = modificationTimestamp;
    }

}
