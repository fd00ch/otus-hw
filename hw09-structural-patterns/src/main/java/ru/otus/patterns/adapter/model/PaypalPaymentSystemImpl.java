package ru.otus.patterns.adapter.model;

import java.math.BigDecimal;

public class PaypalPaymentSystemImpl implements PaypalPaymentSystem {
    @Override
    public void payByPaypal(BigDecimal amount) {
        System.out.println("Pay by paypal: " + amount);
    }
}
