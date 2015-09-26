package com.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
	private String Balance;
	@Column(name= "Customer_AccountNum")
	private long Account_No;
	@Column(name= "Customer_BranchID")
	private String Customer_BranchID;
	@Column(name= "Customer_UserId")
	private String Customer_UserId;
	
	public customer() {
		
	}

	
	public customer(int Cust_ID, String first_Name, String last_Name,
			String address, long account_No, String phone, String user_Name,
			String password) {
		super();
		Cust_ID = Cust_ID;
		First_Name = first_Name;
		Last_Name = last_Name;
		Address = address;
		Account_No = account_No;
		Phone = phone;
		
	}



	public int getCust_ID() {
		return Cust_ID;
	}

	public void setCust_ID(int Cust_ID) {
		Cust_ID = Cust_ID;
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


	public String getBalance() {
		return Balance;
	}


	public void setBalance(String balance) {
		Balance = balance;
	}


	public String getCustomer_BranchID() {
		return Customer_BranchID;
	}


	public void setCustomer_BranchID(String customer_BranchID) {
		Customer_BranchID = customer_BranchID;
	}


	public String getCustomer_UserId() {
		return Customer_UserId;
	}


	public void setCustomer_UserId(String customer_UserId) {
		Customer_UserId = customer_UserId;
	}
	

	
}
