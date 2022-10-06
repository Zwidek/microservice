package com.zwidek.customer.model;

import com.zwidek.customer.service.CustomerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


@ExtendWith(MockitoExtension.class)
class CustomerTest {


    @Test
    void verifyThatTestRuns3Times() {
        CustomerService mock = Mockito.mock(CustomerService.class);
        mock.findCustomerById(1);
        mock.findCustomerById(1);
        mock.findCustomerById(1);
        Mockito.verify(mock, Mockito.times(3)).findCustomerById(1);
    }
    @Test
    void shouldAlwaysReturnGivenName() {
        Customer customer = Mockito.mock(Customer.class);

        Mockito.when(customer.getFirstName()).thenReturn("Marcin", "Janek", "Grzesiek");

        assertThat(customer.getFirstName()).isEqualTo("Marcin");
        assertThat(customer.getFirstName()).isEqualTo("Janek");
        assertThat(customer.getFirstName()).isEqualTo("Grzesiek");
    }

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
