package com.ugur.controller;

import java.util.List;

import com.ugur.dto.DtoGalleristCar;
import com.ugur.dto.DtoGalleristCarIU;

public interface IRestGalleristCarContoller {
	
	public RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
	
	public void deleteGalleristCar(Long id);
	
	public RootEntity<List<DtoGalleristCar>> getAllGalleristCars(); 
}
