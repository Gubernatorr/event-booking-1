package com.eventbooking.common;

public abstract class DomainException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    protected DomainException(String message) {
        super(message);
    }

    protected DomainException(String message, Throwable cause) {
        super(message, cause);
    }

    /** Машиночитний код помилки, наприклад {@code "EVENT_NOT_FOUND"}. */
    public abstract String errorCode();
}