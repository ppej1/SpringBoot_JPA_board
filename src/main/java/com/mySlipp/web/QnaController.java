package com.mySlipp.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mySlipp.domain.Pages;
import com.mySlipp.domain.Question;
import com.mySlipp.domain.QuestionRepository;
import com.mySlipp.domain.User;


@Controller
@RequestMapping("/question")
public class QnaController {
	@Autowired
	private QuestionRepository questionRepository;
	
	@RequestMapping("/qnaList/{currentPage}")
	public String QnAHome(@PathVariable int currentPage, Model model){
		List<Question> questionAllList = questionRepository.findAll();
		int onePageList = 5;
		Pages pages = new Pages(1, questionAllList.size(),  onePageList,currentPage);
		
		List<Question> questionList =  pages.getOnePageList(questionAllList);

		List<Integer> pagesList = pages.getPagesList();
		model.addAttribute("pages",pagesList);
		model.addAttribute("questions",questionList);
		return "/qna/qnaList";
	}
	
	
	@RequestMapping("/form")
	public String QuestionForm(HttpSession session){
		if(!HttpSessionUtils.isLoginUser(session)){
			return "redirect:/user/login";
		}
		return "/qna/form";
	}
	@RequestMapping("/question")
	public String registerQuestion(String title, String contents, HttpSession session){
		if(!HttpSessionUtils.isLoginUser(session)){
			return "redircet:/user/loginForm";
		}
		User sessionUser = HttpSessionUtils.getUserFromSession(session);
		Question newQuestion = new Question(sessionUser, title, contents);
		questionRepository.save(newQuestion);
		System.out.println(newQuestion);
		return "redirect:/question/qnaList/1";
	}
	
	@RequestMapping("/{QuestId}")
	public String show(@PathVariable Long QuestId, Model model){
		System.out.println(questionRepository.findById(QuestId).get());
		model.addAttribute("question", questionRepository.findById(QuestId).get());

		return "/qna/show";
	}
	@RequestMapping("/{QuestId}/form")
	public String updateForm(@PathVariable Long QuestId, Model model, HttpSession session){
		try{
			Question question = questionRepository.findById(QuestId).get();
			hasPermission(session, question);
			model.addAttribute("question",question);
			return "/qna/updateForm";

		}catch(IllegalStateException e){
			return "redirect:/user/login";
		}
	} 
	
	@RequestMapping("/update/{QuestId}")
	public String updat(@PathVariable Long QuestId, String title, String contents, HttpSession session){
		try{
			Question question = questionRepository.findById(QuestId).get();
			hasPermission(session, question);
			question.update(title,contents);
			questionRepository.save(question);
			return String.format("redirect:/question/%d",QuestId);

		}catch(IllegalStateException e){
			return "redirect:/user/login";
		}
	}
	@RequestMapping("/delete/{QuestId}")
	public String delete(@PathVariable Long QuestId , HttpSession session){
		try{
			Question question = questionRepository.findById(QuestId).get();
			hasPermission(session, question);
			questionRepository.deleteById(QuestId);
			return "redirect:/question/qnaList/1";

		}catch(IllegalStateException e){
			return "redirect:/user/login";
		}

	}
	
	private boolean hasPermission(HttpSession session, Question question){
		if(!HttpSessionUtils.isLoginUser(session)){
			throw new IllegalStateException("로그인이 필요합니다");
		}
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		if(!question.isSameWriter(loginUser)){
			throw new IllegalStateException("자신이 쓴 글만 수정, 삭제가 가능합니다.");
		}
		return true;
	}
}
