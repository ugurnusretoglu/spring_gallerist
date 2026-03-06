package com.ugur.controller;

import java.util.List;

import com.ugur.dto.DtoAccount;
import com.ugur.dto.DtoAccountIU;

public interface IRestAccountController {
	
	public RootEntity<DtoAccount> saveAccount(DtoAccountIU dtoAccountIU);
	
	public RootEntity<DtoAccount> deleteAccount(Long id);
	
	public RootEntity<List<DtoAccount>> getAllAccounts();
	
	public RootEntity<DtoAccount> updateAccount(Long id, DtoAccountIU dtoAccountIU);
}
