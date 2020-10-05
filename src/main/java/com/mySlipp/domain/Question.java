package com.mySlipp.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Question {
	@Id
	@JsonProperty
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long QuestId;
	
	@ManyToOne
	@JsonProperty
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	private userInfo writer;
	@JsonProperty
	private String title;
	@Lob
	@JsonProperty
	private String contents;
	
	private LocalDateTime createDate;
	@OneToMany(mappedBy= "question",cascade = CascadeType.ALL)
	@OrderBy("id DESC")
	private List<Answer> answers;
	
	@JsonProperty
	private Integer countOfAnswer =0;
	
	
	public Question(){}
	public Question(userInfo writer, String title, String contents) {
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.createDate = LocalDateTime.now();
	}
	public String getFormattedCreateDate(){
		if(createDate == null){
			return "";
			}
		return createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
	}
	@Override
	public String toString() {
		return String.format("Question [QuestId=%s, writer=%s, title=%s, contents=%s, createDate=%s]", QuestId, writer,
				title, contents, createDate);
	}
	
	public void update(String title, String contents){
		this.title = title;
		this.contents =contents;
	}
	public boolean isSameWriter(userInfo loginUser) {
		
		return this.writer.equals(loginUser);
	}
	public void addAnswer() {
		this.countOfAnswer+=1;
	}
	public void deleteAnswer(){
		this.countOfAnswer -=1;
	}

}
