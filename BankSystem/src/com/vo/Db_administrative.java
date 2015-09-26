package com.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="db_administrative")
public class Db_administrative {

	@Id
	@Column(name= "DB_administrative_ID")
	private int DB_administrative_ID;
	@Column(name= "DB_administrative_Type")
	private String DB_administrative_Type;
	@Column(name= "DB_administrative_EmpolyeeID")
	private int DB_administrative_EmpolyeeID;
	
	
	public Db_administrative(){
		
		
	}


	public Db_administrative(int dB_administrative_ID,
			String dB_administrative_Type, int dB_administrative_EmpolyeeID) {
		super();
		DB_administrative_ID = dB_administrative_ID;
		DB_administrative_Type = dB_administrative_Type;
		DB_administrative_EmpolyeeID = dB_administrative_EmpolyeeID;
	}

	
	public int getDB_administrative_ID() {
		return DB_administrative_ID;
	}


	public void setDB_administrative_ID(int dB_administrative_ID) {
		DB_administrative_ID = dB_administrative_ID;
	}


	public String getDB_administrative_Type() {
		return DB_administrative_Type;
	}


	public void setDB_administrative_Type(String dB_administrative_Type) {
		DB_administrative_Type = dB_administrative_Type;
	}


	public int getDB_administrative_EmpolyeeID() {
		return DB_administrative_EmpolyeeID;
	}


	public void setDB_administrative_EmpolyeeID(int dB_administrative_EmpolyeeID) {
		DB_administrative_EmpolyeeID = dB_administrative_EmpolyeeID;
	}
	
	
	
	
	

}
