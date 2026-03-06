package com.ugur.service;

import java.util.List;

import com.ugur.dto.DtoAddress;
import com.ugur.dto.DtoAddressIU;

public interface IAddressService {
	
	public DtoAddress saveAddress(DtoAddressIU dtoAddressIU);
	
	public DtoAddress deleteAddress(Long id);
	
	public List<DtoAddress> getAllDtoAddresses();
	
	public DtoAddress updateAddress(Long id, DtoAddressIU dtoAddressIU);
}
