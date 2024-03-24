<h1 align="center">Currency Exchange</h1>

![Java](https://img.shields.io/badge/java-black.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-black?style=for-the-badge&logo=Spring&logoColor=white)
![Static Badge](https://img.shields.io/badge/mvc(s)-black?style=for-the-badge&labelColor=white)
![MySQL](https://img.shields.io/badge/MySQL-black?style=for-the-badge&labelColor=white)
![HTTP](https://img.shields.io/badge/http-black?style=for-the-badge&labelColor=white)
![Rest Api](https://img.shields.io/badge/REST%20API-black?style=for-the-badge&labelColor=white)
![JSON](https://img.shields.io/badge/json-black?style=for-the-badge&labelColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-black?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Postman](https://img.shields.io/badge/postman-black?style=for-the-badge&logo=postman&logoColor=white)



### О проекте
REST API для описания валют и обменных курсов. Позволяет просматривать и редактировать списки валют и обменных курсов, и совершать расчёт конвертации произвольных сумм из одной валюты в другую. Веб-интерфейс для проекта не подразумевается.


### Использование приложения
#### GET `/currencies`

Получение списка валют. Пример ответа:
```
[
    {
        "id": 0,
        "name": "United States dollar",
        "code": "USD",
        "sign": "$"
    },   
    {
        "id": 1,
        "name": "Euro",
        "code": "EUR",
        "sign": "€"
    }
]
```

#### GET `/currency/EUR`

Получение конкретной валюты. Пример ответа:
```
{
    "id": 0,
    "name": "Euro",
    "code": "EUR",
    "sign": "€"
}
```


#### POST `/currencies`

Добавление новой валюты в базу. Данные передаются в теле запроса в виде полей формы (`x-www-form-urlencoded`). Поля формы - `name`, `code`, `sign`. Пример ответа - JSON представление вставленной в базу записи, включая её ID:
```
{
    "id": 0,
    "name": "Euro",
    "code": "EUR",
    "sign": "€"
}
```


### Обменные курсы

#### GET `/exchangeRates`

Получение списка всех обменных курсов. Пример ответа:
```
[
    {
        "id": 0,
        "baseCurrency": {
            "id": 0,
            "name": "United States dollar",
            "code": "USD",
            "sign": "$"
        },
        "targetCurrency": {
            "id": 1,
            "name": "Euro",
            "code": "EUR",
            "sign": "€"
        },
        "rate": 0.99
    }
]
```


#### GET `/exchangeRate/USDRUB`

Получение конкретного обменного курса. Валютная пара задаётся идущими подряд кодами валют в адресе запроса. Пример ответа:
```
{
    "id": 0,
    "baseCurrency": {
        "id": 0,
        "name": "United States dollar",
        "code": "USD",
        "sign": "$"
    },
    "targetCurrency": {
        "id": 1,
        "name": "Euro",
        "code": "EUR",
        "sign": "€"
    },
    "rate": 0.99
}

```


#### POST `/exchangeRates`

Добавление нового обменного курса в базу. Данные передаются в теле запроса в виде полей формы (`x-www-form-urlencoded`). Поля формы - `baseCurrencyCode`, `targetCurrencyCode`, `rate`. Пример полей формы:
- `baseCurrencyCode` - USD
- `targetCurrencyCode` - EUR
- `rate` - 0.99

Пример ответа - JSON представление вставленной в базу записи, включая её ID:
```
{
    "id": 0,
    "baseCurrency": {
        "id": 0,
        "name": "United States dollar",
        "code": "USD",
        "sign": "$"
    },
    "targetCurrency": {
        "id": 1,
        "name": "Euro",
        "code": "EUR",
        "sign": "€"
    },
    "rate": 0.99
}
```


#### PATCH `/exchangeRate/USDRUB`

Обновление существующего в базе обменного курса. Валютная пара задаётся идущими подряд кодами валют в адресе запроса. Данные передаются в теле запроса в виде полей формы (`x-www-form-urlencoded`). Единственное поле формы - `rate`.

Пример ответа - JSON представление обновлённой записи в базе данных, включая её ID:
```
{
    "id": 0,
    "baseCurrency": {
        "id": 0,
        "name": "United States dollar",
        "code": "USD",
        "sign": "$"
    },
    "targetCurrency": {
        "id": 1,
        "name": "Euro",
        "code": "EUR",
        "sign": "€"
    },
    "rate": 0.99
}

```


### Обмен валюты

#### GET `/exchange?from=BASE_CURRENCY_CODE&to=TARGET_CURRENCY_CODE&amount=AMOUNT`

Расчёт перевода определённого количества средств из одной валюты в другую. Пример запроса - GET `/exchange?from=USD&to=AUD&amount=10`.

Пример ответа:
```
{
    "baseCurrency": {
        "id": 0,
        "name": "United States dollar",
        "code": "USD",
        "sign": "$"
    },
    "targetCurrency": {
        "id": 1,
        "name": "Australian dollar",
        "code": "AUD",
        "sign": "A€"
    },
    "rate": 1.45,
    "amount": 10.00
    "convertedAmount": 14.50
}
```

