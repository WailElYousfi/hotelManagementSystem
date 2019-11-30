package com.hotel.gestionhoteliere.dao.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="types")
public class Type implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer TypeId;
	private String TypeName;
	private String Description;
	
	@OneToMany(mappedBy="Type", cascade = CascadeType.ALL)
	private List<Room> Rooms;
	
	
	public Type() {
		super();
	}


	public Integer getTypeId() {
		return TypeId;
	}


	public void setTypeId(Integer typeId) {
		TypeId = typeId;
	}


	public String getTypeName() {
		return TypeName;
	}


	public void setTypeName(String typeName) {
		TypeName = typeName;
	}


	public String getDescription() {
		return Description;
	}


	public void setDescription(String description) {
		Description = description;
	}


	public List<Room> getRooms() {
		return Rooms;
	}


	public void setRooms(List<Room> rooms) {
		Rooms = rooms;
	}
	
	
}
