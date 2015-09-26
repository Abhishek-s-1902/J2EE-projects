package com.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="manager")
public class Manager {
	
	
	@Id
	@Column(name= "ManagerID")
	private int ManagerID;
	@Column(name= "Manager_FirstName")
	private String Manager_FirstName;
	@Column(name= "Manager_LastName")
	private String Manager_LastName;
	@Column(name= "Manager_Phone")
	private String Manager_Phone;
	@Column(name= "Manager_Address")
	private String Manager_Address;
	@Column(name= "Manager_EmpolyeeID")
	private int Manager_EmpolyeeID;
	
	public Manager(){
		
	}

	
	
	public Manager(int managerID, String manager_FirstName,
			String manager_LastName, String manager_Phone,
			String manager_Address, int manager_EmpolyeeID) {
		super();
		ManagerID = managerID;
		Manager_FirstName = manager_FirstName;
		Manager_LastName = manager_LastName;
		Manager_Phone = manager_Phone;
		Manager_Address = manager_Address;
		Manager_EmpolyeeID = manager_EmpolyeeID;
	}



	public int getManagerID() {
		return ManagerID;
	}

	public void setManagerID(int managerID) {
		ManagerID = managerID;
	}

	public String getManager_FirstName() {
		return Manager_FirstName;
	}

	public void setManager_FirstName(String manager_FirstName) {
		Manager_FirstName = manager_FirstName;
	}

	public String getManager_LastName() {
		return Manager_LastName;
	}

	public void setManager_LastName(String manager_LastName) {
		Manager_LastName = manager_LastName;
	}

	public String getManager_Phone() {
		return Manager_Phone;
	}

	public void setManager_Phone(String manager_Phone) {
		Manager_Phone = manager_Phone;
	}

	public String getManager_Address() {
		return Manager_Address;
	}

	public void setManager_Address(String manager_Address) {
		Manager_Address = manager_Address;
	}

	public int getManager_EmpolyeeID() {
		return Manager_EmpolyeeID;
	}

	public void setManager_EmpolyeeID(int manager_EmpolyeeID) {
		Manager_EmpolyeeID = manager_EmpolyeeID;
	}
		
	

}
