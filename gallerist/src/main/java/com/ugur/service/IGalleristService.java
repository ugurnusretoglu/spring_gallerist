package com.ugur.service;

import java.util.List;

import com.ugur.dto.DtoGallerist;
import com.ugur.dto.DtoGalleristIU;

public interface IGalleristService {
	
	public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU);
	
	public void deleteGallerist(Long id);
	
	public List<DtoGallerist> getAllGallerists();
}
