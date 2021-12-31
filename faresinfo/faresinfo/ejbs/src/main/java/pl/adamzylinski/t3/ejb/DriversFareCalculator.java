package pl.adamzylinski.t3.ejb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.adamzylinski.t3.ejb.models.Driver;
import pl.adamzylinski.t3.ejb.models.FareData;

/**
 * Classes for calculating Fare prices for given set of {@link Driver}s, by
 * method static getFareForDrivers.
 */
public class DriversFareCalculator {

    /**
     * Calculates fares for given list of drivers and fare data. If the driver's
     * base distance is grather than or equal to traveled distance base fare price
     * is used. Otherwise it:
     * <ol>
     * <li>Calculates distance not covered by base fare distance.</li>
     * <li>Calculates traveled units not covered by base fare.</li>
     * <li>Fare price = base fare price + not covered traveled units * fare's cost
     * per distance traveled.</li>
     * </ol>
     * 
     * @param driversList {@link List} containing {@link Drivers} for fare
     *                    calculation
     * @param fareData    {@link FareData} with fare information.
     * @return {@link Map} containing {@link Driver} as a key and {@link Double}
     *         fare price as a value.
     */
    public static Map<Driver, Double> getFareForDrivers(List<Driver> driversList, FareData fareData) {
        Map<Driver, Double> results = new HashMap<>();
        for (Driver driver : driversList) {
            if (fareData.getDistance() <= driver.getBaseFareDistance()) {
                results.put(driver, driver.getBaseFarePrice());
            } else {
                double offBaseDistance = fareData.getDistance() - driver.getBaseFareDistance();
                double offBaseUnits = offBaseDistance / fareData.getUnit();
                double fare = driver.getBaseFarePrice() + offBaseUnits * fareData.getCPD();
                results.put(driver, fare);
            }
        }
        return results;
    }

    private DriversFareCalculator() {
    }

}
