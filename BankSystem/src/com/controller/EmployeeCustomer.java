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
 * @EmployeeCustomer: handles doPost request from other jsp page
 */
@WebServlet("/Employee_Customer")
public class EmployeeCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	//@doPost: override servlet doPost method and Handles the HTTP Post request from client.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("manager");
		
	 	Connection myConn= null;
	    Statement Mystat= null;
	    ResultSet MyR=null;
	    PreparedStatement pre=null;
	    
	    String First_Name = null;
		Integer custId = null; 
		
	
		try
		{
		
		Class.forName("org.gjt.mm.mysql.Driver");
		
		ServletContext servecontext = request.getServletContext(); 
		
		
		String UserTypeOfuser = (String)servecontext.getAttribute("username");
		myConn = (Connection)servecontext.getAttribute("connString");
		

		Mystat= myConn.createStatement();
		
		
		
		 if(!request.getParameter("customer_Id").equals(""))
		  {
			
			System.out.println(" inside id ");
			custId = Integer.parseInt(request.getParameter("customer_Id"));
			
			
		  }
		  else
		  {
			System.out.println(" inside blank id ");
			custId = 000000;
			
		}
		
		if(!request.getParameter("First_Name").equals(""))
		{
		
			System.out.println(" inside phone ");
			First_Name =   request.getParameter("First_Name");
			
		
		}
		else
		{
			System.out.println(" inside blank phone ");
			First_Name =   "000000";
			
		}
		
		
		String view = "employee_On_customer"+UserTypeOfuser;
		System.out.println("view "+ view);
		String insertquery = " select * from bank."+ view +" where CustomerID = "+custId+" OR Customer_FirstName Like '%"+First_Name+"%'" ;
		
		MyR= Mystat.executeQuery(insertquery);	
		
		List<customer> nameList = new ArrayList<customer>();
		
		while(MyR.next()) 
		{
			customer c1 = new customer();
			
			System.out.println(MyR.getInt("CustomerID"));
			c1.setCust_ID(MyR.getInt("CustomerID"));
			c1.setFirst_Name(MyR.getString("Customer_FirstName"));
			
			nameList.add(c1);
		}
		
	
				
		RequestDispatcher requestdispatch = request.getRequestDispatcher("Emp_Cus_result.jsp");
		
		 request.setAttribute("Resultlist",nameList);
		 
		 
		 requestdispatch.forward(request, response);
		 
			
		 
		}
		catch (Exception e) 
		   {
		        System.out.println(e);
		   }  
	}
		
	
	

}
