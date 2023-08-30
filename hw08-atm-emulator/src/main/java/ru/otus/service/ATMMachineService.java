package ru.otus.service;

import ru.otus.model.Currency;
import ru.otus.model.atm.ATMMachine;
import ru.otus.model.atm.Banknote;

import java.util.Map;

public interface ATMMachineService {
    void deposit(ATMMachine atmMachine, Map<Banknote, Integer> banknotes);
    void withdraw(ATMMachine atmMachine, Currency currency, int amount);
    int getRemainingMoney(ATMMachine atmMachine, Currency currency);
    Map<Banknote, Integer> getRemainingBanknotes(ATMMachine atmMachine);
    int getMinimumBanknoteDenomination(ATMMachine atmMachine, Currency currency);
}
