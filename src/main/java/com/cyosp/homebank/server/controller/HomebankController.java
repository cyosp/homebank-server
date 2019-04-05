package com.cyosp.homebank.server.controller;

import com.cyosp.homebank.server.model.PaymentMode;
import com.cyosp.homebank.server.response.*;
import com.cyosp.homebank.server.service.HomebankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/homebank")
public class HomebankController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

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
    public List<PaymentModeResponse> getPaymentModes() {
        return PaymentMode.getPaymentModes().stream()
                .map(pm -> new PaymentModeResponse(pm.getCode(), pm.getName(), pm.getManaged()))
                .collect(Collectors.toList());
    }

    @GetMapping("/infos")
    public InfosResponse getInfos() {
        return homebankService.getInfos();
    }

    @GetMapping("/properties")
    public PropertiesResponse getProperties() {
        return homebankService.getProperties();
    }

    @GetMapping("/accounts")
    public List<AccountResponse> getAccounts() {
        List<AccountResponse> accounts = homebankService.getAccounts();
        LOGGER.info("Accounts: " + accounts);
        return accounts;
    }

    @GetMapping("/accounts/{id}/operations")
    public List<OperationResponse> getOperationsByAccount(@PathVariable int id) {
        return homebankService.getOperationsByAccount(id);
    }

    @GetMapping("/accounts/{id}/categories")
    public List<CategoryResponse> getCategoriesByAccount(@PathVariable int id) {
        return homebankService.getCategoriesByAccount(id);
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

    @GetMapping("/operations")
    public List<OperationResponse> getOperations() {
        return homebankService.getOperations();
    }

    @GetMapping("/tags")
    public List<TagResponse> getTags() {
        return homebankService.getTags();
    }
}
