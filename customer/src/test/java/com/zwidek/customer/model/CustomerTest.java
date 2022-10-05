package com.zwidek.customer.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class CustomerTest {


    @Test
    @DisplayName("Check all fields of customer")
    void checkIfAllFieldsAreCorrect() {
        Customer customer = new Customer(1, "Jan", "Kowalski", "jan@gmail.com");
        assertAll(
                () -> assertThat(customer.getId()).isEqualTo(1),
                () -> assertThat(customer.getFirstName()).isEqualTo("Jan"),
                () -> assertThat(customer.getLastName()).isEqualTo("Kowalski"),
                () -> assertThat(customer.getEmail()).isEqualTo("jan@gmail.com"));
    }
}
