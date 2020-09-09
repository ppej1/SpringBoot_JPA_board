package com.mySlipp.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mySlipp.DTO.User;

@Controller
public class UserController {
	private List<User> users = new ArrayList<User>();
	
	@RequestMapping("/")
	public String home(){
		return "index";
	}
	@RequestMapping("/register")
	public String registerform(){
		return "registerForm";
	}
	@RequestMapping("/create")
	public String createUser(User user){
		System.out.println(user);
		users.add(user);
		return "redirect:/";
	}
	@RequestMapping("/userList")
	public String userList(Model model){
		model.addAttribute("users",users);
		return "userList";
	}
}
