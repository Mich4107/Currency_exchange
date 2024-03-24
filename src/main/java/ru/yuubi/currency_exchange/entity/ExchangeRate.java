package ru.yuubi.currency_exchange.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import ru.yuubi.currency_exchange.exchange_serializer.ExchangeRateSerializer;

@Entity
@Table(name = "exchange_rates")
@JsonSerialize(using = ExchangeRateSerializer.class)
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exchange_id")
    private int id;

    @Column(name = "base_currency_id")
    private int baseCurrencyId;
    @Column(name = "target_currency_id")
    private int targetCurrencyId;

    @Column(name = "rate")
    private double rate;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "base_currency_id", insertable=false, updatable=false)
    private Currency baseCurrency;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "target_currency_id", insertable=false, updatable=false)
    private Currency targetCurrency;

    public ExchangeRate() {
    }

    public ExchangeRate(int baseCurrencyId, int targetCurrencyId, double rate) {
        this.baseCurrencyId = baseCurrencyId;
        this.targetCurrencyId = targetCurrencyId;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBaseCurrencyId() {
        return baseCurrencyId;
    }

    public void setBaseCurrencyId(int baseCurrency) {
        this.baseCurrencyId = baseCurrency;
    }

    public int getTargetCurrencyId() {
        return targetCurrencyId;
    }

    public void setTargetCurrencyId(int targetCurrency) {
        this.targetCurrencyId = targetCurrency;
    }


    public Currency getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(Currency base_currency) {
        this.baseCurrency = base_currency;
    }

    public Currency getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(Currency target_currency) {
        this.targetCurrency = target_currency;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "id=" + id +
                ", baseCurrency=" + baseCurrencyId +
                ", targetCurrency=" + targetCurrencyId +
                ", rate=" + rate +
                '}';
    }
}
