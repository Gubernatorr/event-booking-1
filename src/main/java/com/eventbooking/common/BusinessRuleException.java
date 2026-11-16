package com.eventbooking.common;

/**
 * Запит синтаксично коректний, але порушує бізнес-правило
 * (наприклад, подія в минулому). Мапиться на HTTP 422.
 */
public class BusinessRuleException extends DomainException {

    private static final long serialVersionUID = 1L;

    private final String errorCode;

    public BusinessRuleException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    @Override
    public String errorCode() {
        return errorCode;
    }
}
