package ru.otus.patterns.adapter.model;

import java.math.BigDecimal;

public interface PaypalPaymentSystem {
    void payByPaypal(BigDecimal amount);
}
