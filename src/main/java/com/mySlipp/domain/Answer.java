package com.mySlipp.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Answer {
	@Id
	@JsonProperty
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@ManyToOne
	@JsonProperty
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
	private userInfo writer;
	
	@ManyToOne
	@JsonProperty
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_question"))
	private Question question;	
	
	@Lob
	@JsonProperty
	private String contents;
	private LocalDateTime createDate;
	public Answer(){
		
	}
	
	public String getFormattedCreateDate(){
		if(createDate == null){
			return "";
			}
		return createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
	}
	
	
	public Answer(userInfo writer, Question question ,String contents) {
		this.writer = writer;
		this.question = question;
		this.contents = contents;
		this.createDate = LocalDateTime.now();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Answer other = (Answer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return String.format("Answer [id=%s, writer=%s, contents=%s, createDate=%s]", id, writer, contents, createDate);
	}

	public boolean isSameWriter(userInfo loginUser) {	
		return loginUser.equals(this.writer);
	}

	
}
