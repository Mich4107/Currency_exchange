package ru.yuubi.currency_exchange.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yuubi.currency_exchange.dao.CurrencyRepository;
import ru.yuubi.currency_exchange.entity.Currency;
import ru.yuubi.currency_exchange.exception_handling.NoSuchCurrencyException;

import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService{

    @Autowired
    private CurrencyRepository currencyRepository;
    @Override
    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    @Override
    public Currency getCurrency(String code) {
        Currency currency = currencyRepository.getCurrencyByCode(code);
        if (currency == null) {
            throw new NoSuchCurrencyException("There is no currency with code '"+code+"' in Database");
        }
        return currency;
    }

    @Override
    public void saveCurrency(Currency currency) {
        currencyRepository.save(currency);
    }

    @Override
    public Currency getCurrencyById(int id) {
        return currencyRepository.getCurrencyById(id);
    }

}
