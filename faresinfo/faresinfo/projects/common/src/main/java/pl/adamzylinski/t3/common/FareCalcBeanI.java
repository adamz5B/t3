package pl.adamzylinski.t3.common;

import java.util.Map;

/**
 * Interface for Beans to provide method for fare calculation.  
 * @param <T> Generic for any possible implementation of fares calculation
 */
public interface FareCalcBeanI<T extends MeanOfTransportI > {
    /**
     * Calculates transportation fares for given list input(e.g. fares for taxi drivers).
     * @return {@link Map} containing object as a key an calculated fare as a value.
     */
    public Map<T,Double> getTransportFares();
}
