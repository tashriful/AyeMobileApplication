package com.aye.web.model.common;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum CommonStatusM {
    Y("Active"),
    N("Inactive"),

    @JsonEnumDefaultValue
    U("Unknown");

    private final String dispalyName;

    private CommonStatusM(String dispalyName) {
        this.dispalyName = dispalyName;
    }

    @JsonValue
    public String getDispalyName() {
        return this.dispalyName;
    }
}
