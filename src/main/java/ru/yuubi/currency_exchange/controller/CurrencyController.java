package ru.yuubi.currency_exchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yuubi.currency_exchange.entity.Currency;
import ru.yuubi.currency_exchange.service.CurrencyService;

import java.util.List;

@RestController
public class CurrencyController {
    @Autowired
    private CurrencyService currencyService;

    //GET localhost:8080/currencies
    @GetMapping("/currencies")
    public List<Currency> getAllCurrencies(){
        return currencyService.getAllCurrencies();
    }

    //GET localhost:8080/currencies/USD
    @GetMapping("/currencies/{code}")
    public Currency getCurrency(@PathVariable String code){
        Currency currency = currencyService.getCurrency(code);
        return currency;
    }

    //POST localhost:8080/currencies?name=US dollar&code=USD&sign=$
    @PostMapping("/currencies")
    public Currency saveCurrency(@RequestParam("name") String name,
                                 @RequestParam("code") String code,
                                 @RequestParam("sign") String sign){

        Currency currency = new Currency(name, code, sign);
        currencyService.saveCurrency(currency);
        return currency;
    }
}
