package com.DAO;

/*
 * @Trade: stores company name with trade direction and decisions 
 * 
 */
public class Trade {

	private String companyName;
	private String direction;
	private double  tradingPrice;
	
	//@tradeDecision : stores actual trade decision to be displayed
	private String tradeDecision;

	/*
	 * @default constructor Trade(): initialize the variable tradeDecision for no trade
	 */
	public Trade()
	{
		tradeDecision = "Nothing returned";
	}
			
			
	/*
	 * @constructor Trade: initialize the variable tradeDecision when trade needs to be done
	 */		
	public Trade(String companyName, String direction, double tradingPrice) {
		super();
		this.companyName = companyName;
		this.direction = direction;
		this.tradingPrice = tradingPrice;
		this.tradeDecision = companyName+","+direction+","+tradingPrice+","+1000;
		
	}
	
	
	/*
	 * @getTradeDecision: getter method for variable tradeDecision
	 */		
	public String getTradeDecision() {
		return tradeDecision;
	}


	/*public void setTradeDecision(String tradeDecision) {
		this.tradeDecision = tradeDecision;
	}

	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public float getTradingPrice() {
		return tradingPrice;
	}
	public void setTradingPrice(float tradingPrice) {
		this.tradingPrice = tradingPrice;
	}*/
	
}
