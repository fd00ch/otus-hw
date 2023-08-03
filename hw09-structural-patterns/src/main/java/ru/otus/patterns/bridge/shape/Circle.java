package ru.otus.patterns.bridge.shape;

import ru.otus.patterns.bridge.color.Color;

public class Circle extends Shape {
    public Circle(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        System.out.println("Draw circle");
        color.fill();
    }
}
