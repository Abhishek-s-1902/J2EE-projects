package com.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="transaction")
public class Transaction {
	
	@Id
	@Column(name= "TransactionID")
	private int TransactionID;
	@Column(name= "Transaction_Type")
	private String Transaction_Type;

	@Column(name = "Transaction_Time", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private String Transaction_Time;
	
	@Column(name= "Amount")
	private double Amount;
	
	@Column(name= "CustomerID")
	private int CustomerID;
	@Column(name= "Customer_AccountNum")
	private long Customer_AccountNum;
	@Column(name= "Branch_ID")
	private int Branch_ID;
	
	public Transaction(){
		
	}

		



	public Transaction(int transactionID, String transaction_Type,
			String transaction_Time, double amount, int customerID,
			long customer_AccountNum, int branch_ID) {
		super();
		TransactionID = transactionID;
		Transaction_Type = transaction_Type;
		Transaction_Time = transaction_Time;
		Amount = amount;
		CustomerID = customerID;
		Customer_AccountNum = customer_AccountNum;
		Branch_ID = branch_ID;
	}








	public int getBranch_ID() {
		return Branch_ID;
	}

	public void setBranch_ID(int branch_ID) {
		Branch_ID = branch_ID;
	}


	public int getTransactionID() {
		return TransactionID;
	}


	public void setTransactionID(int transactionID) {
		TransactionID = transactionID;
	}


	public String getTransaction_Type() {
		return Transaction_Type;
	}


	public void setTransaction_Type(String transaction_Type) {
		Transaction_Type = transaction_Type;
	}


	public String getTransaction_Time() {
		return Transaction_Time;
	}


	public void setTransaction_Time(String transaction_Time) {
		Transaction_Time = transaction_Time;
	}


	public double getAmount() {
		return Amount;
	}


	public void setAmount(double amount) {
		Amount = amount;
	}


	public int getCustomerID() {
		return CustomerID;
	}


	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}


	public long getCustomer_AccountNum() {
		return Customer_AccountNum;
	}


	public void setCustomer_AccountNum(long customer_AccountNum) {
		Customer_AccountNum = customer_AccountNum;
	}
		
	
	
	

}
