package ru.otus.patterns.decorator.model.ingredients;

import ru.otus.patterns.decorator.model.IngredientDecorator;
import ru.otus.patterns.decorator.model.RestaurantMenuItem;

public class Cucumber extends IngredientDecorator {
    public Cucumber(RestaurantMenuItem restaurantMenuItem) {
        super(restaurantMenuItem, "Огурец", 20);
    }

}
