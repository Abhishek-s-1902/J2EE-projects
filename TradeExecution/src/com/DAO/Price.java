package com.DAO;

/*
 * @Price: Object for storing company name and price
 */
public class Price {

	private String companyName;
	private double  tradingPrice;
	private String companyWithPrice;
	
	
	
/*
 * @constructor Price: initializes the variable with the company name and price
 */
	public Price(String companyName, double price) {
		super();
		this.companyName = companyName;
		this.tradingPrice = price;
		this.companyWithPrice = this.companyName+','+tradingPrice;
	}
	
	public String getCompanyWithPrice() {
		return companyWithPrice;
	}

	public void setCompanyWithPrice(String companyWithPrice) {
		this.companyWithPrice = companyWithPrice;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public double getPrice() {
		return tradingPrice;
	}
	public void setPrice(double tradingPrice) {
		this.tradingPrice = tradingPrice;
	}
	
	
}
