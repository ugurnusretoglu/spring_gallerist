package com.ugur.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ugur.controller.IRestCustomerController;
import com.ugur.controller.RestBaseController;
import com.ugur.controller.RootEntity;
import com.ugur.dto.DtoCustomer;
import com.ugur.dto.DtoCustomerIU;
import com.ugur.service.ICustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/customer")
public class RestCustomerControllerImpl extends RestBaseController implements IRestCustomerController {
	
	@Autowired
	private ICustomerService customerService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoCustomer> saveCustomer(@Valid @RequestBody DtoCustomerIU dtoCustomerIU) {
		return ok(customerService.saveCustomer(dtoCustomerIU));
	}
	
	@DeleteMapping("/delete/{id}")
	@Override
	public void deleteCustomer(@PathVariable(name = "id") Long id) {
		customerService.deleteCustomer(id);
	}
	
	@GetMapping("/list")
	@Override
	public RootEntity<List<DtoCustomer>> getAllCustomers() {
		return ok(customerService.getAllCustomers());
	}

	@PutMapping("/update/{id}")
	@Override
	public RootEntity<DtoCustomer> updateCustomer(@PathVariable(name = "id") Long id, 
			@RequestBody DtoCustomerIU dtoCustomerIU) {
		return ok(customerService.updateCustomer(id, dtoCustomerIU));
	}
}
