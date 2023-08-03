package ru.otus.patterns.decorator.model.ingredients;

import ru.otus.patterns.decorator.model.IngredientDecorator;
import ru.otus.patterns.decorator.model.RestaurantMenuItem;

public class BulgarianPepper extends IngredientDecorator {
    public BulgarianPepper(RestaurantMenuItem restaurantMenuItem) {
        super(restaurantMenuItem, "Перец болгарский", 40);
    }

}
