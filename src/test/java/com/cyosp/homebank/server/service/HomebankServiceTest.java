package com.cyosp.homebank.server.service;

import com.cyosp.homebank.server.model.Currency;
import com.cyosp.homebank.server.repository.HomebankRepository;
import com.cyosp.homebank.server.response.PaymentModeResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import static java.time.LocalDate.parse;
import static java.time.format.DateTimeFormatter.ofPattern;
import static org.junit.Assert.assertEquals;
import static org.springframework.context.i18n.LocaleContextHolder.setLocale;

class HomebankServiceTest {

    private HomebankService homebankService;

    private String resourceFolder;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        homebankService = new HomebankService(new HomebankRepository());
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
    void formatAmount() {
        BigDecimal amount = new BigDecimal("1234.5");

        Currency currency = new Currency();
        currency.setIso("EUR");

        setLocale(new Locale("fr", "FR"));

        assertEquals("1 234,50 €", homebankService.formatAmount(amount, currency));
    }

    @Test
    void formatDate() {
        LocalDate localDate = parse("2019-04-28", ofPattern("yyyy-MM-dd"));

        setLocale(new Locale("fr", "FR"));

        assertEquals("28/04/2019", homebankService.formatDate(localDate));
    }
}
