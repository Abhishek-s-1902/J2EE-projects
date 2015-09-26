package com.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

@SuppressWarnings("deprecation")
public class hibernateUtil {
	
	private static SessionFactory sessionFactory;
	
	
	static{
		
		
		Configuration configuration  = new Configuration();
		
		//loads hibernate.cfg.xml;
		configuration.configure();
		
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		
	}
	
	public static SessionFactory getSessionFactory(){
		
		
		return sessionFactory;
				
		
	}

}
