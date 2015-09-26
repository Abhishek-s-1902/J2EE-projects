package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="user_name")
public class user_name {

	
	@Id
	@Column(name= "User_Id")
	private String User_Id;
	@Column(name= "Password")
	private String Password;
	public String getUser_Id() {
		return User_Id;
	}
	
	
public user_name() {
		
	}

	public user_name(String user_Id, String password) {
		super();
		User_Id = user_Id;
		Password = password;
	}

	public void setUser_Id(String user_Id) {
		User_Id = user_Id;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	
	
	
}
