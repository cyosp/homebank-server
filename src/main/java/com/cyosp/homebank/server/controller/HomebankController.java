package com.cyosp.homebank.server.controller;

import com.cyosp.homebank.server.request.OperationQueryRequest;
import com.cyosp.homebank.server.response.*;
import com.cyosp.homebank.server.service.HomebankService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.cyosp.homebank.server.model.HomeBank.NO_KEY;
import static java.lang.Long.MAX_VALUE;

@RestController
@RequestMapping("/homebank")
public class HomebankController {

    private final HomebankService homebankService;

    @Autowired
    public HomebankController(HomebankService homebankService) {
        this.homebankService = homebankService;
    }

    @GetMapping("/reload")
    public void reload() {
        homebankService.load();
    }

    @GetMapping("/payment-modes")
    public List<PaymentModeResponse> paymentModes() {
        return homebankService.paymentModes();
    }

    @GetMapping("/properties")
    public PropertiesResponse getProperties() {
        return homebankService.getProperties();
    }

    @GetMapping("/accounts")
    public List<AccountResponse> accounts() {
        return homebankService.accounts();
    }

    @GetMapping("/accounts/{accountId}/operations")
    public List<OperationResponse> operations(@PathVariable int accountId,
                                              @RequestParam(name = "limit", required = false, defaultValue = "" + MAX_VALUE) long limit,
                                              @RequestParam(name = "from", required = false, defaultValue = "" + NO_KEY) long from,
                                              @RequestParam(name = "q", required = false) String jsonOperationQueryRequest) throws IOException {

        final OperationQueryRequest operationQueryRequest = new ObjectMapper()
                .readValue(jsonOperationQueryRequest, OperationQueryRequest.class);

        return homebankService.operations(accountId, operationQueryRequest, from, limit);
    }

    @GetMapping("/accounts/{accountId}/categories")
    public List<CategoryResponse> categories(@PathVariable int accountId) {
        return homebankService.categories(accountId);
    }

    @GetMapping("/accounts/{id}/payees")
    public List<PayeeResponse> getPayeesByAccount(@PathVariable int id) {
        return homebankService.getPayeesByAccount(id);
    }

    @GetMapping("/currencies")
    public List<CurrencyResponse> getCurrencies() {
        return homebankService.getCurrencies();
    }

    @GetMapping("/favorites")
    public List<FavoriteResponse> getFavorites() {
        return homebankService.getFavorites();
    }

    @GetMapping("/tags")
    public List<TagResponse> getTags() {
        return homebankService.getTags();
    }
}
