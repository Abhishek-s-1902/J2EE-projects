package com.vo;

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
	@Column(name= "hash_Password")
	private String hash_Password;
	@Column(name= "User_Type")
	private String User_Type;

	public user_name() {
		
	}

	

	public user_name(String user_Id, String password, String hash_Password,
			String user_Type) {
		super();
		User_Id = user_Id;
		Password = password;
		this.hash_Password = hash_Password;
		User_Type = user_Type;
	}



	public String getUser_Type() {
		return User_Type;
	}



	public void setUser_Type(String user_Type) {
		User_Type = user_Type;
	}



	public void setUser_Id(String user_Id) {
		User_Id = user_Id;
	}
	

	public String getUser_Id() {
		return User_Id;
	}
	
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	public String getHash_Password() {
		return hash_Password;
	}


	public void setHash_Password(String hash_Password) {
		this.hash_Password = hash_Password;
	}
	
	
}
