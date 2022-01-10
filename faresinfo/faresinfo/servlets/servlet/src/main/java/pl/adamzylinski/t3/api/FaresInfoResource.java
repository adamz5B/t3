package pl.adamzylinski.t3.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import pl.adamzylinski.t3.api.webmodel.DriverWeb;
import pl.adamzylinski.t3.api.webmodel.FareInfoWebI;
import pl.adamzylinski.t3.common.FareCalcBeanI;
import pl.adamzylinski.t3.common.MeanOfTransportI;
import pl.adamzylinski.t3.ejb.models.Driver;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;

@Path("/fares")
public class FaresInfoResource {

    @Inject
    @Any
    Instance<FareCalcBeanI<? extends MeanOfTransportI>> beansInstances;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * Method for returning collection of fares from various sources. Those needs to
     * implement {@link FareInfoWebI}.
     * 
     * @return {@link List} of {@link FareInfoWebI}
     */
    public List<FareInfoWebI> getAll() {
        List<FareInfoWebI> results = new ArrayList<>();
        for (final FareCalcBeanI<? extends MeanOfTransportI> beanInstance : beansInstances) {
            results.addAll(beanInstance.getTransportFares().entrySet().stream()
                    .map(this::getPresentation)
                    .collect(Collectors.toList()));
        }

        return results;
    }

    /**
     * Finds implementation of web model for given data model
     */
    private FareInfoWebI getPresentation(Entry<? extends MeanOfTransportI, Double> entry) {
        if (entry.getKey() instanceof Driver) {
            return new DriverWeb((Driver) entry.getKey(), entry.getValue());
        }
        throw new IllegalStateException("Incopatibile implementation used.");
    }
}
