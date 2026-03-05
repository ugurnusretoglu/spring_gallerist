package com.ugur.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugur.dto.DtoAddress;
import com.ugur.dto.DtoCar;
import com.ugur.dto.DtoGallerist;
import com.ugur.dto.DtoGalleristCar;
import com.ugur.dto.DtoGalleristCarIU;
import com.ugur.entity.Car;
import com.ugur.entity.Gallerist;
import com.ugur.entity.GalleristCar;
import com.ugur.exception.BaseException;
import com.ugur.exception.ErrorMessage;
import com.ugur.exception.MessageType;
import com.ugur.repository.CarRepository;
import com.ugur.repository.GalleristCarRepository;
import com.ugur.repository.GalleristRepository;
import com.ugur.service.IGalleristCarService;

@Service
public class GalleristCarServiceImpl implements IGalleristCarService {

	@Autowired
	private GalleristCarRepository galleristCarRepository;
	
	@Autowired
	private GalleristRepository galleristRepository;
	
	@Autowired
	private CarRepository carRepository;
	
	private GalleristCar creatGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
		Optional<Gallerist> optGallerist = galleristRepository.findById(dtoGalleristCarIU.getGalleristId());
		Optional<Car> optCar = carRepository.findById(dtoGalleristCarIU.getCarId());
		if(optGallerist.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getGalleristId().toString()));
		}
		if(optCar.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getCarId().toString()));
		}
		
		GalleristCar galleristCar=new GalleristCar();
		galleristCar.setCreateTime(new Date());
		galleristCar.setGallerist(optGallerist.get());
		galleristCar.setCar(optCar.get());
		
		return galleristCar;
	}
	
	@Override
	public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
		DtoGalleristCar dtoGalleristCar=new DtoGalleristCar();
		DtoGallerist dtoGallerist=new DtoGallerist();
		DtoCar dtoCar=new DtoCar();
		DtoAddress dtoAddress=new DtoAddress();
		
		GalleristCar savedGalleristCar = galleristCarRepository.save(creatGalleristCar(dtoGalleristCarIU));
		
		BeanUtils.copyProperties(savedGalleristCar, dtoGalleristCar);
		BeanUtils.copyProperties(savedGalleristCar.getGallerist(), dtoGallerist);
		BeanUtils.copyProperties(savedGalleristCar.getGallerist().getAddress(), dtoAddress);
		BeanUtils.copyProperties(savedGalleristCar.getCar(), dtoCar);
		
		dtoGallerist.setAddres(dtoAddress);
		
		dtoGalleristCar.setGallerist(dtoGallerist);
		dtoGalleristCar.setCar(dtoCar);
		
		return dtoGalleristCar;
	}
	
	public GalleristCar getGalleristCarById(Long id) {
		Optional<GalleristCar> optGalleristCar = galleristCarRepository.findById(id);
		if(optGalleristCar.isPresent()) {
			return optGalleristCar.get();
		}
		else {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, optGalleristCar.get().getId().toString()));
		}
	}
	
	@Override
	public void deleteGalleristCar(Long id) {
		GalleristCar galleristCar=getGalleristCarById(id);
		if(galleristCar!=null) {
			galleristCarRepository.delete(galleristCar);
		}
		else {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
		}
	}
	
	

}
