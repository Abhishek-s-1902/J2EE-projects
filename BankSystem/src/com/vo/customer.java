package com.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="customer")
public class customer {
	
	
	@Id
	@Column(name= "CustomerID")
	private int Cust_ID;
	@Column(name= "Customer_FirstName")
	private String First_Name;
	@Column(name= "Customer_LastName")
	private String Last_Name;
	@Column(name= "Customer_Address")
	private String Address;
	@Column(name= "Customer_Phone")
	private String Phone;
	@Column(name= "Customer_Balance")
	private double Balance;
	@Column(name= "Customer_AccountNum")
	private long Account_No;
	@Column(name= "Customer_BranchID")
	private int BranchID;
	@Column(name= "Customer_UserId")
	private String User_Name;
	
	
	
	public customer() {
		
	}



	public customer(int cust_ID, String first_Name, String last_Name,
			String address, String phone, double balance, long account_No,
			int branchID, String user_Name) {
		super();
		Cust_ID = cust_ID;
		First_Name = first_Name;
		Last_Name = last_Name;
		Address = address;
		Phone = phone;
		Balance = balance;
		Account_No = account_No;
		BranchID = branchID;
		User_Name = user_Name;
	}

	public int getCust_ID() {
		return Cust_ID;
	}

	public void setCust_ID(int cust_ID) {
		Cust_ID = cust_ID;
	}

	public double getBalance() {
		return Balance;
	}

	public void setBalance(double balance) {
		Balance = balance;
	}

	public int getBranchID() {
		return BranchID;
	}

	public void setBranchID(int branchID) {
		BranchID = branchID;
	}

	

	public String getFirst_Name() {
		return First_Name;
	}

	public void setFirst_Name(String first_Name) {
		First_Name = first_Name;
	}

	public String getLast_Name() {
		return Last_Name;
	}

	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}

	public String getAddress() {
		return Address;
	}


	public void setAddress(String address) {
		Address = address;
	}


	public long getAccount_No() {
		return Account_No;
	}


	public void setAccount_No(long account_No) {
		Account_No = account_No;
	}


	public String getPhone() {
		return Phone;
	}


	public void setPhone(String phone) {
		Phone = phone;
	}
	
	public String getUser_Name() {
		return User_Name;
	}

	public void setUser_Name(String user_Name) {
		User_Name = user_Name;
	}

	
	
}
