package com.ugur.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ugur.controller.IRestGalleristCarContoller;
import com.ugur.controller.RestBaseController;
import com.ugur.controller.RootEntity;
import com.ugur.dto.DtoGalleristCar;
import com.ugur.dto.DtoGalleristCarIU;
import com.ugur.service.IGalleristCarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/gallerist-car")
public class RestGalleristCarControllerImpl extends RestBaseController implements IRestGalleristCarContoller {
	
	@Autowired
	private IGalleristCarService galleristCarService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoGalleristCar> saveGalleristCar(@Valid @RequestBody DtoGalleristCarIU dtoGalleristCarIU) {
		return ok(galleristCarService.saveGalleristCar(dtoGalleristCarIU));
	}

	@DeleteMapping("/delete/{id}")
	@Override
	public void deleteGalleristCar(@PathVariable(name = "id") Long id) {
		galleristCarService.deleteGalleristCar(id);
	}
	
	@GetMapping("/list")
	@Override
	public RootEntity<List<DtoGalleristCar>> getAllGalleristCars() {
		return ok(galleristCarService.getAllDtoGalleristCars());
	}

}
