package com.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


@SuppressWarnings("deprecation")
public class hibernateUtil {
	
	private static SessionFactory sessionFactory;
	
static{
	
		System.out.println(" IN here");
		
		Configuration configuration  = new Configuration();
		
		
		//loads hibernate.cfg.xml;
		configuration.configure();
		
		
		//configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/bank"+"UserMnanda_mg");
		
		
		configuration.setProperty("hibernate.connection.username","UserDBPhenix_db" );
		
		configuration.setProperty("hibernate.connection.password","");
		
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		System.out.println("OK here");
	}

	public static SessionFactory getSessionFactory(){
	
	
	return sessionFactory;
			
	}

}
