package ru.otus.patterns.adapter.adapters;

import ru.otus.patterns.adapter.model.MainPaymentSystem;
import ru.otus.patterns.adapter.model.PaypalPaymentSystem;

import java.math.BigDecimal;

public class PaypalPaymentAdapter implements MainPaymentSystem {
    private final PaypalPaymentSystem paypalPayment;

    public PaypalPaymentAdapter(PaypalPaymentSystem paypalPayment) {
        this.paypalPayment = paypalPayment;
    }

    @Override
    public void pay(BigDecimal amount) {
        paypalPayment.payByPaypal(amount);
    }
}
