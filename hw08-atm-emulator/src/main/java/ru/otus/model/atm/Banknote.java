package ru.otus.model.atm;

import lombok.Data;
import ru.otus.model.Currency;

@Data
public class Banknote {
    private final Currency currency;
    private final int denomination;

    public Banknote(Currency currency, int denomination) {
        if (currency == null) {
            throw new IllegalArgumentException("Currency must not be null");
        }
        if (denomination <= 0) {
            throw new IllegalArgumentException("Denomination must be positive");
        }
        this.currency = currency;
        this.denomination = denomination;
    }


}
