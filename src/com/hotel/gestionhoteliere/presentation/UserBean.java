package com.hotel.gestionhoteliere.presentation;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
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


    private User user = new User();
    private String Confirmation;
    
    
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
	
	public String getConfirmation() {
		return Confirmation;
	}

	public void setConfirmation(String confirmation) {
		Confirmation = confirmation;
	}
	
	
	
	public User getUserById(Integer id) {
		Session session = sessionfactory.openSession();
		User user = session.load(User.class, id);
		session.close();
		return user;
	}
	
	public User getUserByEmail(String email) {
		Session session = sessionfactory.openSession();
		Query query = session.createQuery("from User where Email= :email");
		query.setString("email", email);
		User currentUser = (User) query.uniqueResult();
		session.close();
		return currentUser;
	}
	
	public List<User> getAllUsers(){
		Session session = sessionfactory.openSession();
		Query query = session.createQuery("from User");
		List<User> users=new ArrayList<User>();
		users = query.getResultList();
		session.close();
		return users;
	}
	
	public void addUser() {
		Session session = sessionfactory.openSession();
	    FacesContext context = FacesContext.getCurrentInstance();
		if(user.getPassword().equals(this.Confirmation)) {		// si password == confirmation
			try {
				session.beginTransaction();
				String password_crypted = Md5.getMD5(user.getPassword()); 		// Crypter le mot de passe
				user.setPassword(password_crypted);			// remplacer le mot de passe saisi par le mot de passe crypté
				RoleBean roleBean = new RoleBean(); 
				Role role = roleBean.getRoleById(user.getRole().getRoleId());		 // Récuperer le role qui a l'id envoyé par le formulaire
				user.setRole(role);
				session.save(user);
				session.getTransaction().commit();
				session.close();
				user = new User();
				context.getExternalContext().redirect("index.xhtml");
			} catch (Exception e) {
				System.out.println("Exception in addUser method: " + e.getMessage());
			}
		}else {
			context.addMessage("myForm:confirmationPassword", new FacesMessage("Password and confirm password don't match"));
		}	
	}
		
	public String editUserRecord(User user) {
		this.user=user;
		return "ok";
	}
 
	public void updateUserRecord() {
		Session session = sessionfactory.openSession();
	    FacesContext context = FacesContext.getCurrentInstance();
        try {
        	session.beginTransaction();
            session.merge(this.user);        
            session.getTransaction().commit();
        	session.close();
        	user = new User();
			context.getExternalContext().redirect("index.xhtml");
        } catch(Exception e){
            System.out.println("Exception in updateUserRecord: " + e.getMessage());
        }
    }
	
	public void deleteUserRecord(User user) {
		Session session = sessionfactory.openSession();
        try {
        	session.beginTransaction();
        	session.delete(user);
        	session.getTransaction().commit();
        	session.close();
        } catch(Exception e){
            System.out.println("Exception in deleteUserRecord: " + e.getMessage());
        }
	}

}
