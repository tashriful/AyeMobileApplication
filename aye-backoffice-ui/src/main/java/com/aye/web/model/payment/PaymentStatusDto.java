package com.aye.web.model.payment;

public enum PaymentStatusDto {
    N("New"), P("Posted"),R("Revised");
    private final String displayName;

    private PaymentStatusDto(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
