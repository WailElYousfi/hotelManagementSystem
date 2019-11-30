package com.hotel.gestionhoteliere.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="payments")
public class Payment implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer PaymentId;
	@JoinColumn( unique = true)
	private String TransactionNumber;
	private String MethodOfPayment;
	private Date PaymentDate;
	private float AmountPaid;
	
	@OneToOne(cascade = CascadeType.ALL) // ONE TO ONE RELATIONSHIP
	@JoinColumn(name="ReservationId", unique = true)
	private Reservation Reservation;
	
	@ManyToOne
	@JoinColumn(name="ComptableId")
	private User Comptable;
	
	
	public Payment() {
		super();
	}


	public Integer getPaymentId() {
		return PaymentId;
	}


	public void setPaymentId(Integer paymentId) {
		PaymentId = paymentId;
	}


	public String getTransactionNumber() {
		return TransactionNumber;
	}


	public void setTransactionNumber(String transactionNumber) {
		TransactionNumber = transactionNumber;
	}


	public String getMethodOfPayment() {
		return MethodOfPayment;
	}


	public void setMethodOfPayment(String methodOfPayment) {
		MethodOfPayment = methodOfPayment;
	}


	public Date getPaymentDate() {
		return PaymentDate;
	}


	public void setPaymentDate(Date paymentDate) {
		PaymentDate = paymentDate;
	}


	public float getAmountPaid() {
		return AmountPaid;
	}


	public void setAmountPaid(float amountPaid) {
		AmountPaid = amountPaid;
	}


	public Reservation getReservation() {
		return Reservation;
	}


	public void setReservation(Reservation reservation) {
		Reservation = reservation;
	}


	public User getComptable() {
		return Comptable;
	}


	public void setComptable(User comptable) {
		Comptable = comptable;
	}

	
	
}
