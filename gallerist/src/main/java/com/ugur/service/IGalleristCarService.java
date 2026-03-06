package com.ugur.service;

import java.util.List;

import com.ugur.dto.DtoGalleristCar;
import com.ugur.dto.DtoGalleristCarIU;

public interface IGalleristCarService {
	
	public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
	
	public void deleteGalleristCar(Long id);
	
	public List<DtoGalleristCar> getAllDtoGalleristCars();
}
