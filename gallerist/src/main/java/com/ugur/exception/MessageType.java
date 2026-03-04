package com.ugur.exception;

import lombok.Getter;

@Getter
public enum MessageType {
	
	NO_RECORD_EXIST("1004", "Kayit bulunamadi"),
	TOKEN_IS_EXPIRED("1005", "Tokenin suresi bitmistir."),
	USERNAME_NOT_FOUND("1006", "username bulunamadi"),
	USERNAME_OR_PASSWORD_INVALID("1007", "Kullanici adi veya sifre hatali"),
	REFRESH_TOKEN_NOT_FOUND("1008", "Refresh token bulunamadi"),
	REFRESH_TOKEN_IS_EXPIRED("1009", "Refresh tokenin suresi bitmistir."),
	CURRENCY_RATES_IS_OCCURED("1010", "Doviz kuru alinamadi"),
	CUSTOMER_AMOUNT_IS_NOT_ENOUGH("1011", "Musterinin parasi yeterli degil."),
	CAR_STATUS_IS_ALREADY_SALED("1012", "Araba satilmis gorundugu icin satilamaz"),
	GENERAL_EXCEPTION("9999", "Genel bir hata olustu");	
	
	
	private String code;
	private String message;
	
	private MessageType(String code, String message) {
		this.code=code;
		this.message=message;
	}

}
