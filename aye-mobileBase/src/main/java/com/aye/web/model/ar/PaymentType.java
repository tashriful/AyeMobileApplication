package com.aye.web.model.ar;

public enum PaymentType {
    CASH("Cash"),
    CHEQUE("Cheque"),
    ONLINE("Online");

    private final String paymentType;

    PaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentType() {
        return paymentType;
    }
}
