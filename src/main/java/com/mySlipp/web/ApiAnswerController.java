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
import com.mySlipp.domain.Result;
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
		question.addAnswer();
		
		return answerRepository.save(answer);
	}
	
	@RequestMapping("/{id}/delete")
	public Result delete(@PathVariable Long QuestId, @PathVariable Long id, HttpSession session){
		if(!HttpSessionUtils.isLoginUser(session)){
			return  Result.fail("로그인해야 합니다");
		}
		Answer answer = answerRepository.findById(id).get();
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		if(!answer.isSameWriter(loginUser)){
			return  Result.fail("자신의 글만 삭제할 수 있습니다.");
		}
		
		answerRepository.deleteById(id);
		Question question = questionRepository.findById(QuestId).get();
		question.deleteAnswer();
		questionRepository.save(question);
		return Result.ok();
	}
}
