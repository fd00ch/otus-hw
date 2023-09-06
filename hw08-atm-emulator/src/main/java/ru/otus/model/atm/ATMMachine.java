package ru.otus.model.atm;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class ATMMachine {
    private Set<BanknoteCell> banknoteCells;
}
