package com.ugur.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugur.dto.DtoAccount;
import com.ugur.dto.DtoAccountIU;
import com.ugur.entity.Account;
import com.ugur.exception.BaseException;
import com.ugur.exception.ErrorMessage;
import com.ugur.exception.MessageType;
import com.ugur.repository.AccountRepository;
import com.ugur.service.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	private Account createAccount(DtoAccountIU dtoAccountIU) {
		Account account=new Account();
		account.setCreateTime(new Date());
		BeanUtils.copyProperties(dtoAccountIU, account);
		
		return account;
	}
	
	@Override
	public DtoAccount saveAccount(DtoAccountIU dtoAccountIU) {
		DtoAccount dtoAccount = new DtoAccount();
		Account savedAccount = accountRepository.save(createAccount(dtoAccountIU));
		BeanUtils.copyProperties(savedAccount, dtoAccount);
		
		return dtoAccount;
	}
	
	public Account getAccountById(Long id) {
		Optional<Account> optAccount = accountRepository.findById(id);
		if(optAccount.isPresent()) {
			return optAccount.get();
		}
		else {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, optAccount.get().getId().toString()));
		}
	}

	@Override
	public DtoAccount deleteAccount(Long id) {
		Account dbAccount = getAccountById(id);
		DtoAccount dtoAccount=new DtoAccount();
		BeanUtils.copyProperties(dbAccount, dtoAccount);
		dtoAccount.setCurrencyType(dbAccount.getCurrencyType());
		
		if(accountRepository.existsCustomerByAccountId(dtoAccount.getId())) {
			throw new BaseException(new ErrorMessage(MessageType.ACCOUNT_ALREADY_IN_USE, dtoAccount.getId().toString()));
		}
		
		accountRepository.delete(dbAccount);
		
		return dtoAccount;
	}

	@Override
	public List<DtoAccount> getAllAccounts() {
		List<DtoAccount> dtoList= new ArrayList<>();
		List<Account> accountList = accountRepository.findAll();
		
		for (Account account : accountList) {
			DtoAccount dto=new DtoAccount();
			BeanUtils.copyProperties(account, dto);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public DtoAccount updateAccount(Long id, DtoAccountIU dtoAccountIU) {
		DtoAccount dto=new DtoAccount();
		Optional<Account> optAccount = accountRepository.findById(id);
		if(optAccount.isPresent()) {
			Account dbAccount=optAccount.get();
			
			dbAccount.setAccountNo(dtoAccountIU.getAccountNo());
			dbAccount.setAmount(dtoAccountIU.getAmount());
			dbAccount.setIban(dtoAccountIU.getIban());
			dbAccount.setCurrencyType(dtoAccountIU.getCurrencyType());
			
			Account updatedAccount=accountRepository.save(dbAccount);
			
			BeanUtils.copyProperties(updatedAccount, dto);
			dto.setCurrencyType(updatedAccount.getCurrencyType());
			
			return dto;
		}
		else {
			throw new BaseException(new ErrorMessage(MessageType.RECORD_NOT_FOUND, id.toString()));
		}
	}
}
