package com.ugur.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ugur.controller.IRestUserController;
import com.ugur.service.IUserService;

@RestController
@RequestMapping("/rest/api/user")
public class RestUserControllerImpl implements IRestUserController {
	
	@Autowired
	private IUserService userService;
	
	@DeleteMapping("/delete/{id}")
	@Override
	public void deleteUser(@PathVariable("id") Long id) {
		userService.deleteUser(id);
	}

}
