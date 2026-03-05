package com.ugur.controller;

import com.ugur.dto.DtoGalleristCar;
import com.ugur.dto.DtoGalleristCarIU;

public interface IRestGalleristCarContoller {
	
	public RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
	public void deleteGalleristCar(Long id);
}
