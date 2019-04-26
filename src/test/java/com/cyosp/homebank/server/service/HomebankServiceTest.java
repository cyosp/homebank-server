package com.cyosp.homebank.server.service;

import com.cyosp.homebank.server.model.Currency;
import com.cyosp.homebank.server.repository.HomebankRepository;
import com.cyosp.homebank.server.response.PaymentModeResponse;
import com.cyosp.homebank.server.response.PropertiesResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.springframework.context.i18n.LocaleContextHolder.setLocale;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class HomebankServiceTest {

    @Autowired
    private HomebankRepository homebankRepository;

    private HomebankService homebankService;

    private String resourceFolder;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        homebankService = new HomebankService(homebankRepository);
        resourceFolder = getClass().getPackage().getName().replaceAll("\\.", "/");
        objectMapper = new ObjectMapper();
    }

    @Test
    void paymentModes() throws IOException {
        final List<PaymentModeResponse> paymentModes = objectMapper.readValue(
                getClass().getClassLoader().getResourceAsStream(resourceFolder + "/PaymentModeResponseList.json"),
                new TypeReference<List<PaymentModeResponse>>() {
                });

        assertEquals(paymentModes, homebankService.paymentModes());
    }

    @Test
    void properties() throws IOException {
        final PropertiesResponse propertiesResponse = objectMapper.readValue(
                getClass().getClassLoader().getResourceAsStream(resourceFolder + "/PropertiesResponse.json"),
                new TypeReference<PropertiesResponse>() {
                });

        assertEquals(propertiesResponse, homebankService.getProperties());
    }

    @Test
    void formatAmount() {
        BigDecimal amount = new BigDecimal("123.4");

        Currency currency = new Currency();
        currency.setIso("EUR");

        setLocale(new Locale("fr", "FR"));

        assertEquals("123,40 €", homebankService.formatAmount(amount, currency));
    }
}
