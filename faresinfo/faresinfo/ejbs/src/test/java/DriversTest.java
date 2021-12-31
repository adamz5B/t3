import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import junit.framework.TestCase;
import pl.adamzylinski.t3.ejb.DriversFareCalculator;
import pl.adamzylinski.t3.ejb.FareDataProvider;
import pl.adamzylinski.t3.ejb.models.Driver;
import pl.adamzylinski.t3.ejb.models.FareData;

public class DriversTest extends TestCase {

    @BeforeClass
    public void setUp() {

    }

    @AfterClass
    public void tearDown() {
    }

    @Test
    /**
     * Test for accessing resource CSV file.
     */
    public void testFileRead() {
        FareDataProvider fdp = new FareDataProvider();
        try {
            List<FareData> list = fdp.getFareDataFromFile();
            assertTrue(list.size() > 0);
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
            assertFalse(true);
        }
    }

    @Test
    /**
     * Test for fare price calculation.
     */
    public void basicFareCalcTest() {
        List<Driver> testDrvrs = Arrays.asList(new Driver(), new Driver());
        testDrvrs.get(0).setBaseFarePrice(200);
        testDrvrs.get(0).setBaseFareDistance(150);
        testDrvrs.get(1).setBaseFarePrice(300);
        testDrvrs.get(1).setBaseFareDistance(250);
        FareData fd = new FareData(200, 50, 100);
        Map<Driver, Double> result = DriversFareCalculator.getFareForDrivers(testDrvrs, fd);
        assertTrue(result.get(testDrvrs.get(0)) == 300.0 && result.get(testDrvrs.get(1)) == 300.0);
    }

}
