package ru.yuubi.currency_exchange.exception_handling;

public class NoSuchCurrencyException extends RuntimeException{
    public NoSuchCurrencyException(String message) {
        super(message);
    }
}
