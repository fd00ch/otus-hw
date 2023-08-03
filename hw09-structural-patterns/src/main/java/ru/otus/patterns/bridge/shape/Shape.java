package ru.otus.patterns.bridge.shape;

import ru.otus.patterns.bridge.color.Color;

public abstract class Shape {
    protected Color color;

    public Shape(Color color) {
        this.color = color;
    }

    public abstract void draw();
}
