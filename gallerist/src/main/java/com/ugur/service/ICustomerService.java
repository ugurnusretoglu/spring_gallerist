package com.ugur.service;

import java.util.List;

import com.ugur.dto.DtoCustomer;
import com.ugur.dto.DtoCustomerIU;

public interface ICustomerService {
	
	public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);
	
	public void deleteCustomer(Long id);
	
	public List<DtoCustomer> getAllCustomers(); 
}
