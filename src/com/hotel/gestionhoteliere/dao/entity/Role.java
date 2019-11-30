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
@Table(name="roles")
public class Role implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer RoleId;
	private String RoleName;
	private String Description;
	
	@OneToMany(mappedBy="Role", cascade = CascadeType.ALL)
	private List<User> Users;
	
	
	
	public Role() {
		super();
	}



	public Integer getRoleId() {
		return RoleId;
	}



	public void setRoleId(Integer roleId) {
		RoleId = roleId;
	}



	public String getRoleName() {
		return RoleName;
	}



	public void setRoleName(String roleName) {
		RoleName = roleName;
	}



	public String getDescription() {
		return Description;
	}



	public void setDescription(String description) {
		Description = description;
	}



	public List<User> getUsers() {
		return Users;
	}



	public void setUsers(List<User> users) {
		Users = users;
	}



}	

