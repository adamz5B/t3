package pl.adamzylinski.t3.ejb;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import pl.adamzylinski.t3.common.FareCalcBeanI;
import pl.adamzylinski.t3.ejb.models.Driver;
import pl.adamzylinski.t3.ejb.models.FareData;
import pl.adamzylinski.t3.ejb.models.FaresHistory;

/**
 * The taxi drivers bean
 */
@Stateless
@LocalBean
public class DriversBean implements FareCalcBeanI<Driver> {
    private static final Logger LOGGER = Logger.getLogger(DriversBean.class.getName());

    @PersistenceContext(unitName = "appDS_PU")
    EntityManager entityManager;

    public List<Driver> getAllDrivers() {
        return entityManager.createNamedQuery("Drivers.getAll", Driver.class).getResultList();
    }

    @Override
    public Map<Driver, Double> getTransportFares(/* double someNotExistingDistanceParameter */) {
        FareData fareData;
        try {
            FareDataProvider fdp = new FareDataProvider();
            // ONE IMPORTANT NOTICE - normally getting here might be done by finding e.g.
            // distance in given collection. However in current cirrcumstances this approach
            // will suffice
            fareData = fdp.getFareDataFromFile().stream().findFirst().get();// NOSONAR
        } catch (IOException | NoSuchElementException e) {
            LOGGER.severe("Could not load CSV file content");
            return Collections.emptyMap();
        }
        List<Driver> drivers = getAllDrivers();
        List<FaresHistory> faresHistory = entityManager.createNamedQuery("FaresHistory.getForData", FaresHistory.class)
                .setParameter("faredata", fareData.getCSV()).getResultList();
        List<Driver> notCalced = drivers.stream()
                .filter(driver -> faresHistory.stream().noneMatch(fr -> fr.getDriver().getId() == driver.getId()))
                .collect(Collectors.toList());
        List<Driver> reCalc = faresHistory.stream()
                .filter(fr -> fr.getModificationTimestamp().before(fr.getDriver().getModifiedTimestamp()))
                .map(FaresHistory::getDriver).collect(Collectors.toList());
        notCalced.addAll(reCalc);
        Map<Driver, Double> result = DriversFareCalculator.getFareForDrivers(notCalced, fareData);
        if (!notCalced.isEmpty()) {
            storeFares(result, fareData);
        }
        result.putAll(faresHistory.stream().filter(fr -> !reCalc.contains(fr.getDriver()))
                .collect(Collectors.toMap(FaresHistory::getDriver, FaresHistory::getPersistedFare)));
        return result;
    }

    @Transactional
    private void storeFares(Map<Driver, Double> results, FareData fareData) {
        for (var set : results.entrySet()) {
            entityManager.merge(
                    new FaresHistory(set.getKey(), fareData.getCSV(), set.getValue()));
        }
    }

    @Produces
    public DriversBean getDriversBean() {
        return this;
    }
}
