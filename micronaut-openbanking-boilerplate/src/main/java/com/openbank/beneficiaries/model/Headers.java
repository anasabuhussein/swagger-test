package com.openbank.beneficiaries.model;

import lombok.Getter;

@Getter
public enum Headers {

    X_INTERACTION_ID("x-fapi-interaction-id"),
    X_REQUEST_ID ("x-request-id");

    private String value;

    Headers(String value) {
        this.value = value;
    }
}
