package com.hotel.gestionhoteliere.dao.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="clients")
public class Client implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer CientId;
	@JoinColumn( unique = true)
	private String Cin;
	private String FirstName;
	private String LastName;
	@JoinColumn( unique = true)
	private String Email;
	private String Phone;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "Client")
    private List<Reservation> Reservations;
	
	public Client() {
		super();
	}

	public Integer getCientId() {
		return CientId;
	}

	public void setCientId(Integer cientId) {
		CientId = cientId;
	}

	public String getCin() {
		return Cin;
	}

	public void setCin(String cin) {
		Cin = cin;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public List<Reservation> getReservations() {
		return Reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		Reservations = reservations;
	}

	
	
}
