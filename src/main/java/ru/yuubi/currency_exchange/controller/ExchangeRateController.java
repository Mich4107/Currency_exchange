package ru.yuubi.currency_exchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yuubi.currency_exchange.entity.Currency;
import ru.yuubi.currency_exchange.entity.ExchangeRate;
import ru.yuubi.currency_exchange.service.CurrencyService;
import ru.yuubi.currency_exchange.service.ExchangeRateService;
import ru.yuubi.currency_exchange.dto.ExchangeDTO;
import ru.yuubi.currency_exchange.exception_handling.NoSuchExchangeRateException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@RestController
public class ExchangeRateController {
    @Autowired
    ExchangeRateService exchangeRateService;

    @Autowired
    CurrencyService currencyService;

    //GET localhost:8080/exchangeRates
    @GetMapping("/exchangeRates")
    public List<ExchangeRate> getAllExchangeRates(){
        return exchangeRateService.getExchangeRates();
    }

    //GET localhost:8080/exchangeRate/USDEUR
    @GetMapping("/exchangeRate/{codes}")
    public ExchangeRate getExchangeRatesByCodes(@PathVariable String codes){
        StringBuilder sb = new StringBuilder(codes);
        String code1;
        String code2;
        try {
            code1 = sb.substring(0,3);
            code2 = sb.substring(3,6);
        } catch (IndexOutOfBoundsException e){
            throw new NoSuchExchangeRateException("You wrote the first or second code incorrectly in terms of the number of characters");
        }

        Currency baseCurrency = currencyService.getCurrency(code1);
        Currency targetCurrency = currencyService.getCurrency(code2);

        ExchangeRate exchangeRate = exchangeRateService.getExchangeRatesByCodes(baseCurrency, targetCurrency);

        if(exchangeRate==null) {
            throw new NoSuchExchangeRateException("There is no exchange rate "+baseCurrency.getCode()+" -> "+targetCurrency.getCode());
        }

        return exchangeRate;
    }

    //POST localhost:8080/exchangeRates
    @PostMapping("/exchangeRates")
    public ExchangeRate saveExchangeRate(@RequestParam("baseCurrencyCode") String baseCurrencyCode,
                                         @RequestParam("targetCurrencyCode") String targetCurrencyCode,
                                         @RequestParam("rate") double rate){

        int baseCurrencyId = currencyService.getCurrency(baseCurrencyCode).getId();
        int targetCurrencyId = currencyService.getCurrency(targetCurrencyCode).getId();

        ExchangeRate exchangeRate = new ExchangeRate(baseCurrencyId, targetCurrencyId, rate);

        exchangeRateService.saveExchangeRate(exchangeRate);
        return exchangeRate;
    }

    //PATCH localhost:8080/exchangeRate/USDEUR
    @PatchMapping("/exchangeRate/{codes}")
    public ExchangeRate updateExchangeRate(@RequestParam("rate") double rate, @PathVariable String codes) {
        ExchangeRate exchangeRate1 = getExchangeRatesByCodes(codes);
        exchangeRate1.setRate(rate);
        exchangeRateService.saveExchangeRate(exchangeRate1);
        return exchangeRate1;
    }

    // GET localhost:8080/exchange?from=BASE_CURRENCY_CODE&to=TARGET_CURRENCY_CODE&amount=AMOUNT
    @GetMapping("/exchange")
    public ExchangeDTO getExchangeRate(@RequestParam String from,
                                       @RequestParam String to,
                                       @RequestParam BigDecimal amount){
        //initializing variables
        ExchangeRate exchangeRate;
        BigDecimal rate = null;
        BigDecimal convertedAmount;

        Currency baseCurrency = currencyService.getCurrency(from);
        Currency targetCurrency = currencyService.getCurrency(to);

        //if u need A->B, and its already exists
        if(exchangeRateService.getExchangeRatesByCodes(baseCurrency, targetCurrency)!=null) {
            exchangeRate = exchangeRateService.getExchangeRatesByCodes(baseCurrency, targetCurrency);
            rate = BigDecimal.valueOf(exchangeRate.getRate());
        } //if u need A->B, but only B->A exists
        else if(exchangeRateService.getExchangeRatesByCodes(targetCurrency, baseCurrency)!=null){
            exchangeRate = exchangeRateService.getExchangeRatesByCodes(targetCurrency, baseCurrency);
            rate = BigDecimal.valueOf(1 / exchangeRate.getRate());
            rate = formatBigDecimalValue(rate, 6);
        } //if u need A->B, but we have USD -> A && USD -> B.
        else {
            Currency dollarCurrency = currencyService.getCurrency("USD");
            if(exchangeRateService.getExchangeRatesByCodes(dollarCurrency, baseCurrency) != null && exchangeRateService.getExchangeRatesByCodes(dollarCurrency, targetCurrency) != null){
                ExchangeRate exchangeRate1 = exchangeRateService.getExchangeRatesByCodes(dollarCurrency, baseCurrency);
                ExchangeRate exchangeRate2 = exchangeRateService.getExchangeRatesByCodes(dollarCurrency, targetCurrency);

                rate = BigDecimal.valueOf(exchangeRate2.getRate()/exchangeRate1.getRate());
                rate = formatBigDecimalValue(rate, 6);
            }

        }

        ExchangeDTO exchangeDTO = new ExchangeDTO();
        exchangeDTO.setBaseCurrency(baseCurrency);
        exchangeDTO.setTargetCurrency(targetCurrency);
        exchangeDTO.setRate(rate);
        exchangeDTO.setAmount(amount);

        String result = formatBigDecimalValue(rate.multiply(amount), 2).stripTrailingZeros().toPlainString();
        convertedAmount = new BigDecimal(result);

        exchangeDTO.setConvertedAmount(convertedAmount);

        return exchangeDTO;
    }

    public BigDecimal formatBigDecimalValue(BigDecimal num, int scale) {
        BigDecimal result = num;
        result = result.setScale(scale, RoundingMode.UP);
        return result;
    }
}
