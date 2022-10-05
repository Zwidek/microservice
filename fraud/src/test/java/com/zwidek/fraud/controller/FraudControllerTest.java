package com.zwidek.fraud.controller;

import com.zwidek.fraud.model.FraudCheckHistory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


class FraudControllerTest {

    @Test
    @DisplayName("checkIfFraudCheckHistoryAllFieldsAreCorrect")
    void checkIfFraudCheckHistoryAllFieldsAreCorrect() {
        LocalDateTime now = LocalDateTime.now();
        FraudCheckHistory fraudCheckHistory1 = new FraudCheckHistory(5, 3, true, now);

        assertAll(
                () -> assertThat(fraudCheckHistory1.getId().intValue()).isEqualTo(5),
                () -> assertThat(fraudCheckHistory1.getCustomerId().intValue()).isEqualTo(3),
                () -> assertThat(fraudCheckHistory1.getIsFraudster().booleanValue()).isTrue(),
                () -> assertThat(fraudCheckHistory1.getCreatedAt()).isEqualTo(now)
        );
    }

        /*
        softAssertions.assertThat(fraudCheckHistory1.getId().intValue()).isEqualTo(5);
        softAssertions.assertThat(fraudCheckHistory1.getCustomerId().intValue()).isEqualTo(3);
        softAssertions.assertThat(fraudCheckHistory1.getIsFraudster().booleanValue()).isTrue();
        softAssertions.assertThat(fraudCheckHistory1.getCreatedAt()).isEqualTo(now);
        softAssertions.assertAll();
         */

    @Test
    void checkIfFraudCheckHistoryIsNotNull() {
        FraudCheckHistory fraudCheckHistory1 = new FraudCheckHistory(1, 1, true, LocalDateTime.now());
        //then
        Assertions.assertNotNull(fraudCheckHistory1, this::getErrorMessage);
    }

    @Test
    void checkIfFraudCheckHistoryIdIsNull() {
        FraudCheckHistory fraudCheckHistory1 = new FraudCheckHistory(null, 1, true, LocalDateTime.now());

        //then
        Assertions.assertNull(fraudCheckHistory1.getId(), this::getErrorMessage);
    }

    @Test
    void checkIfFraudCheckHistoryIsFraudster() {
        FraudCheckHistory fraudCheckHistory1 = new FraudCheckHistory(1, 1, false, LocalDateTime.now());

        //then
        Assertions.assertFalse(fraudCheckHistory1.getIsFraudster());
    }

    private String getErrorMessage() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Object cannot be null";
    }
}