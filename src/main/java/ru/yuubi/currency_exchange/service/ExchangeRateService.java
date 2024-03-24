package ru.yuubi.currency_exchange.service;

import ru.yuubi.currency_exchange.entity.Currency;
import ru.yuubi.currency_exchange.entity.ExchangeRate;

import java.util.List;

public interface ExchangeRateService {
    List<ExchangeRate> getExchangeRates();
    ExchangeRate getExchangeRatesByCodes(Currency baseCurrency, Currency targetCurrency);
    void saveExchangeRate(ExchangeRate exchangeRate);
    ExchangeRate getExchangeRateById(int id);
}
