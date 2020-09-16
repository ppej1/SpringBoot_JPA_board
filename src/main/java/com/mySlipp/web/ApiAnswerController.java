package com.mySlipp.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mySlipp.domain.Answer;
import com.mySlipp.domain.AnswerRepository;
import com.mySlipp.domain.Question;
import com.mySlipp.domain.QuestionRepository;
import com.mySlipp.domain.User;

@RestController
@RequestMapping("api/question/{QuestId}/answers")
public class ApiAnswerController {
	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;
	
	
	@RequestMapping("")
	public Answer create(@PathVariable Long QuestId, String contents, HttpSession session){
		if(!HttpSessionUtils.isLoginUser(session)){
			return null;
		}
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		Question question = questionRepository.findById(QuestId).get();
		Answer answer = new Answer(loginUser, question,contents);
		return answerRepository.save(answer);
	}
}
