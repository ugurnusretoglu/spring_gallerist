package com.ugur.controller;

import java.util.List;

import com.ugur.dto.DtoCustomer;
import com.ugur.dto.DtoCustomerIU;

public interface IRestCustomerController {
	
	public RootEntity<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);
	
	public void deleteCustomer(Long id);
	
	public RootEntity<List<DtoCustomer>> getAllCustomers();
	
}
