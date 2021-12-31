package pl.adamzylinski.t3.api.webmodel;

/**
 * Outgoing model interface for Fare Price information. In the most basic way it
 * provides
 * transport type (e.g. taxi) and its calculated fare price.
 */
public interface FareInfoWebI {
    public String getTransportType();

    public double getFarePrice();
}
