package ru.otus.patterns.bridge.shape;

import ru.otus.patterns.bridge.color.Color;

public class Rectangle extends Shape {
    public Rectangle(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        System.out.println("Draw rectangle");
        color.fill();
    }
}
