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

import com.ugur.controller.IRestGalleristController;
import com.ugur.controller.RestBaseController;
import com.ugur.controller.RootEntity;
import com.ugur.dto.DtoGallerist;
import com.ugur.dto.DtoGalleristIU;
import com.ugur.service.IGalleristService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/gallerist")
public class RestGalleristControllerImpl extends RestBaseController implements IRestGalleristController {
	
	@Autowired
	private IGalleristService galleristService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoGallerist> saveGallerist(@Valid @RequestBody DtoGalleristIU dtoGalleristIU) {
		return ok(galleristService.saveGallerist(dtoGalleristIU));
	}
	
	@DeleteMapping("/delete/{id}")
	@Override
	public void deleteGallerist(@PathVariable("id") Long id) {
		galleristService.deleteGallerist(id);
	}
	
	@GetMapping("/list")
	@Override
	public RootEntity<List<DtoGallerist>> getAllGallerists() {
		return ok(galleristService.getAllGallerists());
	}
	
	@PutMapping("/update/{id}")
	@Override
	public RootEntity<DtoGallerist> updateGallerist(@PathVariable(name = "id") Long id, 
			@RequestBody DtoGalleristIU dtoGalleristIU) {
		return ok(galleristService.updateGallerist(id, dtoGalleristIU));
	}

}
