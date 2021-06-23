package fr.mleduc;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.enterprise.context.ApplicationScoped;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

@ApplicationScoped
public class ChangeService
{
    public ChangeResult compute(int total, Stock stock0, Stock... stocks)
    {
        var model = new Model("the change problem");

        Map<Stock, IntVar> collection = new HashMap<>();
        var add = initVar(model, stock0);
        collection.put(stock0, add);
        for (Stock stock : stocks) {
            IntVar y = initVar(model, stock);
            collection.put(stock, y);
            add = add.add(y).intVar();
        }
        model.arithm(add.intVar(), "<=", total).post();
        var solver = model.getSolver();
        var change = Double.MAX_VALUE;
        Map<String, Integer> collect = new HashMap<>();
        while (solver.solve()) {
            Integer finalSum = collection.values().stream().map(IntVar::getValue).reduce(Integer::sum).orElse(0);

            var restTmp = (total - finalSum);
            if (restTmp < change) {
                change = restTmp;
                collect = collection.entrySet()
                    .stream()
                    .collect(
                        Collectors.toMap(it -> it.getKey().name, it -> it.getValue().getValue() / it.getKey().value));
            }
        }
        return new ChangeResult(change, collect);
    }

    private IntVar initVar(Model model, Stock stock)
    {
        return model.intVar(stock.name,
            IntStream.range(0, stock.quantity + 1).map(i -> i * stock.value).boxed().mapToInt(i -> i).toArray());
    }
}
