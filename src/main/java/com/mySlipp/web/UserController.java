package com.mySlipp.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mySlipp.domain.userInfo;
import com.mySlipp.domain.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	private List<userInfo> users = new ArrayList<userInfo>();
	
	@Autowired
	private UserRepository userRepository;
	

	@RequestMapping("/register")
	public String registerPage(){
		return "/user/form";
	}
	@RequestMapping("/create")
	public String createUser(userInfo user){
		try{
			System.out.println(user);
			userRepository.save(user);
			return "redirect:/";
		}catch (Exception e) {
			return "/user/form";
		}
	}
	@RequestMapping("/loginForm")
	public String loginPage(){
		return "/user/login";
	}
	@RequestMapping("/login")
	public String login(String email,String pwd, HttpSession session){
		userInfo user = userRepository.findByEmail(email);
		System.out.println(user);
		if(user== null){
			return "redirect:/user/loginForm";
		}
		if(!user.matchPassword(pwd)){
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
	@RequestMapping("/{user_id}/profile")
	public String profilePage(@PathVariable Long user_id, Model model, HttpSession session){
		userInfo user = userRepository.findById(user_id).get(); 
		model.addAttribute("user", user);
		return "/user/profile";
	}
	@RequestMapping("/{user_id}/form")
	public String updateForm(@PathVariable Long user_id, Model model, HttpSession session){
		if(!HttpSessionUtils.isLoginUser(session)){
			return "redirect:/user/login";
		}
		userInfo loginUser = HttpSessionUtils.getUserFromSession(session);
		if(!loginUser.matchId(user_id)){
			return "redirect:/";
		}
		userInfo user = userRepository.findById(user_id).get(); 
		model.addAttribute("user", user);
		return "/user/updateForm";
	}
	@RequestMapping("/update/{user_id}")
	public String UpdateUser(@PathVariable Long user_id, userInfo updateUser, HttpSession session){
		if(!HttpSessionUtils.isLoginUser(session)){
			return "redirect:/user/login";
		}
		userInfo loginUser = HttpSessionUtils.getUserFromSession(session);
		if(!loginUser.matchId(user_id)){
			return "redirect:/";
		}
		
		userInfo user = userRepository.findById(user_id).get(); 
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
