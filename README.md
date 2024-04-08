# Currency Converter REST API

This is a simple REST API for currency conversion built with Java and Spring Boot. It allows users to add new exchange rates, retrieve all exchange rates, and perform currency conversion.

## Endpoints

### 1. Add New Exchange Rate

#### URL

POST /conversion/exchangeRate

#### Description
Adds a new exchange rate to the system.

#### Request Body

json
{
  "fromCurrency": "USD",
  "toCurrency": "EUR",
  "date": "2024-04-07",
  "rate": 0.82
}

### 2. Get All Exchange Rates
#### URL

GET /conversion/allExchange

#### Description
Retrieves all exchange rates currently stored in the system.

#### Response Body

[
  {
    "fromCurrency": "USD",
    "toCurrency": "EUR",
    "date": "2024-04-07",
    "rate": 0.82
  },
  {
    "fromCurrency": "EUR",
    "toCurrency": "GBP",
    "date": "2024-04-07",
    "rate": 0.87
  },
  {
    "fromCurrency": "GBP",
    "toCurrency": "JPY",
    "date": "2024-04-07",
    "rate": 150.0
  }
]

### 3. Perform Currency Conversion
#### URL

GET /conversion/convertCurrency?fromCurrency=USD&toCurrency=EUR&amount=100

#### Description
Converts an amount from one currency to another.

#### Parameters
fromCurrency: The currency to convert from.
toCurrency: The currency to convert to.
amount: The amount of currency to convert.

### Usage
To use this API, make HTTP requests to the specified endpoints using the provided parameters and request bodies. You can use this application by lifting the embedded tomcat from spring boot and entering the following url since it has Swagger integrated: http://localhost:8080/currency-conversion/swagger-ui.html.
