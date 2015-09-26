package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * SmapleRegister: First servlet class which will handle the login 
 * request from the client.
 */
@WebServlet("/RegisterServlet")
public class SmapleRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/*
	 * @doPost: override servlet doPost method and Handles the HTTP Post request from client.
	 *  @request variable contains data from client request and @responce variable sends back the 
	 *  processed data 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		SmapleRegister Sample1 = new SmapleRegister();
		
		Connection connection = null;
		ResultSet MyR=null;
		
		String  UserTypeOfuser = null;
		Connection connBank = null, connUserPass = null , connRoot= null;
		
		String UserID = request.getParameter("username");
		String pwd =   request.getParameter("Password");
		
		try{
			
			Class.forName("org.gjt.mm.mysql.Driver");
			
			//@connUserPass: contains connections variable from the database
			connUserPass = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","@bhi.0212");
		    
			
			
			Statement statmentUserPass= connUserPass.createStatement();
			
			//@insertquery: contains the result is the login Id and Password is correct
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
		    	 
	              connRoot =  DriverManager.getConnection("jdbc:mysql://localhost:3306","root","@bhi.0212");
                  System.out.println("Connected database successfully to root...");
           
                  connBank = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","@bhi.0212");
                  System.out.println("Connected database successfully to bank...");
                  
                  Statement statmentRoot= connRoot.createStatement();
                  Statement statmentBank= connBank.createStatement();
	              
                  ResultSet updateLog = statmentRoot.executeQuery(" call bank.audit_insert_main() ");       
                  System.out.println("update looged in table is done");
                  
	              
                  //User type = manager
                  if(UserType.equals("manager")){
	                UserTypeOfuser="UserM"+UserID ;   // UserMRichard_mg
	                
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
	   	                
	   	                   //add view for transaction
	   	                   
	   	                   System.out.println("create view is done");           
	                         
	   	                  int setsafe_mode =   statmentUserPass.executeUpdate("SET SQL_SAFE_UPDATES = 0"); 
	                       int addViewInfo =   statmentUserPass.executeUpdate("UPDATE  user_name set viewInfo = 1 where User_Id = AES_ENCRYPT('" + UserID + "','KEY') ");
	                       System.out.println("add view info is done");
	                               
	                       //create user                   
	                       
	                         int createUser = statmentRoot.executeUpdate("create user '"+UserTypeOfuser+"'@'localhost';");       
	                         System.out.println("creat user is done");
	                        
	                      
	                         int  givePermission1 =   statmentBank.executeUpdate("GRANT SELECT ON bank."+view+" TO '"+UserTypeOfuser+"'@'localhost';");       
	                         System.out.println("create grant is done");
	                       
	                         int  givePermission_OnCustomer =   statmentBank.executeUpdate("GRANT SELECT ON bank."+ manager_On_customer +" TO '"+UserTypeOfuser+"'@'localhost';");       
	                         System.out.println("create grant is done");
	                          
	                         int  givePermission_OnEmployee =   statmentBank.executeUpdate("GRANT SELECT ON bank."+ manager_On_employee +" TO '"+UserTypeOfuser+"'@'localhost';");       
	                         System.out.println("create grant is done");
	                         
	                         int  givePermission_OnTransaction =   statmentBank.executeUpdate("GRANT SELECT ON bank."+ manager_On_transaction +" TO '"+UserTypeOfuser+"'@'localhost';");       
	                         System.out.println("create grant is done");
	                         
	                         
	                          
	                         Sample1.connectDB( UserTypeOfuser  ,"manager",request,  response,UserID);
	                
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
	                   
	                   Sample1.connectDB( UserTypeOfuser  ,"manager",request,  response,UserID);
	                   
	               }
	                
	          }
	          //type = customer
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

	            	//view creation
	                int createView  =   statmentBank.executeUpdate("CREATE OR REPLACE  view "+ view +" as "
	                               + "select * from bank.customer where Customer_UserId = '"+ UserID +"' ;  ");
	                
	                int View_on_Transaction =   statmentBank.executeUpdate("CREATE OR REPLACE  view "+ customer_On_transaction +" as "
                            + "select * from bank.transaction where CustomerID = '"+ customer_id +"' ;  ");
	                
	                
	                
	                //adding view
	                
	                int setsafe_mode =   statmentUserPass.executeUpdate("SET SQL_SAFE_UPDATES = 0");                                                // UserID +"' ;");
	                
	                int addViewInfo =   statmentUserPass.executeUpdate("  UPDATE  user_name set viewInfo = 1 where User_Id = AES_ENCRYPT('" + UserID + "','KEY') ");          
                    System.out.println("add view info is done");
                    
                    //create user 
                    
                    int createUser = statmentRoot.executeUpdate("create user '"+UserTypeOfuser+"'@'localhost';");       
                    System.out.println("creat user is done");
                    
                    
                    //granting permission 
                    int  givePermission1 =   statmentBank.executeUpdate("GRANT SELECT ON bank."+ view +" TO '"+UserTypeOfuser+"'@'localhost';");       
                    System.out.println("create grant is done");
                    
                    int  givePermission_OnTransaction =   statmentBank.executeUpdate("GRANT SELECT ON bank."+ customer_On_transaction +" TO '"+UserTypeOfuser+"'@'localhost';");       
                    System.out.println("create grant is done");
	                  
                    
	              }
	              else if(viewInfo==1)
	              {
	            	  System.out.println("inside 1 :"+view);
	            	  
	            	  int createView =   statmentBank.executeUpdate("CREATE OR REPLACE  view "+ view +" as "
                              + "select * from bank.customer where Customer_UserId = '"+ UserID +"'");
	            	  
	            	  int View_on_Transaction =   statmentBank.executeUpdate("CREATE OR REPLACE  view "+ customer_On_transaction +" as "
	                            + "select * from bank.transaction where CustomerID = '"+ customer_id +"' ;  ");
	            	  
	              }
	              
	              Sample1.connectDB( UserTypeOfuser,"customer",request,response,UserID);
              
	          }
	              
	          //type = employee  
	          else if(UserType.equals("employee"))
	          {
	        	 ///add grant for transaction 
	        	  
	        	  ResultSet branchIdResult  = statmentBank.executeQuery( " select EmployeeBranchID from bank.employee where Empolyee_UserId = "+ "\"" +UserID+ "\""  );
	        	  branchIdResult.next();
	        	  int employee_branch = branchIdResult.getInt("EmployeeBranchID");
                  System.out.println(" manager branch "+ employee_branch);
                  
                  ResultSet employeeIdResult  = statmentBank.executeQuery( " select EmpolyeeID from bank.employee where Empolyee_UserId = "+ "\"" +UserID+ "\""  );
                  employeeIdResult.next();
                  int employee_Id =  employeeIdResult.getInt("EmpolyeeID");
                  System.out.println(" manager branch "+ employee_Id);
                  
                  
                  
                 
                  
	        	  UserTypeOfuser = "UserE" + UserID;
	               
	        	  
	              String view = "View"+ UserTypeOfuser;
	              String employee_On_customer = "employee_On_customer"+ UserTypeOfuser;
	              String employee_On_transaction = "employee_On_transaction"+ UserTypeOfuser;
	              String employee_On_self = "employee_On_self"+ UserTypeOfuser;
	              
	              if(viewInfo == 0)
	              {

	                int createView =   statmentBank.executeUpdate("CREATE OR REPLACE  view "+ view +" as "
	                               + "select * from bank.employee where Empolyee_UserId = '"+ UserID +"' ;  ");
	                
	                int View_on_Customer =   statmentBank.executeUpdate("CREATE OR REPLACE  view "+ employee_On_customer +" as "
                            + "select * from bank.customer where Customer_BranchID = '"+ employee_branch +"' ;  ");
	                
	                int View_on_Transaction = statmentBank.executeUpdate("CREATE OR REPLACE  view "+ employee_On_transaction +" as "
                            + "select * from bank.transaction where Branch_ID = '"+ employee_branch +"' ;  ");
	                 
	                int View_on_self = statmentBank.executeUpdate("CREATE OR REPLACE  view "+ employee_On_self +" as "
                            + "select * from bank.employee where EmpolyeeID = '"+ employee_Id +"' ;  ");
	                
	                
	                int setsafe_mode =   statmentUserPass.executeUpdate("SET SQL_SAFE_UPDATES = 0"); 
	                
	                
	                int addViewInfo =   statmentUserPass.executeUpdate("UPDATE  user_name set viewInfo = 1 where User_Id = AES_ENCRYPT('" + UserID + "','KEY') ");
                    System.out.println("add view info is done");
                    
                    int createUser = statmentRoot.executeUpdate("create user '"+ UserTypeOfuser+ "'@'localhost';");       
                    System.out.println("creat user is done");
                    
                    int  givePermission1 =   statmentBank.executeUpdate("GRANT SELECT ON bank."+ view +" TO '"+UserTypeOfuser+"'@'localhost';");       
                    System.out.println("create grant is done");
                    
                    int  givePermission2 =   statmentBank.executeUpdate("GRANT SELECT ON bank."+ employee_On_customer +" TO '"+UserTypeOfuser+"'@'localhost';");       
                    System.out.println("create grant is done");
                    
                    int  givePermission3 =   statmentBank.executeUpdate("GRANT SELECT ON bank."+ employee_On_transaction +" TO '"+UserTypeOfuser+"'@'localhost';");       
                    System.out.println("create grant is done");
                    
                    int  givePermission4 =   statmentBank.executeUpdate("GRANT SELECT ON bank."+ employee_On_self +" TO '"+UserTypeOfuser+"'@'localhost';");       
                    System.out.println("create grant is done");
                    
                    Sample1.connectDB( UserTypeOfuser,"employee",request,response,UserID);
                    
	              }
	              else if(viewInfo == 1)
	              {
	            	  System.out.println("inside employee view info "+ viewInfo);
	            	  int createView =   statmentBank.executeUpdate("CREATE OR REPLACE  view "+ view +" as "
                              + "select * from bank.employee where Empolyee_UserId = '"+ UserID +"' ;  ");
               
	            	  int View_on_Customer =   statmentBank.executeUpdate("CREATE OR REPLACE  view "+ employee_On_customer +" as "
                       + "select * from bank.customer where Customer_BranchID = '"+ employee_branch +"' ;  ");
               
	            	  int View_on_Transaction = statmentBank.executeUpdate("CREATE OR REPLACE  view "+ employee_On_transaction +" as "
                       + "select * from bank.transaction where Branch_ID = '"+ employee_branch +"' ;  "); 
	            	  
	            	  int View_on_self = statmentBank.executeUpdate("CREATE OR REPLACE  view "+ employee_On_self +" as "
	                            + "select * from bank.employee where EmpolyeeID = '"+ employee_Id +"' ;  ");
	            	  
	            	  
	            	  Sample1.connectDB( UserTypeOfuser  ,"employee", request,  response,UserID);
	              
	              }
	              
	         } 
                  
              //type = Admin    
	          else if(UserType.equals("bd_admin"))
	          {
	        	  // create audit and add grant on that
	        	  UserTypeOfuser="UserDB"+ UserID;
	                
	              String view = "View"+ UserTypeOfuser;
	             
	              String table_01 = "audit_login_table";
	              
	              if(viewInfo == 0)
	              {

	               /* int createView =   statmentBank.executeUpdate(" CREATE OR REPLACE  view "+ view +" as "
	                               + "select * from bank.db_administrative where DB_administrative_UserID = '"+ UserID +"' ;  ");*/
	                
	                int setsafe_mode =   statmentUserPass.executeUpdate("SET SQL_SAFE_UPDATES = 0"); 
	                
	                int addViewInfo =   statmentUserPass.executeUpdate("UPDATE  user_name set viewInfo = 1 where User_Id = AES_ENCRYPT('" + UserID + "','KEY') ");
                    System.out.println("add view info is done");
                    
                    int createUser = statmentRoot.executeUpdate("create user '"+UserTypeOfuser+"'@'localhost';");       
                    System.out.println("creat user is done");
                    
                    int  givePermission1 =   statmentBank.executeUpdate("GRANT SELECT ON bank."+ table_01 +" TO '"+UserTypeOfuser+"'@'localhost';");       
                    System.out.println("create grant is done");
	                
                    
	             } 
	              else if(viewInfo == 1)
	              {
	            	  
	            	  
	            	  int  givePermission1 =   statmentBank.executeUpdate("GRANT SELECT ON bank."+ table_01 +" TO '"+UserTypeOfuser+"'@'localhost';");       
	                    System.out.println("create grant is done");
	            	  
	                    
	              }
	              
	              Sample1.connectDB( UserTypeOfuser  ,"DB_abmin", request,  response,UserID);
	              
	          }   
	              System.out.println( "Connected database successfully to User_password.. 01." );  
	              
	        }// end if
		     else
		     {
		    	 System.out.println("user doesnt exist");
		    	 
		     }
 
		     
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
	}
	
	
	/*
	 * @connectDB: based on User type it does the connection and create dedicated connection for that user
	 */
	public void connectDB(String UserTypeOfuser, String usertype, HttpServletRequest request, HttpServletResponse response, String connected_user)
	{
		   Connection conn = null;
		   System.out.println("user typevvvv..."+UserTypeOfuser);
		   String sql="select * from bank.View"+UserTypeOfuser+" ;   ";
		   RequestDispatcher requestdispatch = null;
		   try 
		   {
			    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank",UserTypeOfuser,"");
			    System.out.println("Connected database successfully to bank...");
			    
			    //call the web page related to the type of user
			    
			    if(usertype.equals("manager"))
			    {
			    	 requestdispatch = request.getRequestDispatcher("managerView.jsp");
			    }
			    else if(usertype.equals("employee"))
			    {
			    	 requestdispatch = request.getRequestDispatcher("employeeView.jsp");
			    }	
			    else if(usertype.equals("customer"))
			    {
			    	System.out.println("inside customer");
			    	 requestdispatch = request.getRequestDispatcher("customerView.jsp");
			    }
			    else if(usertype.equals("DB_abmin"))
			    {
			    	 requestdispatch = request.getRequestDispatcher("adminView.jsp");
			    }
			    
			    ServletContext servecontext = request.getServletContext(); 
			    
			    servecontext.setAttribute("username",UserTypeOfuser);
			    servecontext.setAttribute("connString",conn);
			    servecontext.setAttribute("user_connected",connected_user);
			    request.setAttribute("username",UserTypeOfuser);
			    request.setAttribute("connString",conn );
			    
			    
			    requestdispatch.forward(request, response);
			    
			} catch (Exception e) 
		   {
		        System.out.println(e);
		   }    
	}

}
