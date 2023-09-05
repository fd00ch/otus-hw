package ru.otus.model;

import lombok.Getter;

@Getter
public enum Currency {
    USD (0, "USD"),
    EUR (1, "EUR"),
    RUB (2, "RUB");

    private final int id;
    private final String viewName;

    Currency(int id, String viewName) {
        this.id = id;
        this.viewName = viewName;
    }

    public static Currency getById(int id) {
        if (id == USD.id) {
            return Currency.USD;
        } else if (id == EUR.id) {
            return Currency.EUR;
        } else if (id == RUB.id) {
            return Currency.RUB;
        } else return null;
    }
}
