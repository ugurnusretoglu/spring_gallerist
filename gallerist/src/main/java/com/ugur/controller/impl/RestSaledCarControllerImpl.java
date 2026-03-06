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

import com.ugur.controller.IRestSaledCarController;
import com.ugur.controller.RestBaseController;
import com.ugur.controller.RootEntity;
import com.ugur.dto.DtoSaledCar;
import com.ugur.dto.DtoSaledCarIU;
import com.ugur.service.ISaledCarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/saled-car")
public class RestSaledCarControllerImpl extends RestBaseController implements IRestSaledCarController {
	
	@Autowired
	private ISaledCarService saledCarService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoSaledCar> buyCar(@Valid @RequestBody DtoSaledCarIU dtoSaledCarIU) {
		return ok(saledCarService.buyCar(dtoSaledCarIU));
	}

	@DeleteMapping("/delete/{id}")
	@Override
	public void deleteSaledCar(@PathVariable(name = "id") Long id) {
		saledCarService.deleteSaledCar(id);
	}
	
	@GetMapping("/list")
	@Override
	public RootEntity<List<DtoSaledCar>> getAllSaledCars() {
		return ok(saledCarService.getAllSaledCars());
	}
	
}
