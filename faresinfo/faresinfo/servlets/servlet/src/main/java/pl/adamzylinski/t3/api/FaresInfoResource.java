package pl.adamzylinski.t3.api;

import java.util.List;
import java.util.stream.Collectors;

import pl.adamzylinski.t3.api.webmodel.DriverWeb;
import pl.adamzylinski.t3.api.webmodel.FareInfoWebI;
import pl.adamzylinski.t3.ejb.DriversBean;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/fares")
public class FaresInfoResource {
    @Inject
    DriversBean driversBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * Method for returning collection of fares from various sources. Those needs to
     * implement {@link FareInfoWebI}.
     * 
     * @return {@link List} of {@link FareInfoWebI}
     */
    public List<FareInfoWebI> getAll() {
        return driversBean.getTransportFares().entrySet().stream()
                .map(entry -> new DriverWeb(entry.getKey(), entry.getValue())).collect(Collectors.toList());
    }
}
