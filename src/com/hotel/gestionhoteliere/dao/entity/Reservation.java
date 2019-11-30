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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="reservations")
public class Reservation implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer ReservationId;
	@Temporal(TemporalType.DATE)
	private Date StartDate;
	@Temporal(TemporalType.DATE)
	private Date EndDate;
	private Float PricePerNight=null;
	private Date ReservationDate;
	private Date AcceptanceDate;
	private String State;
	
	@ManyToOne
	@JoinColumn(name="RoomId")
	private Room Room;
	
	@ManyToOne
	@JoinColumn(name="CommercialId")
	private User Commercial;
	
	@ManyToOne
	@JoinColumn(name="ClientId")
	private Client Client;

	@OneToOne(mappedBy = "Reservation", cascade = CascadeType.ALL)
	private Payment Payment;
	
	
	
	public Reservation() {
		super();
	}


	public Integer getReservationId() {
		return ReservationId;
	}


	public void setReservationId(Integer reservationId) {
		ReservationId = reservationId;
	}


	public Date getStartDate() {
		return StartDate;
	}


	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}


	public Date getEndDate() {
		return EndDate;
	}


	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}


	public float getPricePerNight() {
		return PricePerNight;
	}


	public void setPricePerNight(float pricePerNight) {
		PricePerNight = pricePerNight;
	}


	public Date getReservationDate() {
		return ReservationDate;
	}


	public void setReservationDate(Date reservationDate) {
		ReservationDate = reservationDate;
	}


	public Date getAcceptanceDate() {
		return AcceptanceDate;
	}


	public void setAcceptanceDate(Date acceptanceDate) {
		AcceptanceDate = acceptanceDate;
	}


	public String getState() {
		return State;
	}


	public void setState(String state) {
		State = state;
	}


	public Room getRoom() {
		return Room;
	}


	public void setRoom(Room room) {
		Room = room;
	}


	public User getCommercial() {
		return Commercial;
	}


	public void setCommercial(User commercial) {
		Commercial = commercial;
	}


	public Client getClient() {
		return Client;
	}


	public void setClient(Client client) {
		Client = client;
	}

	
	
}