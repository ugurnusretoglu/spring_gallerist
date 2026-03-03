package com.ugur.service;

import com.ugur.dto.DtoCustomer;
import com.ugur.dto.DtoCustomerIU;

public interface ICustomerService {
	
	public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);
}
