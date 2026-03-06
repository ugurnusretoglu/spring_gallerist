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

import com.ugur.controller.IRestAddressController;
import com.ugur.controller.RestBaseController;
import com.ugur.controller.RootEntity;
import com.ugur.dto.DtoAddress;
import com.ugur.dto.DtoAddressIU;
import com.ugur.service.IAddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/address")
public class RestAddressControllerImpl extends RestBaseController implements IRestAddressController {
	
	@Autowired
	private IAddressService addressService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoAddress> saveAddress(@Valid @RequestBody DtoAddressIU dtoAddressIU) {
		return ok(addressService.saveAddress(dtoAddressIU));
	}

	@DeleteMapping("/delete/{id}")
	@Override
	public RootEntity<DtoAddress> deleteAddress(@PathVariable("id") Long id) {
		return ok(addressService.deleteAddress(id));
	}
	
	@GetMapping("/list")
	@Override
	public RootEntity<List<DtoAddress>> getAllAddresses() {
		return ok(addressService.getAllDtoAddresses());
	}

}
