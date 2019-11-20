package com.faith.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.faith.dao.ILoginDao;

@RestController
public class LoginController {
	
	@Autowired
	ILoginDao loginDao;
	
	//verify username and password
	@RequestMapping(value = "/api/login/{username}/{password}", method = RequestMethod.GET)
	public boolean verifyUser(@PathVariable("username")String username,
			@PathVariable("password")String password)
	{
		Boolean b=loginDao.verifyUser(username, password);
		return b;
	}
}
