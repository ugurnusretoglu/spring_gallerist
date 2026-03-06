package com.ugur.controller;

import java.util.List;

import com.ugur.dto.DtoCar;
import com.ugur.dto.DtoCarIU;

public interface IRestCarController {
	
	public RootEntity<DtoCar> saveCar(DtoCarIU dtoCarIU);
	
	public void deleteCar(Long id);
	
	public RootEntity<List<DtoCar>> getAllCars();
	
}
