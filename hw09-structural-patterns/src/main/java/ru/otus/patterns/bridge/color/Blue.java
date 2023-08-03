package ru.otus.patterns.bridge.color;

public class Blue implements Color {
    @Override
    public void fill() {
        System.out.println("Fill with blue color");
    }
}
