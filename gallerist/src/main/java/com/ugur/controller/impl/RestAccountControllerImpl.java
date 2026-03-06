package com.ugur.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ugur.controller.IRestAccountController;
import com.ugur.controller.RestBaseController;
import com.ugur.controller.RootEntity;
import com.ugur.dto.DtoAccount;
import com.ugur.dto.DtoAccountIU;
import com.ugur.service.IAccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/account")
public class RestAccountControllerImpl extends RestBaseController implements IRestAccountController {
	
	@Autowired
	private IAccountService accountService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoAccount> saveAccount(@Valid @RequestBody DtoAccountIU dtoAccountIU) {
		return ok(accountService.saveAccount(dtoAccountIU));
	}
	
	@DeleteMapping("/delete/{id}")
	@Override
	public RootEntity<DtoAccount> deleteAccount(@PathVariable(name = "id") Long id) {
		return ok(accountService.deleteAccount(id));
	}
	
	@GetMapping("/list")
	@Override
	public RootEntity<List<DtoAccount>> getAllAccounts() {
		return ok(accountService.getAllAccounts());
	}
}
