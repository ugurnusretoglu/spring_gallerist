package com.ugur.controller;

import com.ugur.dto.DtoAccount;
import com.ugur.dto.DtoAccountIU;

public interface IRestAccountController {
	
	public RootEntity<DtoAccount> saveAccount(DtoAccountIU dtoAccountIU);
	
}
