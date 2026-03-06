package com.ugur.controller;

import java.util.List;

import com.ugur.dto.DtoSaledCar;
import com.ugur.dto.DtoSaledCarIU;

public interface IRestSaledCarController {
	public RootEntity<DtoSaledCar> buyCar(DtoSaledCarIU dtoSaledCarIU);
	
	public void deleteSaledCar(Long id);
	
	public RootEntity<List<DtoSaledCar>> getAllSaledCars();
}
