package com.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="branch")
public class branch {
	
	@Id
	@Column(name = "BranchID")
	private int BranchID;
	@Column(name = "BranchName")
	private String BranchName;
	@Column(name = "BranchAddress")
	private String BranchAddress;
	

	
	
	public branch(){
		
	}
	
	public branch(int branchID, String branchName, String branchAddress) {
		super();
		BranchID = branchID;
		BranchName = branchName;
		BranchAddress = branchAddress;
	}
	
	public int getBranchID() {
		return BranchID;
	}
	public void setBranchID(int branchID) {
		BranchID = branchID;
	}
	public String getBranchName() {
		return BranchName;
	}
	public void setBranchName(String branchName) {
		BranchName = branchName;
	}
	public String getBranchAddress() {
		return BranchAddress;
	}
	public void setBranchAddress(String branchAddress) {
		BranchAddress = branchAddress;
	}
	
	
	
	
	

}
