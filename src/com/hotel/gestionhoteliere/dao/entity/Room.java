package com.hotel.gestionhoteliere.dao.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	private Integer RoomNumbere;
	private Integer Floor;
	private Long Surface;
	private String Image;
	private String Image2;
	private String Image3;

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="TypeId")
	private Type Type = new Type();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "Room")
    private List<Reservation> Reservations = new ArrayList<Reservation>();
	
	
	public Room() {
		super();
		Type = new Type();
	}


	public Integer getRoomId() {
		return RoomId;
	}


	public void setRoomId(Integer roomId) {
		RoomId = roomId;
	}


	public Integer getRoomNumbere() {
		return RoomNumbere;
	}


	public void setRoomNumbere(Integer roomNumbere) {
		RoomNumbere = roomNumbere;
	}


	public Integer getFloor() {
		return Floor;
	}


	public void setFloor(Integer floor) {
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


	public String getImage() {
		return Image;
	}


	public void setImage(String image) {
		Image = image;
	}


	public String getImage2() {
		return Image2;
	}


	public void setImage2(String image2) {
		Image2 = image2;
	}


	public String getImage3() {
		return Image3;
	}


	public void setImage3(String image3) {
		Image3 = image3;
	}
	
	


	
	
}
