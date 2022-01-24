package fr.mleduc;

import org.eclipse.microprofile.faulttolerance.Timeout;

import javax.inject.Inject;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/change")
@Produces(MediaType.APPLICATION_JSON)
public class ChangeCalculatorResource {
    @Inject
    ChangeService changeService;

    @PUT
    @Timeout(2000)
    public Optional<ChangeResult> change(ChangeRequest changeRequest) {
        List<Stock> stocks = changeRequest.stocks;
        if (stocks == null || stocks.isEmpty()) {
            return Optional.empty();
        }

        Stock stock0 = stocks.remove(0);
        Stock[] stocks1 = stocks.toArray(new Stock[0]);
        return Optional.of(changeService.compute(changeRequest.total, stock0, stocks1));
    }
}
