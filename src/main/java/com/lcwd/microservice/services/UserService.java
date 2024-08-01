package com.lcwd.microservice.services;

import java.util.List;

import com.lcwd.microservice.entities.User;

public interface UserService {

	
	//create user
	User saveUser(User user);
	
	//get all users
	List<User> getAllUsers();
	
	//get user by id
	User getUser(String userId);
	
	
	
}
