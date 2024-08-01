package com.lcwd.microservice.entities;

import java.util.List;

public class RatingResponse {

	
	List<Rating> ratings;

	public RatingResponse() {
	
	}

	public RatingResponse(List<Rating> ratings) {
		super();
		this.ratings = ratings;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
	
	
	
}
