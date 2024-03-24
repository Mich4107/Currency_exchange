package ru.yuubi.currency_exchange.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CurrencyGlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<CurrencyIncorrectData> handleException(NoSuchCurrencyException exception){
        CurrencyIncorrectData data = new CurrencyIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<CurrencyIncorrectData> handleException(NoSuchExchangeRateException exception){
        CurrencyIncorrectData data = new CurrencyIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<CurrencyIncorrectData> handleException(RuntimeException exception){
        CurrencyIncorrectData data = new CurrencyIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }
}
