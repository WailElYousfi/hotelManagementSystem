package com.hotel.gestionhoteliere.filter;

import java.io.IOException;

import javax.faces.application.ResourceHandler;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hotel.gestionhoteliere.dao.entity.User;

@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class AuthorizationFilter implements Filter {

	public AuthorizationFilter() {
	}

	public void destroy() {		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
	    HttpServletResponse res = (HttpServletResponse) response;
	    HttpSession session = req.getSession(false);

	    
	    String loginURI = req.getContextPath() + "/login.xhtml";
	    String adminHomeURI = req.getContextPath() + "/admin.xhtml";
	    String commercialHomeURI = req.getContextPath() + "/commercial.xhtml";
	    String comptableHomeURI = req.getContextPath() + "/comptable.xhtml";
	    

	    boolean loggedIn = session != null && session.getAttribute("currentUser") != null; // il existe un nom stocké dans la session
	    boolean loginRequest = req.getRequestURI().equals(loginURI); // request=loginPage
	    boolean resourceRequest = req.getRequestURI().startsWith(req.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER);

	    
	    if(req.getRequestURI().startsWith(req.getContextPath() + "/client/") || req.getRequestURI().equals("/GestionHoteliere/")) { //les pages de client et la page de demarrage de l'application
	        chain.doFilter(request, response); // continue request
	    }else {
	    	if(loggedIn && loginRequest) { // dejà authentifié et request=loginPage
	    		User currentUser = (User) session.getAttribute("currentUser");
		    	if(currentUser.getRole().getRoleName().equals("Admin"))		
		    		res.sendRedirect(adminHomeURI);
		    	else if(currentUser.getRole().getRoleName().equals("Commercial"))		
		    		res.sendRedirect(commercialHomeURI);
		    	else	
		    		res.sendRedirect(comptableHomeURI);
		    	
		    }else if(loggedIn || resourceRequest || loginRequest){
		        chain.doFilter(request, response); // continue request
		    } else {
		        res.sendRedirect(loginURI);
		    }
	    }
	    
	}

	public void init(FilterConfig filterConfig) throws ServletException {		
	}

	
}
