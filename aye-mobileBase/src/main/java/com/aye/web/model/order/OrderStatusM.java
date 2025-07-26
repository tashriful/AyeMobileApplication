package com.aye.web.model.order;

//@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum OrderStatusM {
    N("New"),
    P("Confirmed"),
    C("Canceled"),
    A("Approved");


    private final String displayName;

    OrderStatusM(String displayName) {
        this.displayName = displayName;
    }


    public String getDisplayName() {
        return displayName;
    }
}
