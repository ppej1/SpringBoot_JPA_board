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
		if(!user.matchPassword(password)){
			return "redirect:/user/loginForm";
		}
		session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);
		
		return "redirect:/";
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);
		return "redirect:/";
	}
	@RequestMapping("/profile")
	public String profilePage(){
		return "/user/profile";
	}
	@RequestMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session){
		if(!HttpSessionUtils.isLoginUser(session)){
			return "redirect:/user/login";
		}
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		if(!loginUser.matchId(id)){
			return "redirect:/";
		}
		User user = userRepository.findById(id).get(); 
		model.addAttribute("user", user);
		return "/user/updateForm";
	}
	@RequestMapping("/update/{id}")
	public String UpdateUser(@PathVariable Long id, User updateUser, HttpSession session){
		if(!HttpSessionUtils.isLoginUser(session)){
			return "redirect:/user/login";
		}
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		if(!loginUser.matchId(id)){
			return "redirect:/";
		}
		
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
