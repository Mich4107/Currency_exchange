package ru.yuubi.currency_exchange.dto;

import lombok.Data;
import ru.yuubi.currency_exchange.entity.Currency;

import java.math.BigDecimal;

@Data
public class ExchangeDTO {
    private Currency baseCurrency;
    private Currency targetCurrency;
    private BigDecimal rate;
    private BigDecimal amount;
    private BigDecimal convertedAmount;
}
