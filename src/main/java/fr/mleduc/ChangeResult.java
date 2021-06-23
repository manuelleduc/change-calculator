package fr.mleduc;

import java.util.List;
import java.util.Map;

public class ChangeResult
{
    private final Map<String, Integer> quantities;

    private final double change;

    public ChangeResult(double change, Map<String, Integer> quantities)
    {

        this.change = change;
        this.quantities = quantities;
    }

    public double getChange()
    {
        return change;
    }

    public Map<String, Integer> getQuantities()
    {
        return quantities;
    }
}
