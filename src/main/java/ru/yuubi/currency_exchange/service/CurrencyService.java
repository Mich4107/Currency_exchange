package ru.yuubi.currency_exchange.service;

import ru.yuubi.currency_exchange.entity.Currency;

import java.util.List;

public interface CurrencyService {
    List<Currency> getAllCurrencies();
    Currency getCurrency(String name);
    void saveCurrency(Currency currency);
    Currency getCurrencyById(int id);
}
