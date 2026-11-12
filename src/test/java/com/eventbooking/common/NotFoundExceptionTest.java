package com.eventbooking.common;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NotFoundExceptionTest {

    @Test
    void keepsCodeAndMessage() {
        NotFoundException e = new NotFoundException("EVENT_NOT_FOUND", "Подію не знайдено");

        assertThat(e.errorCode()).isEqualTo("EVENT_NOT_FOUND");
        assertThat(e.getMessage()).isEqualTo("Подію не знайдено");
    }
}
