package com.aye.web.model.payment;

public enum PaymentTypeDto {
    CASH("Cash"),
    CHEQUE("Cheque"),
    ONLINE("Online");

    private final String paymentType;

    PaymentTypeDto(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentType() {
        return paymentType;
    }
}
