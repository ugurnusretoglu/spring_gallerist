package com.ugur.service;

import com.ugur.dto.CurrencyRatesResponse;

public interface ICurrencyRatesService {
	
	public CurrencyRatesResponse getCurrencyRates(String startDate, String endDate);
	
}
