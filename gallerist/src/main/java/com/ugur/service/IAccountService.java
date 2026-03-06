package com.ugur.service;

import java.util.List;

import com.ugur.dto.DtoAccount;
import com.ugur.dto.DtoAccountIU;

public interface IAccountService {
	
	public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);
	
	public DtoAccount deleteAccount(Long id);
	
	public List<DtoAccount> getAllAccounts();
	
	public DtoAccount updateAccount(Long id, DtoAccountIU dtoAccountIU);
}
