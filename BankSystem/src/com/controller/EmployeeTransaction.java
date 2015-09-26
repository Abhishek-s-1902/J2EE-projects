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
 * @EmployeeTransaction: handles doPost request from other jsp page
 */
@WebServlet("/Emp_Transaction")
public class EmployeeTransaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//@doPost: override servlet doPost method and Handles the HTTP Post request from client.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("employee_trans");
		
	 	Connection myConn= null;
	    Statement Mystat= null;
	    ResultSet MyR=null;
	    PreparedStatement pre=null;
	    String u="";
	    String p="";
	    String time = null;
		Integer trans_Id = null;
		
		try
		{
		
		Class.forName("org.gjt.mm.mysql.Driver");
		
		ServletContext servecontext = request.getServletContext(); 
		
		
		String UserTypeOfuser = (String)servecontext.getAttribute("username");
		myConn = (Connection)servecontext.getAttribute("connString");
		//myConn= DriverManager.getConnection("jdbc:mysql://localhost/bank",u,p);
	    
		System.out.println(" good till here"+ UserTypeOfuser);

		//myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank",UserTypeOfuser,"");
	    System.out.println("Connected database successfully to bank...");
		
		Mystat= myConn.createStatement();
		
		System.out.println("connection good");
		
		//transaction_Id   transaction_time
		
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
		
		String view = "employee_On_transaction"+UserTypeOfuser;
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
			
			nameList.add(t1);
			
		}
		
		
		RequestDispatcher requestdispatch = request.getRequestDispatcher("Emp_Trans_Result.jsp");
		
		 request.setAttribute("Resultlist",nameList);
		 
		 requestdispatch.forward(request, response);
		
		}
		catch (Exception e) 
		{
		     System.out.println(e);
		} 
		
	}

}
