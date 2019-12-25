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
	
    public Double count() {
    	Session session = sessionfactory.openSession();
		Query query = session.createQuery("Select sum(c.AmountPaid) from Payment c");
		Double nbr = (Double) query.uniqueResult();
		session.close();
		return nbr;
    }
    
    public Long methodch() {
    	Session session = sessionfactory.openSession();
		Query query = session.createQuery("Select count(c) from Payment c where c.MethodOfPayment='Chèque'");
		Long nbr = (Long) query.uniqueResult();
		session.close();
		return nbr;
    }

    public Long methodca() {
    	Session session = sessionfactory.openSession();
		Query query = session.createQuery("Select count(c) from Payment c where c.MethodOfPayment='Carte bancaire'");
		Long nbr = (Long) query.uniqueResult();
		session.close();
		return nbr;
    }
    
    public Long methoden() {
    	Session session = sessionfactory.openSession();
		Query query = session.createQuery("Select count(c) from Payment c where c.MethodOfPayment='En espèce'");
		Long nbr = (Long) query.uniqueResult();
		session.close();
		return nbr;
    }
    
    public Double profits10() {
    	Session session = sessionfactory.openSession();
		Query query = session.createQuery("Select sum(c.AmountPaid) from Payment c where c.PaymentDate='2019-10-09 19:56:57'");
		Double nbr = (Double) query.uniqueResult();
		session.close();
		return nbr;
    }
    
    public Double profits11() {
    	Session session = sessionfactory.openSession();
		Query query = session.createQuery("Select sum(c.AmountPaid) from Payment c where c.PaymentDate='2019-11-13 00:00:00'");
		Double nbr = (Double) query.uniqueResult();
		session.close();
		return nbr;
    }
    
    public Double profits12() {
    	Session session = sessionfactory.openSession();
		Query query = session.createQuery("Select sum(c.AmountPaid) from Payment c where c.PaymentDate='2019-12-20 15:54:48'");
		Double nbr = (Double) query.uniqueResult();
		session.close();
		return nbr;
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

			session.close();
		} catch (Exception e) {
			System.out.println("Exception in addPayment method: " + e.getMessage());
		}
	}
	
	public void payReservation(Reservation r) {
		
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
		payment = new Payment();
		method=null;
	}
	
	

}
