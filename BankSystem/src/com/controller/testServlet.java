package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import com.util.hibernateUtil;
import com.vo.customer;

/**
 * Servlet implementation class testServlet
 */
@WebServlet("/testoncustomer")
public class testServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		//customer c1 = (customer)s1.get(customer.class,660001);
		
		Session session = hibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("select u from cutomer u where u.CustomerID = :U_ID");
		
		query.setInteger("U_ID", 660001);
		
		List<customer> user_01 = query.list();
		
		for(customer user : user_01)
		{
			System.out.println("User_Id "+ user.getCust_ID());
			System.out.println("Password "+ user.getFirst_Name());
		}
		
		//System.out.println("cut id"+ c1.getCust_ID());
	}

}
