package com.hotel.gestionhoteliere.presentation;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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

import com.hotel.gestionhoteliere.dao.entity.Client;
import com.hotel.gestionhoteliere.dao.entity.Reservation;
import com.hotel.gestionhoteliere.dao.entity.Role;
import com.hotel.gestionhoteliere.dao.entity.Room;
import com.hotel.gestionhoteliere.dao.entity.Type;
import com.hotel.gestionhoteliere.dao.entity.User;
import com.hotel.gestionhoteliere.generateData.Md5;
import com.hotel.gestionhoteliere.service.LoginDAO;
import com.hotel.gestionhoteliere.service.SessionUtils;



@ManagedBean(name="reservationBean")
@SessionScoped
public class ReservationBean implements Serializable {
	
    private static final long serialVersionUID = 8150756503956053844L;
    private SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();


    private Reservation reservation = new Reservation();
    
    
    @PostConstruct
    public void init() {
        reservation = new Reservation();
    }
	
	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}



	public Reservation getReservationById(Integer id) {
		Session session = sessionfactory.openSession();
		Reservation reservation = session.load(Reservation.class, id);
		session.close();
		return reservation;
	}
	
	public List<Reservation> getAllReservations(){
		Session session = sessionfactory.openSession();
		Query query = session.createQuery("from Reservation");
		List<Reservation> reservations = new ArrayList<Reservation>();
		reservations = query.getResultList();
		session.close();
		return reservations;
	}
	
	public void getReservationForm(Room room) {
		this.reservation.setRoom(room);
	    FacesContext context = FacesContext.getCurrentInstance();
		try {
			context.getExternalContext().redirect("details.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String addReservation() {
		ClientBean clientBean = new ClientBean();
		//on vérifie s'il y a déjà un client portant ce CIN
		Client oldClient = null;
		oldClient = clientBean.getClientByCin(this.reservation.getClient().getCin());
		if(oldClient != null) // on a trouvé ce client là
			reservation.setClient(oldClient);
		
		Session session = sessionfactory.openSession();
//	    FacesContext context = FacesContext.getCurrentInstance();
			try {	
				session.beginTransaction();
				reservation.setAcceptanceDate(null);
				reservation.setCommercial(null);
				reservation.setReservationDate(new Date());
				reservation.setState("En cours de traitement");
				session.save(reservation);
				session.getTransaction().commit();
				reservation = new Reservation();
//				context.getExternalContext().redirect("results.xhtml");
				session.close();
			} catch (Exception e) {
				System.out.println("Exception in addReservation method: " + e.getMessage());
			}
			return "success";
	}
		

}
