package com.ugur.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ugur.controller.IRestCarController;
import com.ugur.controller.RestBaseController;
import com.ugur.controller.RootEntity;
import com.ugur.dto.DtoCar;
import com.ugur.dto.DtoCarIU;
import com.ugur.service.ICarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/car")
public class RestCarControllerImpl extends RestBaseController implements IRestCarController {
	
	@Autowired
	private ICarService carService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoCar> saveCar(@Valid @RequestBody DtoCarIU dtoCarIU) {
		return ok(carService.saveCar(dtoCarIU));
	}
	
	@DeleteMapping("/delete/{id}")
	@Override
	public void deleteCar(@PathVariable(name = "id") Long id) {
		carService.deleteCar(id);
	}
	
	@GetMapping("/list")
	@Override
	public RootEntity<List<DtoCar>> getAllCars() {
		return ok(carService.getAllCars());
	}
	
	@PutMapping("/update/{id}")
	@Override
	public RootEntity<DtoCar> updateCar(@PathVariable(name = "id") Long id, 
			@RequestBody DtoCarIU dtoCarIU) {
		return ok(carService.updateCar(id, dtoCarIU));
	}
}
