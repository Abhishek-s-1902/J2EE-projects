package com.Interface;
import com.DAO.*;

/*
 * @TradingAlgo : Interface provides method signature for service method 
 * 		
 */
public interface TradingAlgo {

	
	Trade buildTradeOrNull(Price price);
		
}
