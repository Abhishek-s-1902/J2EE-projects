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

import com.vo.Manager;


/**
 * @ManagerSelfData: handles doPost request from other jsp page
 */
@WebServlet("/Manager_SelfData")
public class ManagerSelfData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   //@doPost: override servlet doPost method and Handles the HTTP Post request from client.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		
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
		
		String insertquery = " select * from bank."+ view +" where Manager_userID = '"+ connected_User +"'";
		
		MyR= Mystat.executeQuery(insertquery);	
		
		List<Manager> nameList = new ArrayList<Manager>();
		
		while(MyR.next()) 
		{
			Manager c1 = new Manager();
			
			System.out.println(MyR.getInt("ManagerID"));
			System.out.println(MyR.getString("Manager_FirstName"));
			
			c1.setManagerID(MyR.getInt("ManagerID"));
			c1.setManager_FirstName(MyR.getString("Manager_FirstName"));
			c1.setManager_LastName(MyR.getString("Manager_LastName"));
			c1.setManager_Phone(MyR.getString("Manager_Phone"));
			c1.setManager_Address(MyR.getString("Manager_Address"));
			c1.setManager_EmpolyeeID(MyR.getInt("Manager_EmpolyeeID"));
			
			nameList.add(c1);
			
		}
		
		RequestDispatcher requestdispatch = request.getRequestDispatcher("Mag_Self_Result.jsp");
		
		 request.setAttribute("Resultlist",nameList);
		 
		 requestdispatch.forward(request, response);
		
		}
		catch (Exception e) 
		{
		     System.out.println(e);
		} 
		
	}



}
