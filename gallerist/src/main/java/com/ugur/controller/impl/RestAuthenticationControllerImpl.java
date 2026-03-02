package com.ugur.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ugur.controller.IRestAuthenticationController;
import com.ugur.controller.RestBaseController;
import com.ugur.controller.RootEntity;
import com.ugur.dto.AuthRequest;
import com.ugur.dto.AuthResponse;
import com.ugur.dto.DtoUser;
import com.ugur.service.IAuthenticationService;

import jakarta.validation.Valid;

@RestController
public class RestAuthenticationControllerImpl extends RestBaseController implements IRestAuthenticationController {

	@Autowired
	private IAuthenticationService authenticationService;
	
	@PostMapping("/register")
	@Override
	public RootEntity<DtoUser> register(@Valid @RequestBody AuthRequest input) {
		return ok(authenticationService.register(input));
	}
	
	@PostMapping("/authenticate")
	@Override
	public RootEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest input) {
		return ok(authenticationService.authenticate(input));
	}

}
