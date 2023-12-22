package com.github.yadavanuj.firedb.protocol;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusCodes {
    OK("ok"),
    ERROR("error");

    private final String value;

    StatusCodes(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
