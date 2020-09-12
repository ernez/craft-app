package com.ernez.craftapp.domain.enumeration;

public enum UserStatus {
    ACTIVE("active"),
    BLOCKED("blocked");

    private String value;

    UserStatus(String value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
