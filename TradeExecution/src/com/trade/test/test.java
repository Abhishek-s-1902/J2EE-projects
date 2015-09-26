package com.trade.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.DAO.Price;
import com.DAO.Trade;
import com.controller.checkTradeDecision;

/*
 * @Author: Abhishek Sharma
 * @test :  test application as java application runtime environment 
 */
public class test {

	public static void main(String[] args) {
		

		// @s1: providing list of company to be consider under trade
		String[] s1 = {"IBM","GOOG","YAHOO"};
		
		
		try{
			
			//@companyAndPrice.txt : provides list for companies with their prices
			File file = new File("companyAndPrice.txt");
			
			BufferedReader readbuffer = new BufferedReader(new FileReader(file));
			String line;
			
			checkTradeDecision ctd = new checkTradeDecision(s1);
			
			/*
			 * @while: parse the stream from txt file and extract company name with price
			 *         creates Price object and invoke buildTradeOrNull function to check for trading
			 */
			while ((line = readbuffer.readLine()) != null) {
			      
				
				int index = line.indexOf(",");
				String name = line.substring(0,index);
				Double price = Double.parseDouble(line.substring(index+1, line.length()));
				
				//System.out.println(name + " "+ price);
				
				Price p1 = new Price(name,price);
				
				Trade T1;
				T1 = ctd.buildTradeOrNull(p1);
				
				System.out.println(name + " "+ price+"     "+T1.getTradeDecision());
			
			 }
			
			
		}catch(IOException ioEception)
		{
			System.err.println("could not read the file:");
			ioEception.printStackTrace();
		}
		 		
	}

}
