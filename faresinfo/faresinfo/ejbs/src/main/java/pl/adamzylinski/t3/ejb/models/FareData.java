package pl.adamzylinski.t3.ejb.models;

public class FareData {
    public static final String COMMA = ",";
    private double distance;
    private double unit;
    private double costPerDistance;

    public FareData(double distance, double unit, double costPerDistance) {
        this.distance = distance;
        this.unit = unit;
        this.costPerDistance = costPerDistance;
    }

    public double getDistance() {
        return distance;
    }

    public double getUnit() {
        return unit;
    }

    public double getCPD() {
        return costPerDistance;
    }

    public String getCSV() {
        return "" + distance + COMMA + unit + COMMA + costPerDistance;
    }
}
