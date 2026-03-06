package com.ugur.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugur.dto.DtoCar;
import com.ugur.dto.DtoCarIU;
import com.ugur.entity.Car;
import com.ugur.exception.BaseException;
import com.ugur.exception.ErrorMessage;
import com.ugur.exception.MessageType;
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

	//public Car getCarById(Long id) {
		//Optional<Car> optCar = carRepository.findById(id);
		//if(optCar.isPresent()) {
			//return optCar.get();
		//}
		//else {
			//throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
		//}
	//}
	
	@Override
	public void deleteCar(Long id) {
		Car car=carRepository.findById(id)
				.orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.RECORD_NOT_FOUND, id.toString())));
		
		carRepository.delete(car);
	}

	@Override
	public List<DtoCar> getAllCars() {
		List<DtoCar> dtoList=new ArrayList<>();
		List<Car> carList = carRepository.findAll();
		
		for (Car car : carList) {
			DtoCar dto=new DtoCar();
			BeanUtils.copyProperties(car, dto);
			dtoList.add(dto);
		}
		return dtoList;
	}
}
