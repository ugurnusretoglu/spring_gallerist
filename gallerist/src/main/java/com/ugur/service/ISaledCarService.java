package com.ugur.service;

import com.ugur.dto.DtoSaledCar;
import com.ugur.dto.DtoSaledCarIU;

public interface ISaledCarService {
	public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU);
}
