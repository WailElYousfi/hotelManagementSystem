package com.hotel.gestionhoteliere.presentation;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hotel.gestionhoteliere.dao.entity.Type;


@ManagedBean(name="typeBean")
@SessionScoped
public class TypeBean implements Serializable {
	
    private static final long serialVersionUID = 8150756503956053844L;
    private SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
	

    private Type type;
    
    @PostConstruct
    public void init() {
        type = new Type();
    }
		
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public List<Type> getAllTypes(){
		List<Type> types=new ArrayList<Type>();
		Session session = sessionfactory.openSession();
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from Type");
			types = query.getResultList();
			session.getTransaction().commit();
			session.close();
		} catch(Exception e) {
			System.out.println("Exception in getAllRoles: "+e.getMessage());
		}
		return types;
	}
	

}
