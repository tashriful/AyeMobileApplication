package com.aye.web.model.order;

public enum DeliveryStatusM {

    N("New"),
    P("Confirmed"),
    I("Invoiced"),
    C("Cancel");


    private final String displayName;

    DeliveryStatusM(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
