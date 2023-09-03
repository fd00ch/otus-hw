package ru.otus.patterns.bridge;

import ru.otus.patterns.bridge.color.Blue;
import ru.otus.patterns.bridge.color.Green;
import ru.otus.patterns.bridge.color.Red;
import ru.otus.patterns.bridge.shape.Circle;
import ru.otus.patterns.bridge.shape.Shape;
import ru.otus.patterns.bridge.shape.Triangle;

public class BridgeMain {
    public static void main(String[] args) {
        Shape circle = new Circle(new Red());
        Shape triangle = new Triangle(new Blue());
        Shape rectangle = new Circle(new Green());

        circle.draw();
        triangle.draw();
        rectangle.draw();
    }

}
