package com.ugur.controller;

import com.ugur.dto.DtoAddress;
import com.ugur.dto.DtoAddressIU;

public interface IRestAddressController {
	
	public RootEntity<DtoAddress> saveAddress(DtoAddressIU dtoAddressIU);
	
	public RootEntity<DtoAddress> deleteAddress(Long id);
	
}
