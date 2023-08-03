package ru.otus.patterns.bridge.shape;

import ru.otus.patterns.bridge.color.Color;

public class Triangle extends Shape {
    public Triangle(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        System.out.println("Draw triangle");
        color.fill();
    }
}
