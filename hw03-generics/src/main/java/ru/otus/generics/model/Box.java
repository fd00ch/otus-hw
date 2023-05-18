package ru.otus.generics.model;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> implements Comparable<Box<? extends Fruit>> {
    private List<T> fruits = new ArrayList<>();

    public float weight() {
        return fruits.stream()
                .map(Fruit::getWeight)
                .reduce(0f, Float::sum);
    }

    public void add(T fruit) {
        fruits.add(fruit);
    }

    public void addAll(List<? extends T> fruits) {
        this.fruits.addAll(fruits);
    }

    public List<T> getFruits() {
        return fruits;
    }

    public void setFruits(List<T> fruits) {
        this.fruits = fruits;
    }

    public boolean compare(Box<? extends Fruit> anotherBox) {
        return compareTo(anotherBox) == 0;
    }

    @Override
    public int compareTo(Box<? extends Fruit> o) {
        final float epsilon = 0.001f;
        if(Math.abs(weight() - o.weight()) < epsilon) {
            return 0;
        }
        return Float.compare(weight(), o.weight());
    }

    @Override
    public String toString() {
        return "Box{" +
                "fruits count=" + fruits.size() +
                '}';
    }
}
