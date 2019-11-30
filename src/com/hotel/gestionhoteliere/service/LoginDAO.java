package com.hotel.gestionhoteliere.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class LoginDAO {

	public static boolean validate(String user, String password) {
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
	    Session session=sessionFactory.openSession();
	    session.beginTransaction();

		try {
		    Query query=session.createQuery("from User where Email=:user_name and Password=:password");
		    query.setString("user_name", user);
		    query.setString("password", password);
		    List list=query.list();
		    
			if (list.size()==1) {
				//result found, means valid inputs
				return true;
			}
		} catch (Exception ex) {
			System.out.println("Login error -->" + ex.getMessage());
			return false;
		} finally {
		      session.close();
		}
		return false;
		
		
		    
	}
}
