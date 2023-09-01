package ru.otus.patterns.decorator.model;

public abstract class IngredientDecorator implements RestaurantMenuItem {
    private final RestaurantMenuItem restaurantMenuItem;
    private final String label;
    private final double price;

    public IngredientDecorator(RestaurantMenuItem restaurantMenuItem, String label, double price) {
        this.restaurantMenuItem = restaurantMenuItem;
        this.label = label;
        this.price = price;
    }

    public double getPrice() {
        return restaurantMenuItem.getPrice() + this.price;
    }

    public String getLabel() {
        return restaurantMenuItem.getLabel() + ", " + this.label;
    }
}
