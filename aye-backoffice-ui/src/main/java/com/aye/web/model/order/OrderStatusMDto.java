package com.aye.web.model.order;

//@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum OrderStatusMDto {
    N("New"),
    P("Confirmed"),
    C("Canceled"),
    A("Approved");


    private final String displayName;

    OrderStatusMDto(String displayName) {
        this.displayName = displayName;
    }


    public String getDisplayName() {
        return displayName;
    }
}
