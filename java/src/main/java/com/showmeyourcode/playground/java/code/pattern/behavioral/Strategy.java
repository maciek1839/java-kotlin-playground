package com.showmeyourcode.playground.java.code.pattern.behavioral;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

interface PaymentStrategy {
    void pay(int amount);
}

@Slf4j
class CreditCardPayment implements PaymentStrategy {
    private final String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(int amount) {
        log.info("Paid {} using Credit Card: {}", amount, cardNumber);
    }
}

@Setter
class ShoppingCart {
    private PaymentStrategy paymentStrategy;

    public void checkout(int amount) {
        if (paymentStrategy == null) {
            throw new IllegalStateException("Payment strategy not set");
        }
        paymentStrategy.pay(amount);
    }
}

@Slf4j
class PayPalPayment implements PaymentStrategy {
    private final String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(int amount) {
        log.info("Paid {} using PayPal: {}", amount, email);
    }
}

public class Strategy {

    private Strategy() {
    }

    public static void main(String[] args){
        ShoppingCart cart = new ShoppingCart();

        // Paying with a credit card
        cart.setPaymentStrategy(new CreditCardPayment("1234-5678-9876-5432"));
        cart.checkout(100);

        // Changing the strategy to PayPal
        cart.setPaymentStrategy(new PayPalPayment("user@example.com"));
        cart.checkout(200);
    }
}
