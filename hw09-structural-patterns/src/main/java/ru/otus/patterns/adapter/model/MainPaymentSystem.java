package ru.otus.patterns.adapter.model;

import java.math.BigDecimal;

public interface MainPaymentSystem {
    void pay(BigDecimal amount);
}
