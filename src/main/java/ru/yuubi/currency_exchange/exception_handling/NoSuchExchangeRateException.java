package ru.yuubi.currency_exchange.exception_handling;

public class NoSuchExchangeRateException extends RuntimeException{
    public NoSuchExchangeRateException(String message) {
        super(message);
    }
}
