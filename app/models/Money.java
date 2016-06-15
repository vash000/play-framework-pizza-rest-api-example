package models;

import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.Currency;

@Embeddable
public class Money { //Replace with some impl of JSR 354 when some financial operation will be performed


    private String currency;

    @Digits(integer=10, fraction=2)
    public BigDecimal amount;

    public void setCurrency(Currency currency) {
        this.currency = currency.getCurrencyCode();
    }

    public Currency getCurrency() {
        return Currency.getInstance(currency);
    }
}
