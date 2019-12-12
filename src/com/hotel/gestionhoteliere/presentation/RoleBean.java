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



@ManagedBean(name="roleBean")
@SessionScoped
public class RoleBean implements Serializable {
	
    private static final long serialVersionUID = 8150756503956053844L;
    private SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
	

    private Role role;
    
    @PostConstruct
    public void init() {
        role = new Role();
    }
	
	public Role getUser() {
		return role;
	}
	
	public void setUser(Role role) {
		this.role = role;
	}
		
	public List<Role> getAllRoles(){
		List<Role> roles=new ArrayList<Role>();
		Session session = sessionfactory.openSession();
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from Role");
			roles = query.getResultList();
			session.getTransaction().commit();
			session.close();
		} catch(Exception e) {
			System.out.println("Exception in getAllRoles: "+e.getMessage());
		}
		return roles;
	}
	

}
