package com.ugur.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugur.dto.DtoAddress;
import com.ugur.dto.DtoGallerist;
import com.ugur.dto.DtoGalleristIU;
import com.ugur.entity.Address;
import com.ugur.entity.Gallerist;
import com.ugur.exception.BaseException;
import com.ugur.exception.ErrorMessage;
import com.ugur.exception.MessageType;
import com.ugur.repository.AddressRepository;
import com.ugur.repository.GalleristRepository;
import com.ugur.service.IGalleristService;

@Service
public class GalleristServiceImpl implements IGalleristService {
	
	@Autowired
	private GalleristRepository galleristRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	private Gallerist createGallerist(DtoGalleristIU dtoGalleristIU) {
		Optional<Address> optAddress = addressRepository.findById(dtoGalleristIU.getAddressId());
		if(optAddress.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristIU.getAddressId().toString()));
		}
		Gallerist gallerist=new Gallerist();
		gallerist.setCreateTime(new Date());
		BeanUtils.copyProperties(dtoGalleristIU, gallerist);
		gallerist.setAddress(optAddress.get());
		
		return gallerist;
	}
	
	
	@Override
	public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU) {
		DtoGallerist dtoGallerist=new DtoGallerist();
		DtoAddress dtoAddress=new DtoAddress();
		
		Gallerist savedGallerist=galleristRepository.save(createGallerist(dtoGalleristIU));
		BeanUtils.copyProperties(savedGallerist, dtoGallerist);
		BeanUtils.copyProperties(savedGallerist.getAddress(), dtoAddress);
		dtoGallerist.setAddres(dtoAddress);
		
		return dtoGallerist;
	}


	@Override
	public void deleteGallerist(Long id) {
		Gallerist gallerist=galleristRepository.findById(id)
				.orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.RECORD_NOT_FOUND, id.toString())));
		galleristRepository.delete(gallerist);
	}


	@Override
	public List<DtoGallerist> getAllGallerists() {
		List<DtoGallerist> dtoList=new ArrayList<>();
		List<Gallerist> galleristList = galleristRepository.findAll();
		
		for (Gallerist gallerist : galleristList) {
			DtoGallerist dto=new DtoGallerist();
			DtoAddress dtoAddress=new DtoAddress();
			BeanUtils.copyProperties(gallerist, dto);
			BeanUtils.copyProperties(gallerist.getAddress(), dtoAddress);
			dto.setAddres(dtoAddress);
			dtoList.add(dto);
		}
		return dtoList;
	}

}
