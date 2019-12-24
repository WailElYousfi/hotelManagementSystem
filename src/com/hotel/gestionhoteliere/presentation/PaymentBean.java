package com.hotel.gestionhoteliere.presentation;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.hotel.gestionhoteliere.dao.entity.Payment;
import com.hotel.gestionhoteliere.dao.entity.Reservation;
import com.hotel.gestionhoteliere.dao.entity.Role;
import com.hotel.gestionhoteliere.dao.entity.User;
import com.hotel.gestionhoteliere.generateData.Md5;
import com.hotel.gestionhoteliere.service.LoginDAO;
import com.hotel.gestionhoteliere.service.SessionUtils;



@ManagedBean(name="paymentBean")
@SessionScoped
public class PaymentBean implements Serializable {
	
    private static final long serialVersionUID = 8150756503956053844L;
    private SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();


    private Payment payment = new Payment(); 
    private String method;
    
    @PostConstruct
    public void init() {
        payment = new Payment();
    }
    
	public Payment getPayment() {
		return payment;
	}
	
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
	

	public List<Payment> getAllPayments(){
		Session session = sessionfactory.openSession();
		Query query = session.createQuery("from Payment");
		List<Payment> payments=new ArrayList<Payment>();
		payments = query.getResultList();
		session.close();
		return payments;
	}
	
	public void addPayment() {
		Session session = sessionfactory.openSession();
		try {	
			session.beginTransaction();
			session.save(this.payment);
			session.getTransaction().commit();
			payment = new Payment();
			method = null;
			session.close();
		} catch (Exception e) {
			System.out.println("Exception in addPayment method: " + e.getMessage());
		}
	}
	
	public String payReservation(Reservation r) {
		
		String dateToString = new SimpleDateFormat("yyyyMMdd").format(new Date());
		ReservationBean resBean = new ReservationBean();
		HttpSession session = SessionUtils.getSession();
		User comptable = (User) session.getAttribute("currentUser");
		
		this.payment.setReservation(r);
		this.payment.setAmountPaid(resBean.getTotalPrice(r));
		this.payment.setComptable(comptable);
		this.payment.setMethodOfPayment(this.method);
		this.payment.setPaymentDate(new Date());
		this.payment.setTransactionNumber("P" + dateToString + r.getReservationId());
		addPayment();
		return "payment";
	}
	
	

}
