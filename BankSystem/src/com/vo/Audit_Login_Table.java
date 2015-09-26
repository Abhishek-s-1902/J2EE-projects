package com.vo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name="audit_login_table")
public class Audit_Login_Table {

	@Id
	@Column(name = "User_loggedin")
	private String User_loggedin;
	
	@Column(name = "Current_user_loggedin")
	private String Current_user_loggedin;
	
/*	@Column(name = "logged_in_time", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private  Date logged_in_time;*/

	//constructor
	
	
	//getter setter
	public String getUser_loggedin() {
		return User_loggedin;
	}

	public void setUser_loggedin(String user_loggedin) {
		User_loggedin = user_loggedin;
	}

	public String getCurrent_user_loggedin() {
		return Current_user_loggedin;
	}

	public void setCurrent_user_loggedin(String current_user_loggedin) {
		Current_user_loggedin = current_user_loggedin;
	}

/*	public Date getLogged_in_time() {
		return logged_in_time;
	}

	public void setLogged_in_time(Date logged_in_time) {
		this.logged_in_time = logged_in_time;
	}*/
	
	
	
	
	
	
}
