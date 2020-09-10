package com.mySlipp.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mySlipp.domain.User;
import com.mySlipp.domain.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	private List<User> users = new ArrayList<User>();
	
	@Autowired
	private UserRepository userRepository;
	

	@RequestMapping("/register")
	public String registerPage(){
		return "/user/form";
	}
	@RequestMapping("/create")
	public String createUser(User user){
		System.out.println(user);
		userRepository.save(user);
		return "redirect:/";
	}
	@RequestMapping("/loginForm")
	public String loginPage(){
		return "/user/login";
	}
	@RequestMapping("/login")
	public String login(String userId,String password, HttpSession session){
		User user = userRepository.findByUserId(userId);
		if(user== null){
			return "redirect:/user/loginForm";
		}
		if(!password.equals(user.getPassword())){
			return "redirect:/user/loginForm";
		}
		session.setAttribute("user", user);
		
		return "redirect:/";
	}
	
	@RequestMapping("/profile")
	public String profilePage(){
		return "/user/profile";
	}
	@RequestMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model){
		User user = userRepository.findById(id).get(); 
		model.addAttribute("user", user);
		return "/user/updateForm";
	}
	@RequestMapping("/update/{id}")
	public String UpdateUser(@PathVariable Long id, User updateUser){
		User user = userRepository.findById(id).get(); 
		user.update(updateUser);
		System.out.println(user);
		userRepository.save(user);
		return "redirect:/";
	}

	@RequestMapping("/list")
	public String userList(Model model){
		model.addAttribute("users",userRepository.findAll());
		return "/user/list";
	}
}
