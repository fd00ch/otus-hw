package ru.otus.service.impl;

import ru.otus.model.Currency;
import ru.otus.model.atm.ATMMachine;
import ru.otus.model.atm.Banknote;
import ru.otus.service.ATMMachineService;

import java.util.HashMap;
import java.util.Map;

public class ATMMachineServiceImpl implements ATMMachineService {

    @Override
    public void deposit(ATMMachine atmMachine, Map<Banknote, Integer> banknotes) {
        banknotes.forEach((banknote, count) -> atmMachine.getBanknoteCells().stream()
                .filter(banknoteCell -> banknoteCell.getBanknote().equals(banknote))
                .forEach(banknoteCell -> banknoteCell.setCount(banknoteCell.getCount() + count)));
    }

    @Override
    public void withdraw(ATMMachine atmMachine, Currency currency, int amount) {
        if (amount > getRemainingMoney(atmMachine, currency)) {
            throw new IllegalStateException("Not enough money");
        }

        var minimumBanknoteDenomination = getMinimumBanknoteDenomination(atmMachine, currency);
        if (amount % minimumBanknoteDenomination != 0) {
            throw new IllegalStateException("Can't withdraw the required sum");
        }

        Map<Banknote, Integer> banknotesToWithdraw = new HashMap<>();
        final int[] amount2 = {amount};
        atmMachine.getBanknoteCells().stream()
                .filter(banknoteCell -> banknoteCell.getBanknote().getCurrency().equals(currency))
                .sorted((banknoteCell1, banknoteCell2) ->
                        banknoteCell2.getBanknote().getDenomination() - banknoteCell1.getBanknote().getDenomination())
                .forEach(banknoteCell -> {
                    int banknoteDenomination = banknoteCell.getBanknote().getDenomination();
                    int banknoteCount = banknoteCell.getCount();
                    int banknoteCountToWithdraw = amount2[0] / banknoteDenomination;
                    if (banknoteCountToWithdraw > 0) {
                        if (banknoteCountToWithdraw <= banknoteCount) {
                            banknotesToWithdraw.put(banknoteCell.getBanknote(), banknoteCountToWithdraw);
                            amount2[0] -= banknoteCountToWithdraw * banknoteDenomination;
                        } else {
                            banknotesToWithdraw.put(banknoteCell.getBanknote(), banknoteCount);
                            amount2[0] -= banknoteCount * banknoteDenomination;
                        }
                    }
                });
        if (amount2[0] > 0) {
            throw new IllegalArgumentException("Not enough banknotes");
        }

        banknotesToWithdraw.forEach((banknote, count) -> atmMachine.getBanknoteCells().stream()
                .filter(banknoteCell -> banknoteCell.getBanknote().equals(banknote))
                .forEach(banknoteCell -> banknoteCell.setCount(banknoteCell.getCount() - count)));
    }

    @Override
    public int getRemainingMoney(ATMMachine atmMachine, Currency currency) {
        return atmMachine.getBanknoteCells().stream()
                .filter(banknoteCell -> banknoteCell.getBanknote().getCurrency().equals(currency))
                .mapToInt(banknoteCell -> banknoteCell.getBanknote().getDenomination() * banknoteCell.getCount())
                .sum();
    }

    @Override
    public Map<Banknote, Integer> getRemainingBanknotes(ATMMachine atmMachine) {
        Map<Banknote, Integer> banknotes = new HashMap<>();
        atmMachine.getBanknoteCells().forEach(banknoteCell -> banknotes.put(banknoteCell.getBanknote(), banknoteCell.getCount()));
        return banknotes;
    }

    @Override
    public int getMinimumBanknoteDenomination(ATMMachine atmMachine, Currency currency) {
        return atmMachine.getBanknoteCells().stream()
                .filter(banknoteCell -> banknoteCell.getBanknote().getCurrency().equals(currency))
                .mapToInt(banknoteCell -> banknoteCell.getBanknote().getDenomination())
                .min()
                .orElseThrow(() -> new IllegalArgumentException("Minimum banknote denomination not found"));
    }
}
