package com.hotel.gestionhoteliere.presentation;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hotel.gestionhoteliere.dao.entity.Client;
import com.hotel.gestionhoteliere.dao.entity.Reservation;
import com.hotel.gestionhoteliere.dao.entity.Role;
import com.hotel.gestionhoteliere.dao.entity.Room;
import com.hotel.gestionhoteliere.dao.entity.Type;
import com.hotel.gestionhoteliere.dao.entity.User;
import com.hotel.gestionhoteliere.generateData.Md5;

@ManagedBean(name="clientBean")
@SessionScoped
public class ClientBean implements Serializable {
	
	private static final long serialVersionUID = 8150756503956053844L;
    private SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
    
    private Client client;
    
    @PostConstruct
    public void init() {
        client = new Client();
    }
   
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

    
    public Client getClientById(Integer id) {
		Session session = sessionfactory.openSession();
		Client client = session.load(Client.class, id);
		session.close();
		return client;
	}
    
    public Long count() {
    	Session session = sessionfactory.openSession();
		Query query = session.createQuery("Select count(c) from Client c");
		Long nbr = (Long) query.uniqueResult();
		session.close();
		return nbr;
    }
    
    public Client getClientByCin(String cin) {
		Session session = sessionfactory.openSession();
		Query query = session.createQuery("from Client where Cin= :cin");
		query.setParameter("cin", cin);
		Client currentClient = (Client) query.uniqueResult();
		session.close();
		return currentClient;
	}
      
	public List<Client> getAllClients(){
		Session session = sessionfactory.openSession();
		Query query = session.createQuery("from Room");
		List<Client> clients = new ArrayList<Client>();
		clients = query.getResultList();
		session.close();
		return clients;
	}
    
    public void addClient() throws IOException {
		Session session = sessionfactory.openSession();
	    FacesContext context = FacesContext.getCurrentInstance();
			try {
				session.beginTransaction();
				session.save(client);
				session.getTransaction().commit();
				client = new Client();
				session.close();
			} catch (Exception e) {
				System.out.println("Exception in addClient method: " + e.getMessage());
			}
	}
	
}



