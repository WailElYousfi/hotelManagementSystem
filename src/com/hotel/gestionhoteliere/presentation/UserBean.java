package com.hotel.gestionhoteliere.presentation;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hotel.gestionhoteliere.dao.entity.Role;
import com.hotel.gestionhoteliere.dao.entity.User;
import com.hotel.gestionhoteliere.generateData.Md5;
import com.hotel.gestionhoteliere.service.LoginDAO;
import com.hotel.gestionhoteliere.service.SessionUtils;



@ManagedBean(name="userBean")
@SessionScoped
public class UserBean implements Serializable {
	
    private static final long serialVersionUID = 8150756503956053844L;
    private SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();

    private User user;
    
    @PostConstruct
    public void init() {
        user = new User();
    }
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
		
	
	public Role getRole(String email) {
		User currentUser=null;
		try {
			Session session = sessionfactory.openSession();
			Query query = session.createQuery("from User where Email= :email");
			query.setString("email", email);
			currentUser = (User) query.uniqueResult();
			session.getTransaction().commit();
			session.close();
			
		} catch(Exception e) {
			 System.out.println("Exception in getAllUsers: "+e.getMessage());
		}
		return currentUser.getRole();

	}
	
	public List<User> getAllUsers(){
		List<User> users=new ArrayList<User>();
		try {
			Session session = sessionfactory.openSession();
			Query query = session.createQuery("from User");
			users = query.getResultList();
			session.getTransaction().commit();
			session.close();
		} catch(Exception e) {
			 System.out.println("Exception in getAllUsers: "+e.getMessage());
		}
		return users;
	}
	
	/*public boolean addUser() {
		try {
			Session session = sessionfactory.openSession();
			session.save(user);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch(Exception e) {
			 System.out.println("Exception in getAllUsers: "+e.getMessage());
			 return false;
		}
	}*/
	

}
