package com.mySlipp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mySlipp.domain.QuestionRepository;
@Controller
public class HomeController {
	@Autowired
	private QuestionRepository questionRepository;
	
	
	@RequestMapping("/")
	public String home(Model model){
		model.addAttribute("questions",questionRepository.findAll());
		return "index";
	}
}
