package ru.otus.patterns.decorator.model.ingredients;

import ru.otus.patterns.decorator.model.IngredientDecorator;
import ru.otus.patterns.decorator.model.RestaurantMenuItem;

public class Cheese extends IngredientDecorator {
    public Cheese(RestaurantMenuItem restaurantMenuItem) {
        super(restaurantMenuItem, "Сыр", 50);
    }

}
