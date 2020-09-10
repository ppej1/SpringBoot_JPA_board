package com.mySlipp.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mySlipp.domain.Question;
import com.mySlipp.domain.QuestionRepository;
import com.mySlipp.domain.User;

@Controller
@RequestMapping("/qna")
public class QnaController {
	@Autowired
	private QuestionRepository questionRepository;
	
	
	@RequestMapping("/form")
	public String QuestionForm(HttpSession session){
		if(!HttpSessionUtils.isLoginUser(session)){
			return "redirect:/";
		}
		return "/qna/form";
	}
	@RequestMapping("/question")
	public String registerQuestion(String title, String contents, HttpSession session){
		if(!HttpSessionUtils.isLoginUser(session)){
			return "redircet:/user/loginForm";
		}
		User sessionUser = HttpSessionUtils.getUserFromSession(session);
		Question newQuestion = new Question(sessionUser.getId(), title, contents);
		questionRepository.save(newQuestion);
		System.out.println(newQuestion);
		return "redirect:/";
	}
}
