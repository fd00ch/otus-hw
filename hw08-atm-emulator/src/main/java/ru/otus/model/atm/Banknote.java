package ru.otus.model.atm;

import ru.otus.model.Currency;

public record Banknote (Currency currency, int denomination) {
    public Banknote {
        if (currency == null) {
            throw new IllegalArgumentException("Currency must not be null");
        }
        if (denomination <= 0) {
            throw new IllegalArgumentException("Denomination must be positive");
        }
    }

}
