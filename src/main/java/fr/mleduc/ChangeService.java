package fr.mleduc;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

import javax.enterprise.context.ApplicationScoped;
import java.util.stream.IntStream;

@ApplicationScoped
public class ChangeService {
    public ChangeResult compute(int total, Stock stockA, Stock stockB) {
        var model = new Model("my first problem");

        var x = initVar(model, stockA);
        var y = initVar(model, stockB);
        model.arithm(x, "+", y, "<=", total).post();
        var solver = model.getSolver();
        var change = Double.MAX_VALUE;
        var quantityA = 0;
        var quantityB = 0;
        while (solver.solve()) {
            var tmpA = x.getValue();
            var tmpB = y.getValue();
            var restTmp = (total - tmpA - tmpB) / 100.0;
            if (restTmp < change) {
                change = restTmp;
                quantityA = tmpA / stockA.value;
                quantityB = tmpB / stockB.value;
            }
        }
        // System.out.println("rest = " + change + " euros + " + quantityA + " tickets Manu + " + quantityB + " tickets Flora");

        return new ChangeResult(change, quantityA, quantityB);
    }

    private IntVar initVar(Model model, Stock stock) {
        return model.intVar(stock.name, IntStream.range(0, stock.quantity).map(i -> i * stock.value).boxed().mapToInt(i -> i).toArray());
    }
}
