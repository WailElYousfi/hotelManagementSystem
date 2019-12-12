package com.hotel.gestionhoteliere.dao.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer UserId;
	private String FirstName;
	private String LastName;
	@JoinColumn( unique = true)
	private String Email;
	private String Phone;
	private String Password;
	
	@ManyToOne
	@JoinColumn(name="RoleId")
	private Role Role = new Role();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "Commercial")
	private List<Reservation> Reservations;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "Comptable")
    private List<Payment> Payments;
	
	public User() {
		super();
		Role=new Role();
	}

	public Integer getUserId() {
		return UserId;
	}

	public void setUserId(Integer userId) {
		UserId = userId;
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

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public Role getRole() {
		return Role;
	}

	public void setRole(Role role) {
		Role = role;
	}

	public List<Reservation> getReservations() {
		return Reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		Reservations = reservations;
	}

	public List<Payment> getPayments() {
		return Payments;
	}

	public void setPayments(List<Payment> payments) {
		Payments = payments;
	}

	
	
	
}
