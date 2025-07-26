package com.aye.web.model.ar;

public enum PaymentStatus {
    N("New"), P("Posted"),R("Revised");
    private final String displayName;

    private PaymentStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
