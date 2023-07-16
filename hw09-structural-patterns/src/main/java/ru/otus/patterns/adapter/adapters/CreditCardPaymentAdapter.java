package ru.otus.patterns.adapter.adapters;

import ru.otus.patterns.adapter.model.CreditCardPaymentSystem;
import ru.otus.patterns.adapter.model.MainPaymentSystem;

import java.math.BigDecimal;

public class CreditCardPaymentAdapter implements MainPaymentSystem {
    private final CreditCardPaymentSystem creditCardPayment;

    public CreditCardPaymentAdapter(CreditCardPaymentSystem creditCardPayment) {
        this.creditCardPayment = creditCardPayment;
    }

    @Override
    public void pay(BigDecimal amount) {
        creditCardPayment.payByCreditCard(amount);
    }
}
