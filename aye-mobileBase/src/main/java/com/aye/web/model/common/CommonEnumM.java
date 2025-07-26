package com.aye.web.model.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

public class CommonEnumM {

    public CommonEnumM() {
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public static enum CustomerTypes {
        DIST("Distributor"),
        SUD("Super Depot"),
        RET("Retailer"),
        EXP("Export");

        private final String displayName;

        private CustomerTypes(String displayName) {
            this.displayName = displayName;
        }

        @JsonValue
        public String getDisplayName() {
            return this.displayName;
        }
    }

}
