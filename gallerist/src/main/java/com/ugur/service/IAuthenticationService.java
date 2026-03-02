package com.ugur.service;

import com.ugur.dto.AuthRequest;
import com.ugur.dto.AuthResponse;
import com.ugur.dto.DtoUser;

public interface IAuthenticationService {
	
	public DtoUser register(AuthRequest input);
	
	public AuthResponse authenticate(AuthRequest input);
	
}
