package com.ugur.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ugur.dto.AuthRequest;
import com.ugur.dto.DtoUser;
import com.ugur.entity.User;
import com.ugur.repository.UserRepository;
import com.ugur.service.IAuthenticationService;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	private User createUser(AuthRequest input) {
		User user=new User();
		user.setCreateTime(new Date());
		user.setUsername(input.getUsername());
		user.setPassword(passwordEncoder.encode(input.getPassword()));
		
		return user;
	}
	
	@Override
	public DtoUser register(AuthRequest input) {
		DtoUser dtoUser=new DtoUser();
		User savedUser= userRepository.save(createUser(input));
		BeanUtils.copyProperties(savedUser, dtoUser);
		
		return dtoUser;
	}

}
