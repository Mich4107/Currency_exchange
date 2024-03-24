package ru.yuubi.currency_exchange.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yuubi.currency_exchange.entity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    Currency getCurrencyByCode(String code);
    Currency getCurrencyById(int id);
}
