package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;















import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;








import com.util.hibernateUtil;
import com.vo.customer;
/**
 * @Manager_register: handles doPost request from other jsp page
 */
@WebServlet(name = "ManagerController", urlPatterns = { "/MangerSrevlet" })
public class Manager_register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  //@doPost: override servlet doPost method and Handles the HTTP Post request from client.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("manager");
		
		 Connection myConn= null;
		    Statement Mystat= null;
		    ResultSet MyR=null;
		    PreparedStatement pre=null;
		    String u="";
		    String p="";
		    String phone = null;
			Integer phoneno = null;
			Integer custId = null;
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
		
		if(!request.getParameter("phone_no").equals(""))
		{
		
		System.out.println(" inside phone ");
			phone =   request.getParameter("phone_no");
			
		
		}
		else
		{
			System.out.println(" inside blank phone ");
			phone =   "000000";
			
		}
		
		String view = "manager_On_customer"+UserTypeOfuser;
		String insertquery = " select * from bank."+ view +" where CustomerID = "+custId+" OR Customer_Phone = "+phone ;
		
		MyR= Mystat.executeQuery(insertquery);	
		
		List<customer> nameList = new ArrayList<customer>();
		
		while(MyR.next()) 
		{
			customer c1 = new customer();
			
			System.out.println(MyR.getInt("CustomerID"));
			c1.setCust_ID(MyR.getInt("CustomerID"));
			c1.setPhone(MyR.getString("Customer_Phone"));
			
			nameList.add(c1);
		}
		
	
				
		RequestDispatcher requestdispatch = request.getRequestDispatcher("Mag_Cus_result.jsp");
		// request.setAttribute("Customer_Id",custId);
		 request.setAttribute("Resultlist",nameList);
		 
		 
		 requestdispatch.forward(request, response);
		 
			/*for(customer cust : MyR)
			{
				System.out.println("User_Id "+ cust.getCust_ID());
				System.out.println("Password "+ cust.getFirst_Name());
			}*/
		 
		}
		catch (Exception e) 
		   {
		        System.out.println(e);
		   }  
	}

	
}
