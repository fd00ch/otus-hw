package ru.otus.generics.model;

public abstract class Fruit {
    private int weight;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Fruit(int weight) {
        this.weight = weight;
    }
}
