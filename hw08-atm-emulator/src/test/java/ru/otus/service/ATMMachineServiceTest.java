package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.model.Currency;
import ru.otus.model.atm.ATMMachine;
import ru.otus.model.atm.Banknote;
import ru.otus.model.atm.BanknoteCell;
import ru.otus.service.impl.ATMMachineServiceImpl;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ATMMachineServiceTest {
    private static final Banknote banknoteRub100 = new Banknote(Currency.RUB, 100);
    private static final Banknote banknoteRub500 = new Banknote(Currency.RUB, 500);
    private static final Banknote banknoteRub1000 = new Banknote(Currency.RUB, 1000);
    private static final Banknote banknoteRub5000 = new Banknote(Currency.RUB, 5000);

    private static final Banknote banknoteUsd50 = new Banknote(Currency.USD, 50);
    private static final Banknote banknoteUsd100 = new Banknote(Currency.USD, 100);

    private static final Banknote banknoteEur50 = new Banknote(Currency.EUR, 50);
    private static final Banknote banknoteEur100 = new Banknote(Currency.EUR, 100);
    private static final Banknote banknoteEur200 = new Banknote(Currency.EUR, 200);
    private static final Banknote banknoteEur500 = new Banknote(Currency.EUR, 500);

    private ATMMachine atmMachine;
    private ATMMachineService atmMachineService;

    @BeforeEach
    void init() {
        BanknoteCell banknoteCellRub100 = new BanknoteCell(banknoteRub100, 1000);
        BanknoteCell banknoteCellRub500 = new BanknoteCell(banknoteRub500, 1000);
        BanknoteCell banknoteCellRub1000 = new BanknoteCell(banknoteRub1000, 1000);
        BanknoteCell banknoteCellRub5000 = new BanknoteCell(banknoteRub5000, 1000);

        BanknoteCell banknoteCellUsd50 = new BanknoteCell(banknoteUsd50, 1000);
        BanknoteCell banknoteCellUsd100 = new BanknoteCell(banknoteUsd100, 1000);

        BanknoteCell banknoteCellEur50 = new BanknoteCell(banknoteEur50, 1000);
        BanknoteCell banknoteCellEur100 = new BanknoteCell(banknoteEur100, 1000);
        BanknoteCell banknoteCellEur200 = new BanknoteCell(banknoteEur200, 1000);
        BanknoteCell banknoteCellEur500 = new BanknoteCell(banknoteEur500, 1000);

        var banknoteCells = Set.of(
                banknoteCellRub100, banknoteCellRub500, banknoteCellRub1000, banknoteCellRub5000, //RUB
                banknoteCellUsd50, banknoteCellUsd100, //USD
                banknoteCellEur50, banknoteCellEur100, banknoteCellEur200, banknoteCellEur500); //EUR

        atmMachine = new ATMMachine(banknoteCells);
        atmMachineService = new ATMMachineServiceImpl();
    }

    @Test
    void testDeposit6600Success() {
        int initialSum = atmMachineService.getRemainingMoney(atmMachine, Currency.RUB);
        Map<Banknote, Integer> banknotesForDeposit = Map.of(
                banknoteRub100, 1,
                banknoteRub500, 1,
                banknoteRub1000, 1,
                banknoteRub5000, 1);
        atmMachineService.deposit(atmMachine, banknotesForDeposit);
        assertEquals(initialSum + 5000 + 1000 + 500 + 100,
                atmMachineService.getRemainingMoney(atmMachine, Currency.RUB));
    }

    @Test
    void testWithdrawMoreThanHaveFail() {
        int minimumBanknoteDenomination = atmMachineService.getMinimumBanknoteDenomination(atmMachine, Currency.RUB);
        int illegalSumToCharge = atmMachineService.getRemainingMoney(atmMachine, Currency.RUB) + minimumBanknoteDenomination;
        Exception thrown = assertThrows(IllegalStateException.class, () ->
                atmMachineService.withdraw(atmMachine, Currency.RUB, illegalSumToCharge));
        assertEquals("Not enough money", thrown.getMessage());
    }

    @Test
    void testWithdrawInvalidAmountFail() {
        int minimumBanknoteDenomination = atmMachineService.getMinimumBanknoteDenomination(atmMachine, Currency.RUB);
        int invalidBanknoteDenomination = minimumBanknoteDenomination - 1;
        int illegalSumToWithdraw = atmMachineService.getRemainingMoney(atmMachine, Currency.RUB)
                - minimumBanknoteDenomination + invalidBanknoteDenomination;
        Exception thrown = assertThrows(IllegalStateException.class, () ->
                atmMachineService.withdraw(atmMachine, Currency.RUB, illegalSumToWithdraw));
        assertEquals("Can't withdraw the required sum", thrown.getMessage());
    }

    @Test
    void testWithdraw6600RubSuccess() {
        var banknoteRub100Count = 1;
        var banknoteRub500Count = 1;
        var banknoteRub1000Count = 1;
        var banknoteRub5000Count = 1;
        var amountToWithdraw = // 6600
                100 * banknoteRub100Count
                + 500 * banknoteRub500Count
                + 1000 * banknoteRub1000Count
                + 5000 * banknoteRub5000Count;
        var rubRemainingBanknotesBeforeWithdraw = atmMachineService.getRemainingBanknotes(atmMachine);
        var rubRemainingAmount = atmMachineService.getRemainingMoney(atmMachine, Currency.RUB);
        atmMachineService.withdraw(atmMachine, Currency.RUB, amountToWithdraw);
        var rubRemainingBanknotesAfterWithdraw = atmMachineService.getRemainingBanknotes(atmMachine);

        assertEquals(rubRemainingAmount - amountToWithdraw,
                atmMachineService.getRemainingMoney(atmMachine, Currency.RUB));
        assertEquals(rubRemainingBanknotesBeforeWithdraw.get(banknoteRub100) - banknoteRub100Count,
                rubRemainingBanknotesAfterWithdraw.get(banknoteRub100));
        assertEquals(rubRemainingBanknotesBeforeWithdraw.get(banknoteRub500) - banknoteRub500Count,
                rubRemainingBanknotesAfterWithdraw.get(banknoteRub500));
        assertEquals(rubRemainingBanknotesBeforeWithdraw.get(banknoteRub1000) - banknoteRub1000Count,
                rubRemainingBanknotesAfterWithdraw.get(banknoteRub1000));
        assertEquals(rubRemainingBanknotesBeforeWithdraw.get(banknoteRub5000) - banknoteRub5000Count,
                rubRemainingBanknotesAfterWithdraw.get(banknoteRub5000));
    }

    @Test
    void testWithdraw5100RubSuccess() {
        var banknoteRub100Count = 1;
        var banknoteRub500Count = 0;
        var banknoteRub1000Count = 0;
        var banknoteRub5000Count = 1;
        var amountToWithdraw = // 5100
                100 * banknoteRub100Count
                + 500 * banknoteRub500Count
                + 1000 * banknoteRub1000Count
                + 5000 * banknoteRub5000Count;
        var rubRemainingBanknotesBeforeWithdraw = atmMachineService.getRemainingBanknotes(atmMachine);
        var rubRemainingAmount = atmMachineService.getRemainingMoney(atmMachine, Currency.RUB);
        atmMachineService.withdraw(atmMachine, Currency.RUB, amountToWithdraw);
        var rubRemainingBanknotesAfterWithdraw = atmMachineService.getRemainingBanknotes(atmMachine);

        assertEquals(rubRemainingAmount - amountToWithdraw,
                atmMachineService.getRemainingMoney(atmMachine, Currency.RUB));
        assertEquals(rubRemainingBanknotesBeforeWithdraw.get(banknoteRub100) - banknoteRub100Count,
                rubRemainingBanknotesAfterWithdraw.get(banknoteRub100));
        assertEquals(rubRemainingBanknotesBeforeWithdraw.get(banknoteRub500) - banknoteRub500Count,
                rubRemainingBanknotesAfterWithdraw.get(banknoteRub500));
        assertEquals(rubRemainingBanknotesBeforeWithdraw.get(banknoteRub1000) - banknoteRub1000Count,
                rubRemainingBanknotesAfterWithdraw.get(banknoteRub1000));
        assertEquals(rubRemainingBanknotesBeforeWithdraw.get(banknoteRub5000) - banknoteRub5000Count,
                rubRemainingBanknotesAfterWithdraw.get(banknoteRub5000));
    }

    @Test
    void testGetRemainingMoneySuccess() {
        var rubRemainingAmount = atmMachineService.getRemainingMoney(atmMachine, Currency.RUB);
        var usdRemainingAmount = atmMachineService.getRemainingMoney(atmMachine, Currency.USD);
        var eurRemainingAmount = atmMachineService.getRemainingMoney(atmMachine, Currency.EUR);
        assertEquals(1000 * 100 + 1000 * 500 + 1000 * 1000 + 1000 * 5000, rubRemainingAmount);
        assertEquals(1000 * 50 + 1000 * 100, usdRemainingAmount);
        assertEquals(1000 * 50 + 1000 * 100 + 1000 * 200 + 1000 * 500, eurRemainingAmount);
    }
}