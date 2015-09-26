package com.trade.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.DAO.Price;
import com.DAO.Trade;
import com.controller.checkTradeDecision;


/*
 * @secondTestTradeDecision : uses junit test functionality to test the application
 */
public class secondTestTradeDecision {

	

	@Test
	public void testBuildTradeOrNull() {
		
		/*
		 * @s1: providing list of companies concidered for trading 
		 */
		String[] s1 = {"IBM","GOOG","yahoo"};
		
		
		checkTradeDecision ctd = new checkTradeDecision(s1);
		
				
		//sequential execution of list and checking correct results with assertEquals
		
		assertEquals("Nothing returned", ctd.buildTradeOrNull(new Price("yahoo",145.41)).getTradeDecision());
		
		assertEquals("Nothing returned", ctd.buildTradeOrNull( new Price("GOOG",649.00)).getTradeDecision());
		
		assertEquals("Nothing returned", ctd.buildTradeOrNull( new Price("GOOG",650.30)).getTradeDecision());
		
		assertEquals("Nothing returned", ctd.buildTradeOrNull( new Price("yahoo",144.66)).getTradeDecision());
		
		assertEquals("Nothing returned", ctd.buildTradeOrNull( new Price("GOOG",651.66)).getTradeDecision());
		
		assertEquals("GOOG,BUY,652.66,1000", ctd.buildTradeOrNull( new Price("GOOG",652.66)).getTradeDecision());
		
		assertEquals("Nothing returned", ctd.buildTradeOrNull( new Price("yahoo",143.66)).getTradeDecision());
		
		assertEquals("Nothing returned", ctd.buildTradeOrNull( new Price("yahoo",141.66)).getTradeDecision());
		
		assertEquals("Nothing returned", ctd.buildTradeOrNull( new Price("yahoo",140.66)).getTradeDecision());
	
	}

}
