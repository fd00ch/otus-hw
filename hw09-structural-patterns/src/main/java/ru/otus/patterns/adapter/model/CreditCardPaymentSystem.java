package ru.otus.patterns.adapter.model;

import java.math.BigDecimal;

public interface CreditCardPaymentSystem {
    void payByCreditCard(BigDecimal amount);

}
