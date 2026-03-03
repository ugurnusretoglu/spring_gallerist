package com.ugur.controller;

import com.ugur.dto.DtoCar;
import com.ugur.dto.DtoCarIU;

public interface IRestCarController {
	
	public RootEntity<DtoCar> saveCar(DtoCarIU dtoCarIU);
	
}
