package com.pragma.plazoletaservice.infrastructure.exceptionhandler;

public enum ExceptionResponse {
    NO_DATA_FOUND("No data found for the requested petition"),
    ALREADY_EXISTS("Already exists there data"),
    RESTAURANT_DOES_NOT_EXIST("The entered restaurant does not exist"),
    CATEGORY_DOES_NOT_EXIST("The entered category does not exist");

    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}