package com.pros.currency.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pros.currency.model.ExchangeRate;

@Service
public class CurrencyConverterService {

	private List<ExchangeRate> exchangeRates  = new ArrayList<ExchangeRate>();
	
	public void initExchanges() {
		if(exchangeRates.isEmpty()) {
			exchangeRates = new ArrayList<ExchangeRate>();
			exchangeRates.add(new ExchangeRate("USD", "EUR", new Date(), 0.82));
			exchangeRates.add(new ExchangeRate("EUR", "GBP", new Date(), 0.87));
			exchangeRates.add(new ExchangeRate("GBP", "JPY", new Date(), 150.0));			
		}
		Collections.sort(exchangeRates, Comparator.comparing(ExchangeRate::getDate).reversed());

		
	}
	
	public double getExchangeRate(String fromCurrency, String toCurrency, Date date) {
        for (ExchangeRate exchangeRate : exchangeRates) {
            if (exchangeRate.getFromCurrency().equals(fromCurrency)
                    && exchangeRate.getToCurrency().equals(toCurrency)
                   ) {
                return exchangeRate.getRate();
            }
        }
        return -1.0;
    }
	
	public void addExchangeRate(String fromCurrency, String toCurrency, Date date, double rate) {
        exchangeRates.add(new ExchangeRate(fromCurrency, toCurrency, date, rate));
    }
	
	public void addExchangeRate(ExchangeRate exchangeRate) {
        exchangeRates.add(exchangeRate);
    }
	
	private String findIntermediateCurrency(String fromCurrency, String toCurrency, Date date) {
		for (ExchangeRate rate1 : exchangeRates) {
	        if (rate1.getFromCurrency().equals(fromCurrency) && !rate1.getToCurrency().equals(toCurrency)) {
	            for (ExchangeRate rate2 : exchangeRates) {
	                if (rate2.getFromCurrency().equals(rate1.getToCurrency()) && rate2.getToCurrency().equals(toCurrency) && !rate2.getDate().after(date) ) {
	                    return rate1.getToCurrency();
	                }
	            }
	        }
	    }
	    return null;
    }
	
	public double convertCurrency(double amount, String fromCurrency, String toCurrency, Date date) {
        double exchangeRate = getExchangeRate(fromCurrency, toCurrency, date);
        if (exchangeRate != -1.0) {
            return amount * exchangeRate;
        }

        String intermediateCurrency = findIntermediateCurrency(fromCurrency, toCurrency, date);
        if (intermediateCurrency != null) {
            double rate1 = getExchangeRate(fromCurrency, intermediateCurrency, date);
            double rate2 = getExchangeRate(intermediateCurrency, toCurrency, date);
            if (rate1 != -1.0 && rate2 != -1.0) {
                return amount * rate1 * rate2;
            }
        }

        return -1.0;
    }

	public List<ExchangeRate> getExchangeRates() {
		return exchangeRates;
	}

	public void setExchangeRates(List<ExchangeRate> exchangeRates) {
		this.exchangeRates = exchangeRates;
	}
	
	
	
}
