package com.aye.web.model.common;

public enum ModuleType {
    ORD("ORDER"),
    C("COLLECTION");

    private final String displayName;


    ModuleType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
