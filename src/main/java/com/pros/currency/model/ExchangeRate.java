package com.pros.currency.model;

import java.util.Date;

public class ExchangeRate {

	private String fromCurrency;
	private String toCurrency;
	private Date date;
	private double rate;
	
	public ExchangeRate() {
	}

	public ExchangeRate(String fromCurrency, String toCurrency, Date date, double rate) {
		this.fromCurrency = fromCurrency;
		this.toCurrency = toCurrency;
		this.date = date;
		this.rate = rate;
	}

	public String getFromCurrency() {
		return fromCurrency;
	}

	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	public String getToCurrency() {
		return toCurrency;
	}

	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

}
