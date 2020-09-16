package com.mySlipp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class User {
	@Id
	@JsonProperty
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	@JsonProperty
	@Column(nullable=false, length=20)
	private String userId;
	
	private String password;
	@JsonProperty
	private String name;
	@JsonProperty
	private String email;
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Long getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	@Override
	public String toString() {
		return String.format("User [userId=%s, password=%s, name=%s, email=%s]", userId, password, name, email);
	}
	
	public void update(User updateUser) {
		this.password = updateUser.password;
		this.name = updateUser.name;
		this.email = updateUser.email;
	}
	
	public boolean matchPassword(String newPassword){
		if(newPassword == null){
			return false;
		}
		return newPassword.equals(this.password);
	}
	public boolean matchId(Long newId){
		if(newId == null){
			return false;
		}
		return newId.equals(this.id);
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
