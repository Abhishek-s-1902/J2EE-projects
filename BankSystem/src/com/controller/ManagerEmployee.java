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
import com.vo.employee;

/**
 * @ManagerEmployee: handles doPost request from other jsp page
 */
@WebServlet("/Mg_Employee")
public class ManagerEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  //@doPost: override servlet doPost method and Handles the HTTP Post request from client.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		System.out.println("manager");
		
		 	Connection myConn= null;
		    Statement Mystat= null;
		    ResultSet MyR=null;
		    PreparedStatement pre=null;
		    String u="";
		    String p="";
		    String phone = null;
			Integer phoneno = null;
			Integer empId = null;
			
			try
			{
			
			Class.forName("org.gjt.mm.mysql.Driver");
			
			ServletContext servecontext = request.getServletContext(); 
			
			
			String UserTypeOfuser = (String)servecontext.getAttribute("username");
			myConn = (Connection)servecontext.getAttribute("connString");
			
			
			Mystat= myConn.createStatement();
			
			System.out.println("connection good");
			
			  if(!request.getParameter("employee_Id").equals(""))
			  {
				
				System.out.println(" inside id ");
				empId = Integer.parseInt(request.getParameter("employee_Id"));
				System.out.println(" empId "+ empId);
				
			  }
			  else
			  {
				System.out.println(" inside blank id ");
				empId = 000000;
				System.out.println(" empId "+ empId);
			}
			
			if(!request.getParameter("phone_no").equals(""))
			{
							
				System.out.println(" inside phone ");
				phone =   request.getParameter("phone_no");
				System.out.println(" phone "+ phone);
			
			}
			else
			{
				System.out.println(" inside blank phone ");
				phone =   "000000";
				System.out.println(" phone "+ phone);
			}
			
			String view = "manager_On_employee"+UserTypeOfuser;
			String insertquery = " select * from bank."+ view +" where EmpolyeeID = "+empId+" OR EmployeePhoneNum = "+phone ;
			
			MyR= Mystat.executeQuery(insertquery);	
			
			List<employee> nameList = new ArrayList<employee>();
			
			while(MyR.next()) 
			{
				employee e1 = new employee();
				
				System.out.println(MyR.getInt("EmpolyeeID"));
				System.out.println(MyR.getString("EmployeePhoneNum"));
				
				e1.setEmpolyeeID(MyR.getInt("EmpolyeeID"));
				e1.setEmployeePhoneNum(MyR.getString("EmployeePhoneNum"));
				
				nameList.add(e1);
			}
			
			RequestDispatcher requestdispatch = request.getRequestDispatcher("Mag_Emp_Result.jsp");
			
			 request.setAttribute("Resultlist",nameList);
			 
			 requestdispatch.forward(request, response);
			
			}
			catch (Exception e) 
			{
			     System.out.println(e);
			} 
			
			
	}

}
