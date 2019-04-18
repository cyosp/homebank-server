package com.cyosp.homebank.server.service;

import com.cyosp.homebank.server.response.PaymentModeResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class HomebankServiceTest {

    @Autowired
    private HomebankService homebankService;

    private String resourceFolder;

    @BeforeEach
    void setUp() {
        resourceFolder = getClass().getPackage().getName().replaceAll("\\.", "/");
    }

    @Test
    void paymentModes() throws IOException {

        final List<PaymentModeResponse> paymentModes = new ObjectMapper().readValue(
                getClass().getClassLoader().getResourceAsStream(resourceFolder + "/PaymentModeResponseList.json"),
                new TypeReference<List<PaymentModeResponse>>() {
                });

        assertEquals(paymentModes, homebankService.paymentModes());
    }
}
