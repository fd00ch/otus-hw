package ru.otus.patterns.decorator;

import ru.otus.patterns.decorator.model.ingredients.Cheese;
import ru.otus.patterns.decorator.model.ingredients.Cucumber;
import ru.otus.patterns.decorator.model.Shaurma;
import ru.otus.patterns.decorator.model.RestaurantMenuItem;
import ru.otus.patterns.decorator.model.ingredients.Tomato;

public class DecoratorMain {
    public static void main(String[] args) {
        RestaurantMenuItem shaurma = new Shaurma("Шаурма с курицей", 300);
        shaurma = new Cheese(shaurma);
        shaurma = new Cucumber(shaurma);
        shaurma = new Tomato(shaurma);

        // Total price:
        System.out.printf("%s: %s", shaurma.getLabel(), shaurma.getPrice());
    }
}
