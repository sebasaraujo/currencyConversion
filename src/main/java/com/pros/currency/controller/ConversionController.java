package com.pros.currency.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pros.currency.model.ExchangeRate;
import com.pros.currency.service.CurrencyConverterService;

@Controller
@RequestMapping("/conversion")
public class ConversionController {

	@Autowired
	private CurrencyConverterService currencyConverterService;

	@PostMapping("/exchangeRate")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<String> addExchangeRate(@RequestBody ExchangeRate exchangeRate) {
		currencyConverterService.addExchangeRate(exchangeRate);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/convertCurrency")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<String> convertCurrency(@RequestParam String fromCurrency, @RequestParam String toCurrency,
			@RequestParam double amount) {
		currencyConverterService.initExchanges();
		Date date = new Date(); 
        double convertedAmount = currencyConverterService.convertCurrency(amount, fromCurrency, toCurrency, date);
        if (convertedAmount != -1.0) {
            String response = amount + " " + fromCurrency + " is equivalent to " + convertedAmount + " " + toCurrency;
            return new ResponseEntity<String>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("The conversion could not be performed.", HttpStatus.BAD_REQUEST);
        }
	}

	@GetMapping("/allExchange")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<List<ExchangeRate>> findAllExchange() {
		currencyConverterService.initExchanges();
		return ResponseEntity.ok(currencyConverterService.getExchangeRates());
	}

}
