package pl.adamzylinski.t3.ejb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import pl.adamzylinski.t3.ejb.models.FareData;

/**
 * Provides {@link FareData} from fare_data.csv file, which must be placed in
 * resource folder of EJB.
 */
public class FareDataProvider {
    public static final String FILE_NAME = "fare_data.csv";
    public static final String COMMA = ",";
    public static final int DIST_INDEX = 0;
    public static final int UNIT_INDEX = 1;
    public static final int CPD_INDEX = 2;

    /**
     * Reads {@link FareData} from resource file.
     * 
     * @return {@link List} of {@link FareData}
     * @throws IOException if an error occures while reading from file.
     */
    public List<FareData> getFareDataFromFile() throws IOException {
        List<FareData> data = new ArrayList<>();
        InputStreamReader inputStreamReader = getFileReader();
        try (BufferedReader br = new BufferedReader(inputStreamReader)) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA);

                data.add(new FareData(
                        Double.parseDouble(values[DIST_INDEX]),
                        Double.parseDouble(values[UNIT_INDEX]),
                        Double.parseDouble(values[CPD_INDEX])));
            }
        } catch (NumberFormatException e) {
            // Since log4j had its issues nowadays...
            System.err.println("Value cannot be parsed: " + e.getLocalizedMessage());// NOSONAR
            e.printStackTrace();
        }
        return data;
    }

    private InputStreamReader getFileReader() {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(FILE_NAME);
        if (is == null) {
            throw new IllegalArgumentException("file not found!");
        } else {
            return new InputStreamReader(is);
        }
    }

}
