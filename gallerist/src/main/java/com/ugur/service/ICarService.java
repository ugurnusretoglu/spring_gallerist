package com.ugur.service;

import com.ugur.dto.DtoCar;
import com.ugur.dto.DtoCarIU;

public interface ICarService {
	
	public DtoCar saveCar(DtoCarIU dtoCarIU);
	
}
