package com.eventbooking.common;

/** Запитаний ресурс не існує. Мапиться на HTTP 404. */
public class NotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    private final String errorCode;

    public NotFoundException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    @Override
    public String errorCode() {
        return errorCode;
    }
}

