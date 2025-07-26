package com.aye.web.model.common;

public enum OrgTypeM {
    BG("Business Group"),
    OU("Opareting Unit"),
    IO("Inventory Organization");

    private final String displayName;

    private OrgTypeM(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
