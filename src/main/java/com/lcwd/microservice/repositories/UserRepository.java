package com.lcwd.microservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lcwd.microservice.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

	//custom methods and queries
	
}
