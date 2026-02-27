package com.ugur.exception;

import lombok.Getter;

@Getter
public enum MessageType {
	
	NO_RECORD_EXIST("1004", "Kayit bulunamadi"),
	GENERAL_EXCEPTION("9999", "Genel bir hata olustu"),
	RESOURCE_NOT_FOUND("1005", "Kaynak bulunamadi");
	
	private String code;
	private String message;
	
	private MessageType(String code, String message) {
		this.code=code;
		this.message=message;
	}

}
