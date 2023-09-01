package ru.otus.patterns.adapter.model;

import java.math.BigDecimal;

public class CreditCardPaymentSystemImpl implements CreditCardPaymentSystem {
    @Override
    public void payByCreditCard(BigDecimal amount) {
        System.out.println("Pay by credit card: " + amount);
    }
}
