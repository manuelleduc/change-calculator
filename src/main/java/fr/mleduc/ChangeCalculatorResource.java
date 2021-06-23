package fr.mleduc;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/change")
@Produces(MediaType.APPLICATION_JSON)
public class ChangeCalculatorResource {

    @Inject
    ChangeService changeService;

    @GET
    public ChangeResult change() {
        return changeService.compute(3400, new Stock("manu", 925, 10), new Stock("flora", 750, 10));
    }
}
