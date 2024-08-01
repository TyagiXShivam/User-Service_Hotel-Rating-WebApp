package com.lcwd.microservice.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwd.microservice.entities.Hotel;
import com.lcwd.microservice.entities.Rating;
import com.lcwd.microservice.entities.User;
import com.lcwd.microservice.exceptions.ResourceNotFoundException;
import com.lcwd.microservice.external.services.HotelService;
import com.lcwd.microservice.repositories.UserRepository;
import com.lcwd.microservice.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	private Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private HotelService hotelService;
	
	@Override
	public User saveUser(User user) {
		//generate unique user id
		String randomUserId=UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		User user=userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User Not Found With id="+userId));
		//fetch ratings of above user from rating service
		Rating[] ratingsOfUser=restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
		 logger.info("{}", ratingsOfUser);
		 List<Rating> ratings=new ArrayList<>();
		if (ratingsOfUser != null) {
             ratings = Arrays.asList(ratingsOfUser);
            //user.setRatings(ratings);
        }
		//api call to get the hotel of which rating is fetched
		for(Rating rating:ratings) {
			//ResponseEntity<Hotel> forEntity=restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
			//Hotel hotel=forEntity.getBody();
			
			//using feign client
			Hotel hotel=hotelService.getHotel(rating.getHotelId());
			rating.setHotel(hotel);
		}
		user.setRatings(ratings);
		
		return user;
	}

}
