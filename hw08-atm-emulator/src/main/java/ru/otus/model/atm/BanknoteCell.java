package ru.otus.model.atm;

import lombok.Data;

@Data
public class BanknoteCell {
    private final Banknote banknote;
    private int count;

    public BanknoteCell(Banknote banknote, int count) {
        if (banknote == null) {
            throw new IllegalArgumentException("Banknote must not be null");
        }
        if (count < 0) {
            throw new IllegalArgumentException("Count must be positive");
        }

        this.banknote = banknote;
        this.count = count;
    }
}
