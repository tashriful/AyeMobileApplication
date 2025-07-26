package com.aye.web.model;

public enum UiPathDto {
    ORD_UI("ord_ui");

    private final String uiPath;


    UiPathDto(String uiPath) {
        this.uiPath = uiPath;
    }

    public String getUiPath() {
        return uiPath;
    }
}
