package com.lcwd.microservice.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.lcwd.microservice.entities.Rating;

@FeignClient(name="RATING-SERVICE")
public interface RatingService {

	//get 
	
	
	
	//post
	@PostMapping("/ratings/create")
	public Rating createRating(Rating values);
	
	//put
	//Implement it afterwards
	@PutMapping("/ratings/{ratingId}")
	public Rating updateRating(String ratingId,Rating rating);
	
}
