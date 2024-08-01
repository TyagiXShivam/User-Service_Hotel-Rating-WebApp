package com.lcwd.microservice.controllers;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.microservice.entities.User;
import com.lcwd.microservice.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	private final org.slf4j.Logger logger=LoggerFactory.getLogger(UserController.class);
	
	
	//create user
	@PostMapping("/create")
	public ResponseEntity<User> createUser(@RequestBody User user){
		
		User newUser=userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
	}
	int retryCount=1;
	
	//get user by id
	@GetMapping("/{userId}")
	//@CircuitBreaker(name="ratingHotelBreaker",fallbackMethod="ratingHotelFallback")
	@Retry(name="ratingHotelService",fallbackMethod="ratingHotelFallback")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId){
		logger.info("Retry count: {}",retryCount);
		retryCount++;
		User user=userService.getUser(userId);
		return ResponseEntity.ok(user);
	}

	
	//creating fallback method for circuit breaker
	public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex){
		//logger.info("Fallback executed because service is down:",ex.getMessage());
		
		ex.printStackTrace();
		User user=new User();
		user.setName("Dummy");
		user.setEmail("Dummy@gmail.com");
		user.setAbout("This user is dummy user because one service is down");
		user.setUserId("12345");
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	
	//get all users
	@GetMapping("/getall")
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> allUsers=userService.getAllUsers();
		return ResponseEntity.ok(allUsers);
	}
	
	
	
	
}
