package ru.yuubi.currency_exchange.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yuubi.currency_exchange.dao.ExchangeRateRepository;
import ru.yuubi.currency_exchange.entity.Currency;
import ru.yuubi.currency_exchange.entity.ExchangeRate;

import java.util.List;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {
    @Autowired
    ExchangeRateRepository exchangeRateRepository;

    @Override
    public List<ExchangeRate> getExchangeRates() {
        return exchangeRateRepository.findAll();
    }

    @Override
    public ExchangeRate getExchangeRatesByCodes(Currency baseCurrency, Currency targetCurrency) {
        ExchangeRate exchangeRate = exchangeRateRepository.getExchangeRateByBaseCurrencyAndTargetCurrency(baseCurrency, targetCurrency);
        return exchangeRate;
    }

    @Override
    public void saveExchangeRate(ExchangeRate exchangeRate) {
        exchangeRateRepository.save(exchangeRate);
    }

    @Override
    public ExchangeRate getExchangeRateById(int id) {
        return exchangeRateRepository.getExchangeRateById(id);
    }
}
