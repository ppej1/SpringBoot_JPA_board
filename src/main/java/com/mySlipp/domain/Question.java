package com.mySlipp.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long QuestId;
	
	private Long WriterId;
	private String title;
	private String contents;
	public Long getQuestId() {
		return QuestId;
	}
	public void setQuestId(Long questId) {
		QuestId = questId;
	}
	public Long getWriterId() {
		return WriterId;
	}
	public void setWriterId(Long writerId) {
		WriterId = writerId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	@Override
	public String toString() {
		return String.format("Question [QuestId=%s, WriterId=%s, title=%s, contents=%s]", QuestId, WriterId, title,
				contents);
	}
	public Question(){}
	public Question(Long writerId, String title, String contents) {
		WriterId = writerId;
		this.title = title;
		this.contents = contents;
	}
	
	
}
