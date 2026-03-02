package com.ugur.service;

import com.ugur.dto.AuthRequest;
import com.ugur.dto.AuthResponse;
import com.ugur.dto.DtoUser;
import com.ugur.dto.RefreshTokenRequest;
import com.ugur.repository.RefreshTokenRepository;

public interface IAuthenticationService {
	
	public DtoUser register(AuthRequest input);
	
	public AuthResponse authenticate(AuthRequest input);
	
	public AuthResponse refreshToken(RefreshTokenRequest input);
	
}
