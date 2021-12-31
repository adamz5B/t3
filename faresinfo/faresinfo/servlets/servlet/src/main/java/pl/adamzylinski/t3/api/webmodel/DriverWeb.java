package pl.adamzylinski.t3.api.webmodel;

import pl.adamzylinski.t3.ejb.models.Driver;

/**
 * Outgoing model class for taxi drivers information. Fields are set on
 * creation, and only getters are available.
 */
public class DriverWeb implements FareInfoWebI {

    private static final String TRANSP_TYPE = "taxi";
    private String name;
    private String nameFurigana;
    private String surname;
    private String surnameFurigana;
    private String email;
    private String vehicleType;
    private double farePrice;

    public DriverWeb(Driver driver, double farePrice) {
        this.name = driver.getName();
        this.nameFurigana = driver.getNameFurigana();
        this.surname = driver.getSurname();
        this.surnameFurigana = driver.getSurnameFurigana();
        this.email = driver.getEmail();
        this.vehicleType = driver.getVehicleType();
        this.farePrice = farePrice;
    }

    @Override
    public String getTransportType() {
        return TRANSP_TYPE;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getNameFurigana() {
        return nameFurigana;
    }

    public String getSurnameFurigana() {
        return surnameFurigana;
    }

    public String getEmail() {
        return email;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    @Override
    public double getFarePrice() {
        return farePrice;
    }

}
