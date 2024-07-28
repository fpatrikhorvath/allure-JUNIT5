package com.framework.allure.rest.response;

public enum ResponseErrorEnum {
    USER_NOT_FOUND("User not found"),
    // Add more error messages here as needed
    ;

    private final String message;

    ResponseErrorEnum(final String message) {
        this.message = message;
    }

    public static ResponseErrorEnum getByMessage(final String message) {
        for (ResponseErrorEnum e : ResponseErrorEnum.values()) {
            if (e.message.equals(message)) {
                return e;
            }
        }
        throw new IllegalArgumentException("No enum constant with value: " + message);
    }
}
