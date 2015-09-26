import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entity.customer;
import com.entity.user_name;
import com.util.hibernateUtil;


public class TestClass {

	
	
	public static void main(String[] args) {
		
		
		
		Session session = hibernateUtil.getSessionFactory().openSession();
		
		
		Query query = session.createQuery("select u from user_name u where u.User_Id = :U_ID");
		
		query.setString("U_ID", "sharma_4243");   //
		//query.setString("pwd","123456");

		
		List<user_name> user_01 = query.list();
		
		for(user_name user : user_01)
		{
			System.out.println("User_Id "+ user.getUser_Id());
			System.out.println("Password "+ user.getPassword());
		}
		
	
/*
		user_name U1 = new user_name( "","afnan_0000"   );
		
		System.out.println(U1.getUser_Id());
		
		Transaction transaction =  session.getTransaction();
		
		transaction.begin();
		
		session.save(U1);
		transaction.commit();*/

		
		
		
}
	
	

}
