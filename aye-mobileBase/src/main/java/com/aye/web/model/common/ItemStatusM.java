package com.aye.web.model.common;

public enum ItemStatusM {
    A("ACTIVE"),
    I("INACTIVE");

    private final String displayName;

    private ItemStatusM(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
