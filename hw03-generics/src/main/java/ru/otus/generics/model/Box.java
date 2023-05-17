package ru.otus.generics.model;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> implements Comparable<Box<? extends Fruit>> {
    private List<T> fruits = new ArrayList<>();

    public int weight() {
        return fruits.stream()
                .mapToInt(Fruit::getWeight)
                .sum();
    }

    public void add(T t) {
        fruits.add(t);
    }

    public void addAll(List<T> tList) {
        fruits.addAll(tList);
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
        return Integer.compare(weight(), o.weight());
    }

    @Override
    public String toString() {
        return "Box{" +
                "fruits count=" + fruits.size() +
                '}';
    }
}
