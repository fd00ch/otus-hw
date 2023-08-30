package ru.otus.model;

import lombok.Getter;

@Getter
public enum Currency {
    USD (0, "USD", '$'),
    EUR (1, "EUR", '€'),
    RUB (2, "RUB", '₽');

    private final int id;

    private final String viewName;
    private final char symbol;
    Currency(int id, String viewName, char symbol) {
        this.id = id;
        this.viewName = viewName;
        this.symbol = symbol;
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
