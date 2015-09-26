package com.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="employee")
public class employee {
	
	@Id
	@Column(name= "EmpolyeeID")
	private int EmpolyeeID;
	@Column(name= "EmpolyeeFirstName")
	private String EmpolyeeFirstName;
	@Column(name= "EmpolyeeLastName")
	private String EmpolyeeLastName;
	@Column(name= "EmployeePhoneNum")
	private String EmployeePhoneNum;
	@Column(name= "EmpolyeeAddress")
	private String EmpolyeeAddress;
	@Column(name= "EmployeeBranchID")
	private int EmployeeBranchID;
	@Column(name= "Empolyee_UserId")
	private String Empolyee_UserId;
	@Column(name= "Employee_Type")
	private String Employee_Type;
	
	public employee(){
		
	}
	
	
	
	
	public employee(int empolyeeID, String empolyeeFirstName,
			String empolyeeLastName, String employeePhoneNum,
			String empolyeeAddress, int employeeBranchID,
			String empolyee_UserId, String employee_Type) {
		super();
		EmpolyeeID = empolyeeID;
		EmpolyeeFirstName = empolyeeFirstName;
		EmpolyeeLastName = empolyeeLastName;
		EmployeePhoneNum = employeePhoneNum;
		EmpolyeeAddress = empolyeeAddress;
		EmployeeBranchID = employeeBranchID;
		Empolyee_UserId = empolyee_UserId;
		Employee_Type = employee_Type;
	}




	public String getEmployee_Type() {
		return Employee_Type;
	}


	public void setEmployee_Type(String employee_Type) {
		Employee_Type = employee_Type;
	}


	public int getEmpolyeeID() {
		return EmpolyeeID;
	}
	public void setEmpolyeeID(int empolyeeID) {
		EmpolyeeID = empolyeeID;
	}
	public String getEmpolyeeFirstName() {
		return EmpolyeeFirstName;
	}
	public void setEmpolyeeFirstName(String empolyeeFirstName) {
		EmpolyeeFirstName = empolyeeFirstName;
	}
	public String getEmpolyeeLastName() {
		return EmpolyeeLastName;
	}
	public void setEmpolyeeLastName(String empolyeeLastName) {
		EmpolyeeLastName = empolyeeLastName;
	}
	public String getEmployeePhoneNum() {
		return EmployeePhoneNum;
	}
	public void setEmployeePhoneNum(String employeePhoneNum) {
		EmployeePhoneNum = employeePhoneNum;
	}
	public String getEmpolyeeAddress() {
		return EmpolyeeAddress;
	}
	public void setEmpolyeeAddress(String empolyeeAddress) {
		EmpolyeeAddress = empolyeeAddress;
	}
	public int getEmployeeBranchID() {
		return EmployeeBranchID;
	}
	public void setEmployeeBranchID(int employeeBranchID) {
		EmployeeBranchID = employeeBranchID;
	}
	public String getEmpolyee_UserId() {
		return Empolyee_UserId;
	}
	public void setEmpolyee_UserId(String empolyee_UserId) {
		Empolyee_UserId = empolyee_UserId;
	}
	
	
	
	
	
}
