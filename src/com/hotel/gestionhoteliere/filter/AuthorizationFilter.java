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
	    String homeURI = req.getContextPath() + "/admin.xhtml";

	    boolean loggedIn = session != null && session.getAttribute("username") != null; // il existe un nom stocké dans la session
	    boolean loginRequest = req.getRequestURI().equals(loginURI); // request=loginPage
	    boolean resourceRequest = req.getRequestURI().startsWith(req.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER);

	    if(loggedIn && loginRequest) { // dejà authentifié et request=loginPage
	        res.sendRedirect(homeURI);
	    }else if(loggedIn || resourceRequest || loginRequest){
	        chain.doFilter(request, response); // continue request
	    } else {
	        res.sendRedirect(loginURI);
	    }
	}

	public void init(FilterConfig filterConfig) throws ServletException {		
	}

	
}
