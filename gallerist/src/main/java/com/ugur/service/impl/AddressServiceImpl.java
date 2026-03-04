package com.ugur.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ugur.dto.DtoAddress;
import com.ugur.dto.DtoAddressIU;
import com.ugur.entity.Address;
import com.ugur.exception.BaseException;
import com.ugur.exception.ErrorMessage;
import com.ugur.exception.MessageType;
import com.ugur.repository.AddressRepository;
import com.ugur.service.IAddressService;

@Service
public class AddressServiceImpl implements IAddressService {
	
	@Autowired
	private AddressRepository addressRepository;

	private Address createAddress(DtoAddressIU dtoAddressIU) {
		Address address= new Address();
		address.setCreateTime(new Date());
		BeanUtils.copyProperties(dtoAddressIU, address);
		
		return address;
	}
	
	@Override
	public DtoAddress saveAddress(DtoAddressIU dtoAddressIU) {
		DtoAddress dtoAddress=new DtoAddress();
		Address savedAddress = addressRepository.save(createAddress(dtoAddressIU));
		BeanUtils.copyProperties(savedAddress, dtoAddress);
		
		return dtoAddress;
	}
	
	public Address getAddressById(Long id) {
		Optional<Address> optAddress = addressRepository.findById(id);
		if(optAddress.isPresent()) {
			return optAddress.get();
		}
		else {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, optAddress.get().getId().toString()));
		}
	}

	@Override
	public DtoAddress deleteAddress(Long id) {
		Address dbAddress = getAddressById(id);
		DtoAddress dtoAddress=new DtoAddress();
		BeanUtils.copyProperties(dbAddress, dtoAddress);
		
		if(addressRepository.existsCustomerByAddressId(dtoAddress.getId())) {
			throw new BaseException(new ErrorMessage(MessageType.ADDRESS_ALREADY_IN_USE, dtoAddress.getId().toString()));
		}
		if(addressRepository.existsGalleristByAddressId(dtoAddress.getId())) {
			throw new BaseException(new ErrorMessage(MessageType.ADDRESS_ALREADY_IN_USE, dtoAddress.getId().toString()));
		}
		
		addressRepository.delete(dbAddress);
		
		return dtoAddress;
	}

}
