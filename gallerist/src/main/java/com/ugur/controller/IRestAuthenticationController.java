package com.ugur.controller;

import com.ugur.dto.AuthRequest;
import com.ugur.dto.AuthResponse;
import com.ugur.dto.DtoUser;
import com.ugur.dto.RefreshTokenRequest;
import com.ugur.entity.RefreshToken;

public interface IRestAuthenticationController {
	
	public RootEntity<DtoUser> register(AuthRequest input);
	
	public RootEntity<AuthResponse> authenticate(AuthRequest input);
	
	public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest input);
}
