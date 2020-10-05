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
	@Column(nullable=false, length=20, unique=true)	
	private String email;
	
	private String password;
	@JsonProperty
	@Column(nullable=false, length=20)	
	private String firstName;
	@JsonProperty
	@Column(nullable=false, length=20)	
	private String lastName;
	
	
	public void update(User updateUser) {
		this.password = updateUser.password;
		this.firstName = updateUser.firstName;
		this.lastName = updateUser.lastName;
		this.email = updateUser.email;
	}
	
	@Override
	public String toString() {
		return String.format("User [id=%s, email=%s, password=%s, firstName=%s, lastName=%s]", id, email, password,
				firstName, lastName);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
