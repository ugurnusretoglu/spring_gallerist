package com.ugur.service;

import com.ugur.dto.DtoAddress;
import com.ugur.dto.DtoAddressIU;

public interface IAddressService {
	
	public DtoAddress saveAddress(DtoAddressIU dtoAddressIU);
	
	public DtoAddress deleteAddress(Long id);
}
