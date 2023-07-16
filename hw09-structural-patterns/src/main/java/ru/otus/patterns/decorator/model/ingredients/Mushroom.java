package ru.otus.patterns.decorator.model.ingredients;

import ru.otus.patterns.decorator.model.IngredientDecorator;
import ru.otus.patterns.decorator.model.RestaurantMenuItem;

public class Mushroom extends IngredientDecorator {
    public Mushroom(RestaurantMenuItem restaurantMenuItem) {
        super(restaurantMenuItem, "Грибы", 45);
    }

}
