package ru.yuubi.currency_exchange.exchange_serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yuubi.currency_exchange.entity.Currency;
import ru.yuubi.currency_exchange.entity.ExchangeRate;
import ru.yuubi.currency_exchange.service.CurrencyService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class ExchangeRateSerializer extends JsonSerializer<ExchangeRate> {
    @Autowired
    CurrencyService currencyService;

    @Override
    public void serialize(ExchangeRate exchangeRate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", exchangeRate.getId());

        Currency baseCurrency = currencyService.getCurrencyById(exchangeRate.getBaseCurrencyId());
        Currency targetCurrency = currencyService.getCurrencyById(exchangeRate.getTargetCurrencyId());

        jsonGenerator.writeObjectFieldStart("baseCurrency");
        jsonGenerator.writeNumberField("id", baseCurrency.getId());
        jsonGenerator.writeStringField("name", baseCurrency.getName());
        jsonGenerator.writeStringField("code", baseCurrency.getCode());
        jsonGenerator.writeStringField("sign", baseCurrency.getSign());
        jsonGenerator.writeEndObject();

        jsonGenerator.writeObjectFieldStart("targetCurrency");
        jsonGenerator.writeNumberField("id", targetCurrency.getId());
        jsonGenerator.writeStringField("name", targetCurrency.getName());
        jsonGenerator.writeStringField("code", targetCurrency.getCode());
        jsonGenerator.writeStringField("sign", targetCurrency.getSign());
        jsonGenerator.writeEndObject();

        jsonGenerator.writeNumberField("rate", exchangeRate.getRate());

        jsonGenerator.writeEndObject();
    }
}
