package com.cyosp.homebank.server.service;

import com.cyosp.homebank.server.model.*;
import com.cyosp.homebank.server.repository.HomebankRepository;
import com.cyosp.homebank.server.response.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.cyosp.homebank.server.model.PaymentMode.NO_PAYMENT_MODE_DEFINED;
import static com.cyosp.homebank.server.model.PaymentMode.PAYMENT_MODES;
import static java.text.NumberFormat.getCurrencyInstance;
import static java.util.Currency.getInstance;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static org.springframework.beans.BeanUtils.copyProperties;
import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Service
public class HomebankService {

    private final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    private final HomebankRepository homebankRepository;

    public HomebankService(HomebankRepository homebankRepository) {
        this.homebankRepository = homebankRepository;
    }

    public void load() {
        homebankRepository.load();
    }

    public List<PaymentModeResponse> paymentModes() {
        return PAYMENT_MODES.stream()
                .map(PaymentModeResponse::from)
                .collect(toList());
    }

    public PropertiesResponse getProperties() {
        PropertiesResponse ret = new PropertiesResponse();

        Properties properties = homebankRepository.getProperties();
        copyProperties(properties, ret);

        return ret;
    }

    String formatAmount(BigDecimal amount, Currency currency) {
        NumberFormat formatter = getCurrencyInstance(getLocale());
        formatter.setCurrency(getInstance(currency.getIso()));
        return formatter.format(amount);
    }

    public List<AccountResponse> accounts() {
        return homebankRepository.getAccounts().stream()
                .map(account -> {
                    AccountResponse accountResponse = new AccountResponse();
                    copyProperties(account, accountResponse);

                    AccountParamsResponse accountParamsResponse = new AccountParamsResponse();
                    copyProperties(new AccountParams(ofNullable(account.getFlags()).orElse(0)), accountParamsResponse);
                    accountResponse.setParams(accountParamsResponse);

                    accountResponse.setBalance(balanceFormatted(account));

                    return accountResponse;
                }).collect(toList());
    }

    BigDecimal balance(Account account) {
        return homebankRepository.operations(account)
                .map(Operation::getAmount)
                .reduce(account.getInitial(), BigDecimal::add);
    }

    String balanceFormatted(Account account)
    {
        return formatAmount(balance(account), homebankRepository.currency(account));
    }

    public List<OperationResponse> getOperationsByAccount(int id) {
        List<OperationResponse> ret = new ArrayList<>();

        homebankRepository.operations(homebankRepository.account(id)).forEach(operation -> {
            operation.convertJulianToDate();
            OperationResponse operationResponse = new OperationResponse();
            copyProperties(operation, operationResponse);
            operationResponse.setDateFormatted(SIMPLE_DATE_FORMAT.format(operation.getJavaDate()));

            PaymentMode paymentMode;
            if (ofNullable(operation.getPaymode()).isPresent()) {
                paymentMode = PAYMENT_MODES.stream()
                        .filter(pm -> pm.getCode().equals(operation.getPaymode()))
                        .findFirst()
                        .orElse(NO_PAYMENT_MODE_DEFINED);
            } else paymentMode = NO_PAYMENT_MODE_DEFINED;
            operationResponse.setPaymodeName(paymentMode.getName());

            operationResponse.setAmount(formatAmount(operation.getAmount(), operation.getCurrency()));
            operationResponse.setBalance(formatAmount(operation.getBalance(), operation.getCurrency()));
            ret.add(operationResponse);
        });

        return ret;
    }

    public List<CategoryResponse> getCategoriesByAccount(int id) {
        List<CategoryResponse> ret = new ArrayList<>();

        for (Category category : homebankRepository.getCategoriesByAccount(id)) {
            CategoryResponse categoryResponse = new CategoryResponse();
            copyProperties(category, categoryResponse);
            categoryResponse.setBalance(formatAmount(category.getBalance(), category.getCurrency()));
            ret.add(categoryResponse);
        }

        return ret;
    }

    public List<PayeeResponse> getPayeesByAccount(int id) {
        List<PayeeResponse> ret = new ArrayList<>();

        for (Payee payee : homebankRepository.getPayeesByAccount(id)) {
            PayeeResponse payeeResponse = new PayeeResponse();
            copyProperties(payee, payeeResponse);
            payeeResponse.setBalance(formatAmount(payee.getBalance(), payee.getCurrency()));
            ret.add(payeeResponse);
        }

        return ret;
    }

    public List<CurrencyResponse> getCurrencies() {
        List<CurrencyResponse> ret = new ArrayList<>();

        for (Currency currency : homebankRepository.getCurrencies()) {
            CurrencyResponse currencyResponse = new CurrencyResponse();
            copyProperties(currency, currencyResponse);
            ret.add(currencyResponse);
        }

        return ret;
    }

    public List<FavoriteResponse> getFavorites() {
        List<FavoriteResponse> ret = new ArrayList<>();

        for (Favorite favorite : homebankRepository.getFavorites()) {
            FavoriteResponse favoriteResponse = new FavoriteResponse();
            copyProperties(favorite, favoriteResponse);
            ret.add(favoriteResponse);
        }

        return ret;
    }

    public List<OperationResponse> getOperations() {
        List<OperationResponse> ret = new ArrayList<>();

        for (Operation operation : homebankRepository.operations()) {
            operation.convertJulianToDate();
            OperationResponse operationResponse = new OperationResponse();
            copyProperties(operation, operationResponse);
            operationResponse.setDateFormatted(SIMPLE_DATE_FORMAT.format(operation.getJavaDate()));
            operationResponse.setAmount(formatAmount(operation.getAmount(), operation.getCurrency()));
            ret.add(operationResponse);
        }

        return ret;
    }

    public List<TagResponse> getTags() {
        List<TagResponse> ret = new ArrayList<>();

        for (Tag tag : homebankRepository.getTags()) {
            TagResponse tagResponse = new TagResponse();
            copyProperties(tag, tagResponse);
            ret.add(tagResponse);
        }

        return ret;
    }
}
