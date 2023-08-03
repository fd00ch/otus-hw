package ru.otus.patterns.decorator.model.ingredients;

import ru.otus.patterns.decorator.model.IngredientDecorator;
import ru.otus.patterns.decorator.model.RestaurantMenuItem;

public class Tomato extends IngredientDecorator {
    public Tomato(RestaurantMenuItem restaurantMenuItem) {
        super(restaurantMenuItem, "Помидоры", 25);
    }

}
