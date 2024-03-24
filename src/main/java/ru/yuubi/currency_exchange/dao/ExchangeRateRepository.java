package ru.yuubi.currency_exchange.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yuubi.currency_exchange.entity.Currency;
import ru.yuubi.currency_exchange.entity.ExchangeRate;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Integer> {
    ExchangeRate getExchangeRateByBaseCurrencyAndTargetCurrency(Currency baseCurrency, Currency targetCurrency);
    ExchangeRate getExchangeRateByTargetCurrencyAndBaseCurrency(Currency targetCurrency, Currency baseCurrency);
    ExchangeRate getExchangeRateById(int id);

}
