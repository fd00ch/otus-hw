package otus.study.cashmachine.machine.service;

import otus.study.cashmachine.machine.data.CashMachine;

import java.math.BigDecimal;
import java.util.List;

public interface CashMachineService {
    List<Integer> getMoney(String cardNum, String pin, BigDecimal amount);

    BigDecimal putMoney(String cardNum, String pin, List<Integer> notes);

    BigDecimal checkBalance(String cardNum, String pin);

    boolean changePin(String cardNum, String oldPin, String newPin);
}
