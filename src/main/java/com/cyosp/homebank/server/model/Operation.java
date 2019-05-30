package com.cyosp.homebank.server.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static java.time.LocalDate.ofInstant;
import static java.time.ZoneId.systemDefault;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Getter
@Setter
public class Operation {

    @XStreamOmitField
    private Integer key;

    @XStreamAsAttribute
    @XStreamAlias("date")
    private Long date;

    @XStreamAsAttribute
    @XStreamAlias("amount")
    private BigDecimal amount;

    @XStreamAsAttribute
    @XStreamAlias("account")
    private Integer account;

    @XStreamAsAttribute
    @XStreamAlias("paymode")
    private Integer paymode;

    @XStreamAsAttribute
    @XStreamAlias("flags")
    private Integer flags;

    @XStreamAsAttribute
    @XStreamAlias("payee")
    private Integer payeeKey;
    @XStreamOmitField
    private Payee payee;

    @XStreamAsAttribute
    @XStreamAlias("category")
    private Integer categoryKey;
    @XStreamOmitField
    private Category category;

    @XStreamAsAttribute
    @XStreamAlias("wording")
    private String wording;

    @XStreamOmitField
    private BigDecimal balance;

    public LocalDate localDate() {
        final long DAY_TO_MILLISECONDS = 24 * 60 * 60 * 1000L;

        long diffInMs = date * DAY_TO_MILLISECONDS;

        GregorianCalendar gregorianCalendar = new GregorianCalendar(1, GregorianCalendar.JANUARY, 1);
        long dateInMs = diffInMs + gregorianCalendar.getTimeInMillis();
        int dayNbr = (int) (dateInMs / DAY_TO_MILLISECONDS);

        GregorianCalendar newGregorianCalendar = new GregorianCalendar();
        newGregorianCalendar.setTimeInMillis(0);
        newGregorianCalendar.add(GregorianCalendar.DAY_OF_MONTH, dayNbr + 2);

        TimeZone timeZone = newGregorianCalendar.getTimeZone();
        ZoneId zoneId = timeZone == null ? systemDefault() : timeZone.toZoneId();
        return ofInstant(newGregorianCalendar.toInstant(), zoneId);
    }

    public boolean filter(OperationQueryModel operationQueryModel) {
        final String wordingQuery = operationQueryModel.getWording();
        return isNotEmpty(wording) && isNotEmpty(wordingQuery) && wording.contains(wordingQuery);
    }
}
