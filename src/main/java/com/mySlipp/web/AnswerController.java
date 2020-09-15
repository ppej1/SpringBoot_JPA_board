package com.mySlipp.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mySlipp.domain.Answer;
import com.mySlipp.domain.AnswerRepository;
import com.mySlipp.domain.Question;
import com.mySlipp.domain.QuestionRepository;
import com.mySlipp.domain.User;

@Controller
@RequestMapping("/question/{QuestId}/answers")
public class AnswerController {
	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;
	
	
	@RequestMapping("")
	public String create(@PathVariable Long QuestId, String contents, HttpSession session){
		if(!HttpSessionUtils.isLoginUser(session)){
			return "redircet:/user/loginForm";
		}
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		Question question = questionRepository.findById(QuestId).get();
		Answer answer = new Answer(loginUser, question,contents);
		answerRepository.save(answer);
		return String.format("redirect:/question/%d", QuestId);
	}
}
