package com.ugur.controller;

import java.util.List;

import com.ugur.dto.DtoAddress;
import com.ugur.dto.DtoAddressIU;

public interface IRestAddressController {
	
	public RootEntity<DtoAddress> saveAddress(DtoAddressIU dtoAddressIU);
	
	public RootEntity<DtoAddress> deleteAddress(Long id);
	
	public RootEntity<List<DtoAddress>> getAllAddresses();
	
	public RootEntity<DtoAddress> updateAddress(Long id, DtoAddressIU dtoAddressIU);
}
