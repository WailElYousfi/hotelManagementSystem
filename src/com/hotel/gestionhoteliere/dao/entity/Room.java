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
@Table(name="rooms")
public class Room implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer RoomId;
	@JoinColumn( unique = true)
	private int RoomNumbere;
	private int Floor;
	private Long Surface;
	
	@ManyToOne
	@JoinColumn(name="TypeId")
	private Type Type;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "Room")
    private List<Reservation> Reservations;
	
	
	public Room() {
		super();
	}


	public Integer getRoomId() {
		return RoomId;
	}


	public void setRoomId(Integer roomId) {
		RoomId = roomId;
	}


	public int getRoomNumbere() {
		return RoomNumbere;
	}


	public void setRoomNumbere(int roomNumbere) {
		RoomNumbere = roomNumbere;
	}


	public int getFloor() {
		return Floor;
	}


	public void setFloor(int floor) {
		Floor = floor;
	}


	public Long getSurface() {
		return Surface;
	}


	public void setSurface(Long surface) {
		Surface = surface;
	}


	public Type getType() {
		return Type;
	}


	public void setType(Type type) {
		Type = type;
	}


	public List<Reservation> getReservations() {
		return Reservations;
	}


	public void setReservations(List<Reservation> reservations) {
		Reservations = reservations;
	}


	
	
}
