package ru.otus.generics;

import ru.otus.generics.builder.BoxBuilder;
import ru.otus.generics.manager.BoxManager;
import ru.otus.generics.model.Apple;
import ru.otus.generics.model.Box;
import ru.otus.generics.model.Fruit;
import ru.otus.generics.model.Orange;

public class GenericsOtus {
    private static final float APPLE_WEIGHT_IN_GRAMS = 100.0f;
    private static final float ORANGE_WEIGHT_IN_GRAMS = 150.0f;

    public static void main(String[] args) {
        Box<Apple> appleBox1 = BoxBuilder.buildAppleBox(3, APPLE_WEIGHT_IN_GRAMS);
        System.out.println("Apple box1 weight: " + appleBox1.weight());

        Box<Apple> appleBox2 = BoxBuilder.buildAppleBox(5, APPLE_WEIGHT_IN_GRAMS);
        System.out.println("Apple box1 weight: " + appleBox2.weight());

        Box<Orange> orangeBox1 = BoxBuilder.buildOrangeBox(2, ORANGE_WEIGHT_IN_GRAMS);
        System.out.println("Orange box1 weight: " + orangeBox1.weight());


        System.out.println("Apple box1 weight is equal to Apple box2 weight: " + appleBox1.compare(appleBox2));
        System.out.println("Apple box1 weight is equal to Orange box1 weight: " + appleBox1.compare(orangeBox1));

        System.out.println("Apple box1 before movement: " + appleBox1);
        System.out.println("Apple box2 before movement: " + appleBox2);
        //BoxManager.moveContentToAnotherBox(appleBox1, orangeBox1); // compile error "нельзя яблоки высыпать в коробку с апельсинами"
        BoxManager.moveContentToAnotherBox(appleBox1, appleBox2); // ok
        System.out.println("Apple box1 after movement: " + appleBox1);
        System.out.println("Apple box2 after movement: " + appleBox2);

        //---------------------------------------------------------------

        Box<Fruit> appleOnlyFruitBox = BoxBuilder.buildAppleFruitBox(3, APPLE_WEIGHT_IN_GRAMS);
        Box<Fruit> orangeOnlyFruitBox = BoxBuilder.buildOrangeFruitBox(2, ORANGE_WEIGHT_IN_GRAMS);
        BoxManager.moveContentToAnotherBox(appleOnlyFruitBox, appleBox1); // ok: Box<Apple> --> Box<Fruit>
        BoxManager.moveContentToAnotherBox(orangeOnlyFruitBox, orangeBox1); // ok: Box<Orange> --> Box<Fruit>
        BoxManager.moveContentToAnotherBox(orangeOnlyFruitBox, appleOnlyFruitBox); // ok: Box<Fruit> --> Box<Fruit>
//        BoxManager.moveContentToAnotherBox(appleBox1, appleOnlyFruitBox); // compile error: Box<Fruit> --> Box<Apple>
//        BoxManager.moveContentToAnotherBox(orangeBox1, orangeOnlyFruitBox); // compile error: Box<Fruit> --> Box<Orange>
    }
}
