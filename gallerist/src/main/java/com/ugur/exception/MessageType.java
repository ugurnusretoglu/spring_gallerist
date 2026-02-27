package com.ugur.exception;

import lombok.Getter;

@Getter
public enum MessageType {
	
	NO_RECORD_EXIST("1004", "Kayit bulunamadi"),
	TOKEN_IS_EXPIRED("1005", "Tokenin suresi bitmistir."),
	USERNAME_NOT_FOUND("1006", "username bulunamadi"),
	GENERAL_EXCEPTION("9999", "Genel bir hata olustu");	
	
	
	private String code;
	private String message;
	
	private MessageType(String code, String message) {
		this.code=code;
		this.message=message;
	}

}
