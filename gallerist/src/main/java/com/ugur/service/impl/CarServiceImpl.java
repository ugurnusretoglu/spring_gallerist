package com.ugur.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugur.dto.DtoCar;
import com.ugur.dto.DtoCarIU;
import com.ugur.entity.Car;
import com.ugur.repository.CarRepository;
import com.ugur.service.ICarService;

@Service
public class CarServiceImpl implements ICarService {
	
	@Autowired
	private CarRepository carRepository;
	
	private Car createCar(DtoCarIU dtoCarIU) {
		Car car = new Car();
		car.setCreateTime(new Date());
		BeanUtils.copyProperties(dtoCarIU, car);
		
		return car;
	}
	
	@Override
	public DtoCar saveCar(DtoCarIU dtoCarIU) {
		DtoCar dtoCar=new DtoCar();
		Car savedCar = carRepository.save(createCar(dtoCarIU));
		BeanUtils.copyProperties(savedCar, dtoCar);
		
		return dtoCar;
	}

}
