package com.cyosp.homebank.server.service;

import com.cyosp.homebank.server.model.*;
import com.cyosp.homebank.server.repository.HomebankRepository;
import com.cyosp.homebank.server.response.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.cyosp.homebank.server.model.PaymentMode.NO_PAYMENT_MODE_DEFINED;
import static com.cyosp.homebank.server.model.PaymentMode.getPaymentModes;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

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

    public InfosResponse getInfos() {
        InfosResponse ret = new InfosResponse();

        HomeBank homeBank = homebankRepository.getInfos();
        ret.setV(homeBank.getV());
        ret.setD(homeBank.getD());

        return ret;
    }

    public List<PaymentModeResponse> paymentModes() {
        return getPaymentModes().stream()
                .map(PaymentModeResponse::from)
                .collect(toList());
    }

    public PropertiesResponse getProperties() {
        PropertiesResponse ret = new PropertiesResponse();

        Properties properties = homebankRepository.getProperties();
        BeanUtils.copyProperties(properties, ret);

        return ret;
    }

    private String formatAmount(BigDecimal amount, Currency currency) {
        StringBuilder pattern = new StringBuilder("#,##0.");
        for (int i = 0; i < currency.getFrac(); i++)
            pattern.append("0");
        DecimalFormat df = new DecimalFormat(pattern.toString());
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(currency.getGchar());
        symbols.setDecimalSeparator(currency.getDchar());
        df.setDecimalFormatSymbols(symbols);
        // TODO : Change how symbol is defined and placed
        // https://stackoverflow.com/questions/29215163/currency-symbol-with-another-number-format
        return df.format(amount) + " " + currency.getSymb();
    }

    public List<AccountResponse> accounts() {
        List<AccountResponse> ret = new ArrayList<>();

        for (Account account : homebankRepository.getAccounts()) {
            Options options = new Options();
            if (account.getFlags() != null) options.setOptions(account.getFlags());

            AccountResponse accountResponse = new AccountResponse();
            BeanUtils.copyProperties(account, accountResponse);

            OptionsResponse optionsResponse = new OptionsResponse();
            BeanUtils.copyProperties(options, optionsResponse);
            accountResponse.setOptions(optionsResponse);

            Currency currency = homebankRepository.currency(account);
            CurrencyResponse currencyResponse = new CurrencyResponse();
            BeanUtils.copyProperties(currency, currencyResponse);
            accountResponse.setCurrency(currencyResponse);

            BigDecimal balance = homebankRepository.operations(account)
                    .map(Operation::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            accountResponse.setBalance(formatAmount(balance, currency));

            ret.add(accountResponse);
        }

        return ret;
    }

    public List<OperationResponse> getOperationsByAccount(int id) {
        List<OperationResponse> ret = new ArrayList<>();

        homebankRepository.operations(homebankRepository.account(id)).forEach(operation -> {
            operation.convertJulianToDate();
            OperationResponse operationResponse = new OperationResponse();
            BeanUtils.copyProperties(operation, operationResponse);
            operationResponse.setDateFormatted(SIMPLE_DATE_FORMAT.format(operation.getJavaDate()));

            PaymentMode paymentMode;
            if (ofNullable(operation.getPaymode()).isPresent()) {
                paymentMode = PaymentMode.getPaymentModes().stream()
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
            BeanUtils.copyProperties(category, categoryResponse);
            categoryResponse.setBalance(formatAmount(category.getBalance(), category.getCurrency()));
            ret.add(categoryResponse);
        }

        return ret;
    }

    public List<PayeeResponse> getPayeesByAccount(int id) {
        List<PayeeResponse> ret = new ArrayList<>();

        for (Payee payee : homebankRepository.getPayeesByAccount(id)) {
            PayeeResponse payeeResponse = new PayeeResponse();
            BeanUtils.copyProperties(payee, payeeResponse);
            payeeResponse.setBalance(formatAmount(payee.getBalance(), payee.getCurrency()));
            ret.add(payeeResponse);
        }

        return ret;
    }

    public List<CurrencyResponse> getCurrencies() {
        List<CurrencyResponse> ret = new ArrayList<>();

        for (Currency currency : homebankRepository.getCurrencies()) {
            CurrencyResponse currencyResponse = new CurrencyResponse();
            BeanUtils.copyProperties(currency, currencyResponse);
            ret.add(currencyResponse);
        }

        return ret;
    }

    public List<FavoriteResponse> getFavorites() {
        List<FavoriteResponse> ret = new ArrayList<>();

        for (Favorite favorite : homebankRepository.getFavorites()) {
            FavoriteResponse favoriteResponse = new FavoriteResponse();
            BeanUtils.copyProperties(favorite, favoriteResponse);
            ret.add(favoriteResponse);
        }

        return ret;
    }

    public List<OperationResponse> getOperations() {
        List<OperationResponse> ret = new ArrayList<>();

        for (Operation operation : homebankRepository.operations()) {
            operation.convertJulianToDate();
            OperationResponse operationResponse = new OperationResponse();
            BeanUtils.copyProperties(operation, operationResponse);
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
            BeanUtils.copyProperties(tag, tagResponse);
            ret.add(tagResponse);
        }

        return ret;
    }
}
