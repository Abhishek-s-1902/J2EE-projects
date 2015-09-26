package com.trade.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.DAO.Price;
import com.DAO.Trade;
import com.controller.checkTradeDecision;


/*
 * @checkTradeDecisionTest : uses junit test functionality to test the application
 * 							
 */
@RunWith(Parameterized.class)
public class checkTradeDecisionTest {

	// parameters for automated execution 
	private Price parameter1;
	private String experctedValue;
	
	
	/*
	 * @checkTradeDecisionTest: initializing test parameters.
	 */
	public checkTradeDecisionTest(String experctedValue, Price parameter1 ) 
	{
		super();
		this.experctedValue = experctedValue;
		this.parameter1 = parameter1;
		
	}

	/*
	 * @getValue: provides set of test case values in an Array list
	 */
	@Parameters
	public static Collection<Object[]> getValue(){
		
		String s1 = "Nothing returned";
	
		Object[][] values = {{s1,new Price("IBM",143.41)},
							{s1, new Price("GOOG",651.00)},
							{s1, new Price("GOOG",651.30)},
							{s1, new Price("IBM",144.66)},
							{s1, new Price("IBM",145.66)},
							{"IBM,BUY,146.66,1000", new Price("IBM",146.66)}};
		
		return Arrays.asList(values);
	}
	
	
	
	@Test
	public void testBuildTradeOrNull() {
		
		
		/*
		 * @s1: providing list of companies considered for trading 
		 */
		String[] s1 = {"IBM","GOOG"};
		
		checkTradeDecision ctd = new checkTradeDecision(s1);
		
		
		//assertEquals(experctedValue, ctd.buildTradeOrNull(parameter1).getTradeDecision());
		
		
		/*
		 * sequential execution of list and checking correct results with assertEquals
		 */
		
		assertEquals("Nothing returned", ctd.buildTradeOrNull(new Price("IBM",143.41)).getTradeDecision());
		
		assertEquals("Nothing returned", ctd.buildTradeOrNull( new Price("GOOG",651.00)).getTradeDecision());
		
		assertEquals("Nothing returned", ctd.buildTradeOrNull( new Price("GOOG",651.30)).getTradeDecision());
		
		assertEquals("Nothing returned", ctd.buildTradeOrNull( new Price("IBM",144.66)).getTradeDecision());
		
		assertEquals("Nothing returned", ctd.buildTradeOrNull( new Price("IBM",145.66)).getTradeDecision());
		
		assertEquals("IBM,BUY,146.66,1000", ctd.buildTradeOrNull( new Price("IBM",146.66)).getTradeDecision());
		
	}

}
