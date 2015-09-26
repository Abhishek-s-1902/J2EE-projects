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

import com.vo.customer;


/**
 * @customerSelfData: it handles request from another servlet 
 * for processing customer self data 
 */
@WebServlet("/cust_selfData")
public class customerSelfData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	/*
	 * @doGet: overwrite doGet method of servlet and handles the http Get request from other servlet(non-Javadoc)
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("customer_data");
		
	 	Connection myConn= null;
	    Statement Mystat= null;
	    ResultSet MyR=null;
	    PreparedStatement pre=null;
	  
	    try
		{
		
		Class.forName("org.gjt.mm.mysql.Driver");
		
		ServletContext servecontext = request.getServletContext(); 
		
		
		String UserTypeOfuser = (String)servecontext.getAttribute("username");
		String connected_User = (String)servecontext.getAttribute("user_connected");
		
		myConn = (Connection)servecontext.getAttribute("connString");
		//myConn= DriverManager.getConnection("jdbc:mysql://localhost/bank",u,p);
	    
		System.out.println(" good till here "+ UserTypeOfuser);

		//myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank",UserTypeOfuser,"");
	    System.out.println("Connected database successfully to bank...");
		
		Mystat= myConn.createStatement();
		
		System.out.println("connection good");
	    
		String view = "View"+UserTypeOfuser;
		
		String insertquery = " select * from bank."+ view +" where Customer_UserId = '"+ connected_User +"'";
		
		MyR= Mystat.executeQuery(insertquery);	
		
		List<customer> nameList = new ArrayList<customer>();
		
		while(MyR.next()) 
		{
			customer c1 = new customer();
			
			System.out.println(MyR.getInt("CustomerID"));
			System.out.println(MyR.getString("Customer_FirstName"));
			
			c1.setCust_ID(MyR.getInt("CustomerID"));
			c1.setFirst_Name(MyR.getString("Customer_FirstName"));
			c1.setLast_Name(MyR.getString("Customer_LastName"));
			c1.setPhone(MyR.getString("Customer_Phone"));
			c1.setAddress(MyR.getString("Customer_Address"));
			c1.setBranchID(MyR.getInt("Customer_Balance"));
			
			nameList.add(c1);
			
		}
		
		//@requestdispatch: dispatch processed request to Cust_Self_Result.jsp page
		RequestDispatcher requestdispatch = request.getRequestDispatcher("Cust_Self_Result.jsp");
		
		 request.setAttribute("Resultlist",nameList);
		 
		 requestdispatch.forward(request, response);
		
		}
		catch (Exception e) 
		{
		     System.out.println(e);
		} 
		
	}

}
