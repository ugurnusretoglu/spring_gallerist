package com.ugur.controller;

import com.ugur.dto.DtoGallerist;
import com.ugur.dto.DtoGalleristIU;

public interface IRestGalleristController {
	
	public RootEntity<DtoGallerist> saveGallerist(DtoGalleristIU dtoGalleristIU);
	
}
