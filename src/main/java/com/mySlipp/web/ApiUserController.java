package com.mySlipp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mySlipp.domain.userInfo;
import com.mySlipp.domain.UserRepository;

@RestController
@RequestMapping("/api/user")
public class ApiUserController {
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/{id}")
	public userInfo show(@PathVariable Long id){
		return userRepository.findById(id).get();
	}
}
