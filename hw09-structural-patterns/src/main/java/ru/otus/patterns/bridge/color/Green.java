package ru.otus.patterns.bridge.color;

public class Green implements Color {
    @Override
    public void fill() {
        System.out.println("Fill with green color");
    }
}
