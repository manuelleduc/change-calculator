package fr.mleduc;

import java.util.Objects;

public class Stock {
    String name;
    int value;
    int quantity;

    public Stock(String name, int value, int quantity) {
        this.name = name;
        this.value = value;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return value == stock.value && quantity == stock.quantity && name.equals(stock.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value, quantity);
    }
}
