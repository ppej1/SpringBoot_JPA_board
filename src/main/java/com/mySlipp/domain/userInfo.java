package com.mySlipp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class userInfo {
	@Id
	@JsonProperty
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long user_id;
	@JsonProperty
	@Column(nullable=false, length=20, unique=true)	
	private String email;
	
	private String pwd;
	@JsonProperty
	@Column(nullable=false, length=20)	
	private String firstName;
	@JsonProperty
	@Column(nullable=false, length=20)	
	private String lastName;
	
	
	public void update(userInfo updateUser) {
		this.pwd = updateUser.pwd;
		this.firstName = updateUser.firstName;
		this.lastName = updateUser.lastName;
		this.email = updateUser.email;
	}
	
	@Override
	public String toString() {
		return String.format("User [id=%s, email=%s, pwd=%s, firstName=%s, lastName=%s]", user_id, email, pwd,
				firstName, lastName);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean matchPassword(String newPassword){
		if(newPassword == null){
			return false;
		}
		return newPassword.equals(this.pwd);
	}
	public boolean matchId(Long newId){
		if(newId == null){
			return false;
		}
		return newId.equals(this.user_id);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
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
		userInfo other = (userInfo) obj;
		if (user_id == null) {
			if (other.user_id != null)
				return false;
		} else if (!user_id.equals(other.user_id))
			return false;
		return true;
	}	
}
