package com.ugur.service;

import java.util.List;

import com.ugur.dto.DtoSaledCar;
import com.ugur.dto.DtoSaledCarIU;

public interface ISaledCarService {
	public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU);
	
	public void deleteSaledCar(Long id);
	
	public List<DtoSaledCar> getAllSaledCars();
}
