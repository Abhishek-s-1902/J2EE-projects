package com.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.DAO.Price;
import com.DAO.Trade;
import com.Interface.TradingAlgo;


/*
 * @checkTradeDecision: service class and implements TradingAlgo 
 *                     provides algorithm to make decision on trading companies  
 */
public class checkTradeDecision implements TradingAlgo{

	/*
	 * @companyWithPriceList : HashMap to store companies list considered for trading and with there three past prices
	 * @Object O,J : for synchronization of application under multi-threading environment
	 */
	private static final Object O = new Object();
	private static final Object J = new Object();
	
	
	private static HashMap<String, ArrayList> companyWithPriceList = 
													new HashMap<String, ArrayList>();
	private String companyName ;
	private double companyPrice;
	private boolean trade = false;
	
	public checkTradeDecision()
	{
		
	}
	
	/*
	 * @constructor checkTradeDecision: initializes HashMap with key as company name and empty arrayList to 
	 * 									store three prices
	 * 
	 */
	public checkTradeDecision(String[] companiesName) {
		
		for(String S1 : companiesName)
		{
			
			ArrayList<Float> A1 = new ArrayList<Float>();
			companyWithPriceList.put(S1,A1 );
			
			
		}
	}
	
	/*
	 * @buildTradeOrNull : contains actual implementation to decide weather to make a trade or not 
	 *                   takes Price object as input argument and returns Trade object storing the result 
	 */
	public  Trade buildTradeOrNull(Price price){
		
		
		synchronized(J){


		String companyWithPrice = price.getCompanyWithPrice();
				
		
		companyName =  price.getCompanyName();
				
		companyPrice = price.getPrice();
		
		if(!companyWithPriceList.containsKey(companyName))
		{
			System.out.println("no such company exist in the company List to be trade");
			return new Trade();
		}	
		
		
		try{
		
			
		Runnable runThis = new Runnable() {
			
			
				@SuppressWarnings("unchecked")
				public void run() {
				
				try{
					
					
					if(Thread.activeCount()>0)
					Thread.sleep(1000);
					
					synchronized(O){
					
						
						ArrayList<Double> priceList ;
						
						
						priceList = (ArrayList<Double>)companyWithPriceList.get(checkTradeDecision.this.companyName);
						
						
						/*
						 * if the size of arrayList is <3 , so nothing and store the new value
						 */
						if(priceList.size()<3)
						{
							priceList.add(checkTradeDecision.this.companyPrice);
							
							trade = false;
							
														
							priceList = (ArrayList<Double>)companyWithPriceList.get(checkTradeDecision.this.companyName);
							
						}
						
						/*
						 * else calculate the simple average of the four prices and decide for the trade
						 */
						else
						{
							double sum = (priceList.get(0)+priceList.get(1)+priceList.get(2)+companyPrice)/4;
							
							if(sum > priceList.get(0))
							{
								
								trade = true;	
								
							}
							else{
							
								
								trade = false;
							}
							
							/*
							 * replace the old value with the new one and rotate the arrayList to have next oldest price
							 * in position
							 */
							
							priceList.set(0,companyPrice);
							
							Collections.rotate(priceList, 2);
							 
												
							
						}
						
						
						
					} 
					
					
				}catch(NullPointerException nullException){
					
					System.out.println(" object does not not exist: ");
					nullException.printStackTrace();
				}catch(Exception ex)
				{
					System.out.println(" something went wrong :");
					ex.printStackTrace();
				}
				
				return ;
			}
			
				
		};
		
		
		Thread T1 = new Thread(runThis);
		T1.start();
		T1.join();
		
		
				if(checkTradeDecision.this.trade==true)
				{
					
					Trade tradeDecision = new Trade(checkTradeDecision.this.companyName,"BUY",checkTradeDecision.this.companyPrice );
					return tradeDecision;
				}
				else if(checkTradeDecision.this.trade == false)
				{
					Trade tradeDecision = new Trade();
					return tradeDecision;
				}
					
		
				
		}catch(Exception ex)
		{
			
			System.out.println(ex);
		}		
			
		return null;
		
		}	

	}
	
		
}
	

