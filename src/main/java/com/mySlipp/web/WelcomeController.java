package com.mySlipp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {
	@RequestMapping("/helloworld")
	public String welcome(String name ,String age,Model model){
		System.out.println("name:"+ name);
		model.addAttribute("name", name);
		model.addAttribute("age",age);
		return "welcome";
	}
}
