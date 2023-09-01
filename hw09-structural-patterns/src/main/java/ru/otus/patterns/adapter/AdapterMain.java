package ru.otus.patterns.adapter;

import ru.otus.patterns.adapter.adapters.CreditCardPaymentAdapter;
import ru.otus.patterns.adapter.adapters.PaypalPaymentAdapter;
import ru.otus.patterns.adapter.model.*;

import java.math.BigDecimal;

public class AdapterMain {
    public static void main(String[] args) {
        CreditCardPaymentSystem creditCardPaymentSystem = new CreditCardPaymentSystemImpl();
        MainPaymentSystem mainPaymentSystemUsingCreditCard = new CreditCardPaymentAdapter(creditCardPaymentSystem);
        mainPaymentSystemUsingCreditCard.pay(new BigDecimal("1500"));


        PaypalPaymentSystem paypalPaymentSystem = new PaypalPaymentSystemImpl();
        MainPaymentSystem mainPaymentSystemUsingPaypal = new PaypalPaymentAdapter(paypalPaymentSystem);
        mainPaymentSystemUsingPaypal.pay(new BigDecimal("2500"));
    }
}
