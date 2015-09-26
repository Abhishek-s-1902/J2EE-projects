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

import com.vo.employee;

/**
 * @EmployeeSelfData: handles doPost request from other jsp page
 */
@WebServlet("/Employee_Self")
public class EmployeeSelfData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	//@doGet:  override servlet doPost method and Handles the HTTP Post request from client.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("employee_data");
		
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
		

		Mystat= myConn.createStatement();
		
			    
		String view = "employee_On_self"+UserTypeOfuser;
		
		String insertquery = " select * from bank."+ view +" where Empolyee_UserId = '"+ connected_User +"'";
		
		MyR= Mystat.executeQuery(insertquery);	
		
		List<employee> nameList = new ArrayList<employee>();
		
		while(MyR.next()) 
		{
			employee e1 = new employee();
			
			System.out.println(MyR.getInt("EmpolyeeID"));
			System.out.println(MyR.getString("EmpolyeeFirstName"));
			
			e1.setEmpolyeeID(MyR.getInt("EmpolyeeID"));
			e1.setEmpolyeeFirstName(MyR.getString("EmpolyeeFirstName"));
			e1.setEmpolyeeLastName(MyR.getString("EmpolyeeLastName"));
			e1.setEmployeePhoneNum(MyR.getString("EmployeePhoneNum"));
			e1.setEmpolyeeAddress(MyR.getString("EmpolyeeAddress"));
			e1.setEmployeeBranchID(MyR.getInt("EmployeeBranchID"));
			
			nameList.add(e1);
			
		}
		
		RequestDispatcher requestdispatch = request.getRequestDispatcher("Emp_Slef_Result.jsp");
		
		 request.setAttribute("Resultlist",nameList);
		 
		 requestdispatch.forward(request, response);
		
		}
		catch (Exception e) 
		{
		     System.out.println(e);
		} 
		
	}

}
