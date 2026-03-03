package com.ugur.controller;

import com.ugur.dto.CurrencyRatesResponse;

public interface IRestCurrencyRatesController {
	
	public RootEntity<CurrencyRatesResponse> getCurrencyRates(String startDate, String endDate);
	
}
