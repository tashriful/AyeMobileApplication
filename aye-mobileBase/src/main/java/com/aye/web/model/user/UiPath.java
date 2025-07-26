package com.aye.web.model.user;

public enum UiPath {
    ORD_UI("ord_ui");

    private final String uiPath;


    UiPath(String uiPath) {
        this.uiPath = uiPath;
    }

    public String getUiPath() {
        return uiPath;
    }
}
