package fr.mleduc;

public class ChangeResult {

    private double change;
    private int quantityA;
    private int quantityB;

    public ChangeResult() {
    }

    public ChangeResult(double change, int quantityA, int quantityB) {

        this.change = change;
        this.quantityA = quantityA;
        this.quantityB = quantityB;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public int getQuantityA() {
        return quantityA;
    }

    public void setQuantityA(int quantityA) {
        this.quantityA = quantityA;
    }

    public int getQuantityB() {
        return quantityB;
    }

    public void setQuantityB(int quantityB) {
        this.quantityB = quantityB;
    }
}
