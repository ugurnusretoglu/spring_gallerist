package com.ugur.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ugur.dto.AuthRequest;
import com.ugur.dto.AuthResponse;
import com.ugur.dto.DtoUser;
import com.ugur.entity.RefreshToken;
import com.ugur.entity.User;
import com.ugur.exception.BaseException;
import com.ugur.exception.ErrorMessage;
import com.ugur.exception.MessageType;
import com.ugur.jwt.JWTService;
import com.ugur.repository.RefreshTokenRepository;
import com.ugur.repository.UserRepository;
import com.ugur.service.IAuthenticationService;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	private User createUser(AuthRequest input) {
		User user=new User();
		user.setCreateTime(new Date());
		user.setUsername(input.getUsername());
		user.setPassword(passwordEncoder.encode(input.getPassword()));
		
		return user;
	}
	
	private RefreshToken createRefreshToken(User user) {
		RefreshToken refreshToken=new RefreshToken();
		
		refreshToken.setCreateTime(new Date());
		refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1000*60*60*4));
		refreshToken.setRefreshToken(UUID.randomUUID().toString());
		refreshToken.setUser(user);
		
		return refreshToken;
	}

	@Override
	public DtoUser register(AuthRequest input) {
		DtoUser dtoUser=new DtoUser();
		User savedUser= userRepository.save(createUser(input));
		BeanUtils.copyProperties(savedUser, dtoUser);
		
		return dtoUser;
	}

	@Override
	public AuthResponse authenticate(AuthRequest input) {
		
		try {
			UsernamePasswordAuthenticationToken authenticationToken=
					new UsernamePasswordAuthenticationToken(input.getUsername(), input.getPassword());
			authenticationProvider.authenticate(authenticationToken);
			
			Optional<User> optUser  = userRepository.findByUsername(input.getUsername());
			
			String accesssToken = jwtService.generateToken(optUser.get());
			RefreshToken savedRefreshToken=refreshTokenRepository.save(createRefreshToken(optUser.get()));
			 
			return new AuthResponse(accesssToken, savedRefreshToken.getRefreshToken());
			
		} catch (Exception e) {
			throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INVALID, e.getMessage()));
		}
		
		
	}

}
