package com.ugur.service.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ugur.dto.CurrencyRatesResponse;
import com.ugur.exception.BaseException;
import com.ugur.exception.ErrorMessage;
import com.ugur.exception.MessageType;
import com.ugur.service.ICurrencyRatesService;

@Service
public class CurrencyRatesServiceImpl implements ICurrencyRatesService {

	@Override
	public CurrencyRatesResponse getCurrencyRates(String startDate, String endDate) {
		String rootURL="https://evds3.tcmb.gov.tr/igmevdsms-dis/";
		String series="TP.DK.USD.A.YTL";
		String type= "json";	
		String endpoint= rootURL+"series="+series+"&startDate="+startDate+"&endDate="+endDate+"&type="+type;
	
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.set("key", "YOUR_KEY_API");
		
		HttpEntity<?> httpEntity=new HttpEntity<>(httpHeaders);
		
		try {
			RestTemplate restTemplate=new RestTemplate();
			ResponseEntity<CurrencyRatesResponse> response = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<CurrencyRatesResponse>() {
			});
			if(response.getStatusCode().is2xxSuccessful()) {
				return response.getBody();
			}
		} catch (Exception e) {
			throw new BaseException(new ErrorMessage(MessageType.CURRENCY_RATES_IS_OCCURED, e.getMessage()));
		}
		return null;
	}

}
