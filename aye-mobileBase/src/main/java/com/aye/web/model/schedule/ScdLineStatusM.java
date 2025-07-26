package com.aye.web.model.schedule;

public enum ScdLineStatusM {
    N("New"),
    P("Post"),
    PA("Pre Approved"),
    A("Approve"),
    C("Canceled");


    private final String displayName;

    ScdLineStatusM(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
