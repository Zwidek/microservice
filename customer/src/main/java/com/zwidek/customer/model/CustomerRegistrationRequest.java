package com.zwidek.customer.model;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email
) {
}
