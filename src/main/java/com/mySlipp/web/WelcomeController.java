package com.mySlipp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {
	@RequestMapping("/aboutMe")
	public String welcome(){

		return "/page/aboutMe";
	}
}
