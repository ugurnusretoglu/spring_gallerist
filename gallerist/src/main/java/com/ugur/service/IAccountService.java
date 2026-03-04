package com.ugur.service;

import com.ugur.dto.DtoAccount;
import com.ugur.dto.DtoAccountIU;

public interface IAccountService {
	
	public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);
	
	public DtoAccount deleteAccount(Long id);
}
