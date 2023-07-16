package ru.otus.patterns.decorator.model;

public class Shaurma implements RestaurantMenuItem {
    private final String label;
    private final double price;

    public Shaurma(String label, double price) {
        this.label = label;
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }

    public String getLabel() {
        return this.label;
    }
}
