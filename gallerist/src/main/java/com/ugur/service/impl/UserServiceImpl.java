package com.ugur.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugur.entity.User;
import com.ugur.exception.BaseException;
import com.ugur.exception.ErrorMessage;
import com.ugur.exception.MessageType;
import com.ugur.repository.UserRepository;
import com.ugur.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void deleteUser(Long id) {
		User user=userRepository.findById(id)
				.orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.RECORD_NOT_FOUND, id.toString())));
		userRepository.delete(user);
	}

}
