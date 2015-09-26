package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;

import com.vo.customer;
import com.vo.user_name;
import com.util.hibernateUtil;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(name = "RegisterController", urlPatterns = { "/RegisterProcess" })
public class RegisterServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;
       
   
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		
		
		RegisterServlet R1 = new RegisterServlet();
		
		Connection connection = null;
		ResultSet MyR = null;
		
		String  UserTypeOfuser = null;
		Connection connBank = null, connUserPass = null , connRoot= null;
		
		String UserID = request.getParameter("username");
		String pwd =   request.getParameter("Password");
		
		try{
			
			Class.forName("org.gjt.mm.mysql.Driver");
			
			
			connUserPass = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
		    
			
			
			Statement statmentUserPass= connUserPass.createStatement();
			
			String insertquery = "   select  AES_DECRYPT(User_Id,'KEY'), AES_DECRYPT(Password,'KEY'),User_Type,viewInfo   FROM bank.user_name where User_Id = AES_ENCRYPT(" + "\"" +UserID+ "\"" + ",'KEY') AND Password =   AES_ENCRYPT(" + "\""+ pwd +"\"" + ",'KEY')";
		     
		     Statement s1 = connUserPass.createStatement();
				
				MyR = s1.executeQuery(insertquery);

		     if (MyR.next())
		     {
		    	 System.out.println( "inside while" );
		    	 System.out.println( MyR.getString( "AES_DECRYPT(User_Id,'KEY')" ) +
		    			 " "+ MyR.getString("AES_DECRYPT(Password,'KEY')")    );
		    	 
		    	 
		    	 String UserType = MyR.getString("User_Type");
		    	 int viewInfo = MyR.getInt("viewInfo");
	              System.out.println( "viewInfo .. "+viewInfo);
		    	 
	              connRoot =  DriverManager.getConnection("jdbc:mysql://localhost:3306","root","root");
                  System.out.println("Connected database successfully to root...");
           
                  connBank = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
                  System.out.println("Connected database successfully to bank...");
                  
                  Statement statmentRoot= connRoot.createStatement();
                  Statement statmentBank= connBank.createStatement();
	              
	              /////////////type = manager/////////////////////////////////
	              
	              if(UserType.equals("manager")){
	                UserTypeOfuser="UserM"+UserID ;
	                
	                String view = "View"+ UserTypeOfuser;
                    String manager_On_customer = "manager_On_customer" + UserTypeOfuser;
                    String manager_On_employee = "manager_On_employee" + UserTypeOfuser;
                    String manager_On_transaction = "manager_On_transaction"+ UserTypeOfuser;
                    
                    ResultSet branchIdResult  = statmentBank.executeQuery( " select Manager_branchID from bank.manager where Manager_userID = "+ "\"" +UserID+ "\""  );
                    branchIdResult.next();
                    
                    int manager_branch = branchIdResult.getInt("Manager_branchID");
                    System.out.println(" manager branch "+ manager_branch);
                    
	                if(viewInfo == 0)
	                {

	                      int createView =   statmentBank.executeUpdate("CREATE OR REPLACE  view "+view+" as "
	                               + "select * from bank.manager where Manager_userID = '"+UserID+"' ;   ");
	                            
	                              
	                       int View_On_customer =   statmentBank.executeUpdate("CREATE OR REPLACE  view "+manager_On_customer+" as "
	   	                               + "select * from bank.customer where customer.Customer_BranchID = "+ manager_branch);
	   	                            
	                         
	   	                   int View_On_employee =   statmentBank.executeUpdate("CREATE OR REPLACE  view "+manager_On_employee+" as "
		   	                               + "select * from bank.employee where employee.EmployeeBranchID =" + manager_branch);
		   	                 
	   	                   int View_on_Transaction = statmentBank.executeUpdate("CREATE OR REPLACE  view "+ manager_On_transaction +" as "
	                            + "select * from bank.transaction where Branch_ID = '"+ manager_branch +"' ;  ");
	   	                
	   	                   ////add view for transaction////
	   	                   
	   	                   System.out.println("create view is done");           
	                         
	   	                   int setsafe_mode =   statmentUserPass.executeUpdate("SET SQL_SAFE_UPDATES = 0"); 
	                       int addViewInfo =   statmentUserPass.executeUpdate("UPDATE  user_name set viewInfo = 1 where User_Id = AES_ENCRYPT('" + UserID + "','KEY') ");
	                       System.out.println("add view info is done");
	                               
	                               
	                       
	                        int createUser = statmentRoot.executeUpdate("create user '"+UserTypeOfuser+"'@'localhost';");       
	                        System.out.println("creat user is done"+ createUser);
	                        
	                      
	                         int  givePermission1 =   statmentBank.executeUpdate("GRANT SELECT ON bank."+view+" TO '"+UserTypeOfuser+"'@'localhost';");       
	                         System.out.println("create grant is done");
	                       
	                         int  givePermission_OnCustomer =   statmentBank.executeUpdate("GRANT SELECT ON bank."+ manager_On_customer +" TO '"+UserTypeOfuser+"'@'localhost';");       
	                         System.out.println("create grant is done");
	                          
	                         int  givePermission_OnEmployee =   statmentBank.executeUpdate("GRANT SELECT ON bank."+ manager_On_employee +" TO '"+UserTypeOfuser+"'@'localhost';");       
	                         System.out.println("create grant is done");
	                         
	                         int  givePermission_OnTransaction =   statmentBank.executeUpdate("GRANT SELECT ON bank."+ manager_On_transaction +" TO '"+UserTypeOfuser+"'@'localhost';");       
	                         System.out.println("create grant is done");
	                         
	                         R1.connectDB(UserTypeOfuser,"manager" );
	                          
	                } //endif viewInfo==0  
	                
	                else if(viewInfo==1)
	                {
	                	
	                	int createView =   statmentBank.executeUpdate("CREATE OR REPLACE  view "+view+" as "
	                               + "select * from bank.manager where Manager_userID = '"+UserID+"' ;   ");
	                	
	                   int View_On_customer =   statmentBank.executeUpdate("CREATE OR REPLACE  view "+manager_On_customer+" as "
	                               + "select * from bank.customer where customer.Customer_BranchID = "+ manager_branch);
	                            
                      
	                   int View_On_employee =   statmentBank.executeUpdate("CREATE OR REPLACE  view "+manager_On_employee+" as "
	   	                               + "select * from bank.employee where employee.EmployeeBranchID =" + manager_branch);
	                   
	                   int View_on_Transaction = statmentBank.executeUpdate("CREATE OR REPLACE  view "+ manager_On_transaction +" as "
	                            + "select * from bank.transaction where Branch_ID = '"+ manager_branch +"' ;  ");
	              
	                   R1.connectDB(UserTypeOfuser,"manager");
	                
	                }
	                
	          }
	          //////// type = customer  ////////////////////  
	          else if(UserType.equals("customer"))
	          {
	        	  
	        	  UserTypeOfuser= "UserC"+ UserID;
	                
	              String view = "View"+ UserTypeOfuser;
	              String customer_On_transaction = "customer_On_transaction"+ UserTypeOfuser;
	              
	              ResultSet branchIdResult  = statmentBank.executeQuery( " select CustomerID from bank.customer where Customer_UserId = "+ "\"" + UserID+ "\""  );
                  branchIdResult.next();
                  
                  int customer_id = branchIdResult.getInt("CustomerID");
                  System.out.println(" customer id "+ customer_id);
	              
	              
	              if(viewInfo == 0)
	              {

	            	//////view creation ////////////////// 
	                int createView  =   statmentBank.executeUpdate("CREATE OR REPLACE  view "+ view +" as "
	                               + "select * from bank.customer where Customer_UserId = '"+ UserID +"' ;  ");
	                
	                int View_on_Transaction =   statmentBank.executeUpdate("CREATE OR REPLACE  view "+ customer_On_transaction +" as "
                            + "select * from bank.transaction where CustomerID = '"+ customer_id +"' ;  ");
	                
	                
	                
	                ///adding view ///// 
	                
	                int setsafe_mode =   statmentUserPass.executeUpdate("SET SQL_SAFE_UPDATES = 0");                                                // UserID +"' ;");
	                
	                int addViewInfo =   statmentUserPass.executeUpdate("  UPDATE  user_name set viewInfo = 1 where User_Id = AES_ENCRYPT('" + UserID + "','KEY') ");          
                    System.out.println("add view info is done");
                    
                    int createUser = statmentRoot.executeUpdate("create user '"+UserTypeOfuser+"'@'localhost';");       
                    System.out.println("creat user is done"+ createUser);
                    
                    
                    ///granting permission ////////////////
                    int  givePermission1 =   statmentBank.executeUpdate("GRANT SELECT ON bank."+ view +" TO '"+UserTypeOfuser+"'@'localhost';");       
                    System.out.println("create grant is done");
                    
                    int  givePermission_OnTransaction =   statmentBank.executeUpdate("GRANT SELECT ON bank."+ customer_On_transaction +" TO '"+UserTypeOfuser+"'@'localhost';");       
                    System.out.println("create grant is done");
	                  
                    
	              }
	              else if(viewInfo==1)
	              {
	            	  
	            	  int createView =   statmentBank.executeUpdate("CREATE OR REPLACE  view "+ view +" as "
                              + "select * from bank.customer where Customer_UserId = '"+ UserID +"' ;  ");
	            	  
	            	  int View_on_Transaction =   statmentBank.executeUpdate("CREATE OR REPLACE  view "+ view +" as "
	                            + "select * from bank.transaction where CustomerID = '"+ customer_id +"' ;  ");
	            	  
	              }
              
	          }
	              
	          ///////////type = employee///////////////////////    
	          else if(UserType.equals("employee"))
	          {
	        	 ///add grant for transaction 
	        	  
	        	  ResultSet branchIdResult  = statmentBank.executeQuery( " select EmployeeBranchID from bank.employee where Empolyee_UserId = "+ "\"" +UserID+ "\""  );
                  branchIdResult.next();
                  
                  int employee_branch = branchIdResult.getInt("EmployeeBranchID");
                  System.out.println(" manager branch "+ employee_branch);
                  
	        	  
	        	  UserTypeOfuser = "UserE" + UserID;
	               
	        	  
	              String view = "View"+ UserTypeOfuser;
	              String employee_On_customer = "employee_On_customer"+ UserTypeOfuser;
	              String employee_On_transaction = "employee_On_transaction"+ UserTypeOfuser;
	            
	              
	              if(viewInfo == 0)
	              {

	                int createView =   statmentBank.executeUpdate("CREATE OR REPLACE  view "+ view +" as "
	                               + "select * from bank.employee where Empolyee_UserId = '"+ UserID +"' ;  ");
	                
	                int View_on_Customer =   statmentBank.executeUpdate("CREATE OR REPLACE  view "+ employee_On_customer +" as "
                            + "select * from bank.customer where Customer_BranchID = '"+ employee_branch +"' ;  ");
	                
	                int View_on_Transaction = statmentBank.executeUpdate("CREATE OR REPLACE  view "+ employee_On_transaction +" as "
                            + "select * from bank.transaction where Branch_ID = '"+ employee_branch +"' ;  ");
	                 
	                int setsafe_mode =   statmentUserPass.executeUpdate("SET SQL_SAFE_UPDATES = 0"); 
	                
	                int addViewInfo =   statmentUserPass.executeUpdate("UPDATE  user_name set viewInfo = 1 where User_Id = AES_ENCRYPT('" + UserID + "','KEY') ");
                    System.out.println("add view info is done");
                    
                    /*int createUser = statmentRoot.executeUpdate("create user '"+ UserTypeOfuser+ "'@'localhost';");       
                    System.out.println("creat user is done");*/
                    
                    int  givePermission1 =   statmentBank.executeUpdate("GRANT SELECT ON bank."+ view +" TO '"+UserTypeOfuser+"'@'localhost';");       
                    System.out.println("create grant is done");
                    
                    int  givePermission2 =   statmentBank.executeUpdate("GRANT SELECT ON bank."+ employee_On_customer +" TO '"+UserTypeOfuser+"'@'localhost';");       
                    System.out.println("create grant is done");
                    
                    int  givePermission3 =   statmentBank.executeUpdate("GRANT SELECT ON bank."+ employee_On_transaction +" TO '"+UserTypeOfuser+"'@'localhost';");       
                    System.out.println("create grant is done");
                    
                    
                    
	              }
	              else if(viewInfo == 1)
	              {
	            	  int createView =   statmentBank.executeUpdate("CREATE OR REPLACE  view "+ view +" as "
                              + "select * from bank.employee where Empolyee_UserId = '"+ UserID +"' ;  ");
               
	            	  int View_on_Customer =   statmentBank.executeUpdate("CREATE OR REPLACE  view "+ employee_On_customer +" as "
                       + "select * from bank.customer where Customer_BranchID = '"+ employee_branch +"' ;  ");
               
	            	  int View_on_Transaction = statmentBank.executeUpdate("CREATE OR REPLACE  view "+ employee_On_transaction +" as "
                       + "select * from bank.transaction where Branch_ID = '"+ employee_branch +"' ;  "); 
	            	  
	              }
	              
	          } 
	          else if(UserType.equals("bd_admin"))
	          {
	        	  // create audit and add grant on that
	        	  UserTypeOfuser="UserAD"+ UserID;
	                
	              String view = "View"+ UserTypeOfuser;
	             
	              if(viewInfo == 0)
	              {

	                int createView =   statmentBank.executeUpdate(" CREATE OR REPLACE  view "+ view +" as "
	                               + "select * from bank.db_administrative where DB_administrative_UserID = '"+ UserID +"' ;  ");
	                
	                int setsafe_mode =   statmentUserPass.executeUpdate("SET SQL_SAFE_UPDATES = 0"); 
	                
	                int addViewInfo =   statmentUserPass.executeUpdate("UPDATE  user_name set viewInfo = 1 where User_Id = AES_ENCRYPT('" + UserID + "','KEY') ");
                    System.out.println("add view info is done");
                    
                    int createUser = statmentRoot.executeUpdate("create user '"+UserTypeOfuser+"'@'localhost';");       
                    System.out.println("creat user is done");
                    
                    int  givePermission1 =   statmentBank.executeUpdate("GRANT SELECT ON bank."+ view +" TO '"+UserTypeOfuser+"'@'localhost';");       
                    System.out.println("create grant is done");
	                
                    
	             } 
	              
	              
	          }   
	              System.out.println( "Connected database successfully to User_password.. 01." );  
	              
	        }// end if(manager)
		    else
		    {
		     //send back error msg back and to register.html 
		     	 
		    }

		  
		     
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
	}// end service fun


	public void connectDB(String user, String usertype)
	{
		 Connection conn = null;
		    System.out.println("user typevvvv..."+user);
		   String sql="select * from bank.View"+user+" ;   ";
		   
		   try 
		   {
			    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank",user,"");
			    System.out.println("Connected database successfully to bank...");
			    
			    //call the web page related to the type of user
			    
		   } catch (Exception e) 
		   {
		        System.out.println(e);
		   }    
	}

}// end class

		
		
		
		
	
