package com.hotel.gestionhoteliere.presentation;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;

import com.hotel.gestionhoteliere.generateData.Md5;
import com.hotel.gestionhoteliere.service.LoginDAO;
import com.hotel.gestionhoteliere.service.SessionUtils;



@ManagedBean
@SessionScoped
public class Login implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;
	
	private String user;
	private String pwd;
	private String msg;

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	//validate login
	public String validateUsernamePassword() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        String pwd_crypted= Md5.getMD5(pwd);
		boolean valid = LoginDAO.validate(user, pwd_crypted);
		if (valid) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", user);
			context.getExternalContext().redirect("admin.xhtml");
			return "admin";
		} else {
			context.addMessage(null, new FacesMessage("Authentication Failed. Check username or password."));
			return "login";
		}
	}

	//logout event, invalidate session
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
	}
}
