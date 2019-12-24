package com.hotel.gestionhoteliere.service;

import java.util.Properties;

import javax.faces.bean.ManagedBean;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@ManagedBean(name="emailBean")
public class Mail {

	final String senderEmailID = "wailmoadhotel@gmail.com";
	final String senderPassword = "wailmoadhotel123456";
	final String emailSMTPserver = "smtp.gmail.com";
	final String emailServerPort = "465";
	
	private String receiverEmailID ;
	
	private String emailSubject;
	
	private String emailBody ;
	
	public Mail(String email, String subject, String body) {
		receiverEmailID=email;
		emailSubject=subject;
		emailBody=body;
	}
	
	public void sendMail(){
	   
	
	  Properties props = new Properties();
	  props.put("mail.smtp.user",senderEmailID);
	  props.put("mail.smtp.host", emailSMTPserver);
	  props.put("mail.smtp.port", emailServerPort);
	  props.put("mail.smtp.starttls.enable", "true");
	  props.put("mail.smtp.auth", "true");
	  props.put("mail.smtp.socketFactory.port", emailServerPort);
	  props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	  props.put("mail.smtp.socketFactory.fallback", "false");
	   try{  
	  Authenticator auth = new SMTPAuthenticator();
	  Session session = Session.getInstance(props, auth);
	  MimeMessage msg = new MimeMessage(session);
	  msg.setText(emailBody);
	  msg.setSubject(emailSubject);
	  msg.setFrom(new InternetAddress(senderEmailID));
	  msg.setRecipient(Message.RecipientType.TO,new InternetAddress(receiverEmailID));
	  Transport.send(msg);
	  System.out.println("Message send Successfully:)"); }
	  
	  catch (Exception mex){
	  mex.printStackTrace();}
	  
	  
	  }
	
	
	
	  public class SMTPAuthenticator extends javax.mail.Authenticator
	  {
		  
	  public SMTPAuthenticator() {
			super();
			// TODO Auto-generated constructor stub
		}

	public PasswordAuthentication getPasswordAuthentication()
	  {
	  return new PasswordAuthentication(senderEmailID, senderPassword);
	  }
	  }
	     }