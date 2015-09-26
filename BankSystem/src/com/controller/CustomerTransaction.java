package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vo.Transaction;

/**
 * @CustomerTransaction: handels http Get request from client and process for transaction related query
 */
@WebServlet("/Cust_transaction")
public class CustomerTransaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    //@doPost: It handles https doPost request from client side 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("customer");
		
	 	Connection myConn= null;
	    Statement Mystat= null;
	    ResultSet MyR=null;
	    PreparedStatement pre=null;
	    
	    Integer trans_Id = null; 
	    String time = null;
		
	    try
		{
		
		Class.forName("org.gjt.mm.mysql.Driver");
		
		ServletContext servecontext = request.getServletContext(); 
		
		
		String UserTypeOfuser = (String)servecontext.getAttribute("username");
		myConn = (Connection)servecontext.getAttribute("connString");
		
		
		Mystat= myConn.createStatement();
		
		System.out.println("connection good");
		
		
		
		if(!request.getParameter("transaction_Id").equals(""))
		  {
			
			System.out.println(" inside id ");
			trans_Id = Integer.parseInt(request.getParameter("transaction_Id"));
			System.out.println(" transaction_Id "+ trans_Id);
			
		  }
		  else
		  {
			System.out.println(" inside blank id ");
			trans_Id = 000000;
			System.out.println(" trans_Id "+ trans_Id);
		}
		
		if(!request.getParameter("transaction_time").equals(""))
		{
						
			System.out.println(" inside phone ");
			time =   request.getParameter("transaction_time");
			System.out.println(" time "+ time);
		
		}
		else
		{
			System.out.println(" inside blank phone ");
			time =   "000000";
			System.out.println(" time "+ time);
		}
		
		
		String view = "customer_On_transaction"+UserTypeOfuser;
		String insertquery = " select * from bank."+ view +" where TransactionID = "+ trans_Id +" OR Transaction_Time LIKE '%"+time+"%'" ;
		
		MyR= Mystat.executeQuery(insertquery);	
		
		List<Transaction> nameList = new ArrayList<Transaction>();
		
		while(MyR.next()) 
		{
			Transaction t1 = new Transaction();
			
			System.out.println(MyR.getInt("TransactionID"));
			System.out.println(MyR.getString("Transaction_Time"));
			
			t1.setTransactionID(MyR.getInt("TransactionID"));
			t1.setTransaction_Time(MyR.getString("Transaction_Time"));
			t1.setAmount(MyR.getDouble("Amount"));
			
			nameList.add(t1);
			
		}
		
		
		RequestDispatcher requestdispatch = request.getRequestDispatcher("Cust_Trans_Result.jsp");
		
		 request.setAttribute("Resultlist",nameList);
		 
		 requestdispatch.forward(request, response);
		
		}
		catch (Exception e) 
		{
		     System.out.println(e);
		} 
		
		
		
	}

}
