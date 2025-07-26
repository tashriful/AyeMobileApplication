package com.aye.web.model;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum CommonStatusMDto {
    Y("Active"),
    N("Inactive"),

    @JsonEnumDefaultValue
    U("Unknown");

    private final String dispalyName;

    private CommonStatusMDto(String dispalyName) {
        this.dispalyName = dispalyName;
    }

    @JsonValue
    public String getDispalyName() {
        return this.dispalyName;
    }
}
