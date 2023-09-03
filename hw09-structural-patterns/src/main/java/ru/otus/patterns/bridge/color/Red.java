package ru.otus.patterns.bridge.color;

public class Red implements Color {
    @Override
    public void fill() {
        System.out.println("Fill with red color");
    }
}
