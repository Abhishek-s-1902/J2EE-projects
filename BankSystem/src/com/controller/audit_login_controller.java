package com.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.util.hibernateUtil;
import com.vo.Audit_Login_Table;

import java.util.Date;
/**
 * Servlet implementation class audit_login_controller
 */
@WebServlet("/audit_login")
public class audit_login_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("BD admin");
		String loggedin_user = null;
		String system_user = null;
		Date time_stamp = null;
		DateFormat df = new SimpleDateFormat("yyyy MM dd");  //Locale.ENGLISH kk:mm:ss
		Session session = hibernateUtil.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(Audit_Login_Table.class);
		
		Criterion criterion_loggedin_user = null;
		Criterion criterion_system_user = null;
		Criterion criterion_time_stamp = null;
		try
		{
		
		if(!request.getParameter("user_Id").equals(""))
		{
			
			System.out.println(" inside id ");
			loggedin_user = request.getParameter("customer_Id");
			
			criterion_loggedin_user =  Restrictions.like("User_loggedin", loggedin_user);
		}
		else
		{
			System.out.println(" inside blank id ");
			loggedin_user = "000000";
			criterion_loggedin_user =  Restrictions.like("User_loggedin", loggedin_user);
		}
		
		//2 second
		if(!request.getParameter("system_user_Id").equals(""))
		{
		
			System.out.println(" inside phone ");
			system_user =   request.getParameter("system_user_Id");
			criterion_system_user =  Restrictions.like("Current_user_loggedin", system_user);
		}
		else
		{
			System.out.println(" inside blank phone ");
			system_user =   "000000";
			criterion_system_user =  Restrictions.like("Current_user_loggedin", system_user);
		}

		//3 third
		/*if(!request.getParameter("Time_stamp").equals(""))
		{
		
			System.out.println(" inside phone ");
			time_stamp =  df.parse(request.getParameter("Time_stamp"));
			criterion_time_stamp =  Restrictions.like("logged_in_time", time_stamp);
		}
		else
		{
			System.out.println(" inside blank phone ");
			time_stamp =  df.parse("0000");
			criterion_time_stamp =  Restrictions.like("logged_in_time",time_stamp);
		}*/
		
		//Restrictions.or(criterion_loggedin_user, criterion_system_user, criterion_time_stamp); 
		//criterion_time_stamp
		
		LogicalExpression LEX =  Restrictions.or(criterion_loggedin_user, criterion_system_user );
		
		criteria.add(LEX);

		
		
		ArrayList<Audit_Login_Table> user_list =  (ArrayList<Audit_Login_Table>) criteria.list();
		
		
			System.out.println("size "+ user_list.size());
		
			
		for(Audit_Login_Table audit : user_list)
		{
			System.out.println("User_Id "+ audit.getUser_loggedin());
			System.out.println("time "+ audit.getCurrent_user_loggedin());
			
		}

		RequestDispatcher requestdispatch = request.getRequestDispatcher("main_Audit_Result.jsp");
		
		 request.setAttribute("audit_list",user_list);
		 
		 
		 requestdispatch.forward(request, response);

		}catch (Exception e) 
		   {
	        System.out.println(e);
	   }
		
	}

}
