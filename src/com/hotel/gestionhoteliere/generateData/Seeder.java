package com.hotel.gestionhoteliere.generateData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.github.javafaker.Faker;
import com.hotel.gestionhoteliere.dao.entity.Client;
import com.hotel.gestionhoteliere.dao.entity.Payment;
import com.hotel.gestionhoteliere.dao.entity.Reservation;
import com.hotel.gestionhoteliere.dao.entity.Role;
import com.hotel.gestionhoteliere.dao.entity.Room;
import com.hotel.gestionhoteliere.dao.entity.Type;
import com.hotel.gestionhoteliere.dao.entity.User;

public class Seeder {
	public static void main(String[] args) throws ParseException {

	  SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
	  Session session = sessionfactory.openSession();

	  Type t1 = new Type();
	  t1.setTypeName("Chambre simple");
	  t1.setDescription("C'est une chambre pour une personne, avec un lit simple");

	  Type t2 = new Type();
	  t2.setTypeName("Chambre double");
	  t2.setDescription("C'est une chambre conçue pour 2 personnes. Elle se caractérise par la présence de deux lits simples");

	  Type t3 = new Type();
	  t3.setTypeName("Chambre triple");
	  t3.setDescription("C'est une chambre pour trois personnes, généralement avec trois lits simples");

	  Type t4 = new Type();
	  t4.setTypeName("Chambre quadruple");
	  t4.setDescription("C'est une chambre pour quatre personnes");

	  List<Type> types = new ArrayList<Type>();
	  types.add(t1);
	  types.add(t2);
	  types.add(t3);
	  types.add(t4);

	  for (Type type : types) {
		session.save(type);
	  }
	  
////////////////////////////////////////////////////////////////////////////////////////

	  Role r1 = new Role();
	  r1.setRoleName("Admin");
	  r1.setDescription("Adminitrateur de l'application");

	  Role r2 = new Role();
	  r2.setRoleName("Commercial");
	  r2.setDescription("Le responsable de service commercial, c'est lui qui vérifie les réservations des clients");

	  Role r3 = new Role();
	  r3.setRoleName("Comptable");
	  r3.setDescription("C'est lui qui paye les réservations et qui génère les factures");

	  List<Role> roles = new ArrayList<Role>();
	  roles.add(r1);
	  roles.add(r2);
	  roles.add(r3);

	  for (Role Role : roles) {
		session.save(Role);
	  }

	  ////////////////////////////////////////////////////////////////////////////////////////

	  List<Room> rooms = new ArrayList<Room>();
	  for (int i = 0; i < 70; i++) {
		Faker faker = new Faker();
		Random rand = new Random();
		Room r = new Room();
		r.setRoomNumbere(i+1);
		r.setFloor(faker.number().numberBetween(1, 5));
		r.setSurface((long)faker.number().randomDouble(4, 20, 60));
		r.setType(types.get(rand.nextInt(types.size())));
		r.setImage(Integer.toString(faker.number().numberBetween(1, 10))+".jpg");
		rooms.add(r);
		session.save(r);
	  }

	  ////////////////////////////////////////////////////////////////////////////////////////

	  	  List<Client> clients = new ArrayList<Client>();
	  for (int i = 0; i < 40; i++) {
		Faker faker = new Faker(new Random(i));
		Client c = new Client();
		c.setFirstName(faker.name().firstName());
		c.setLastName(faker.name().lastName());
		c.setEmail(faker.internet().emailAddress());
		c.setPhone(faker.phoneNumber().cellPhone().toString());
		c.setCin("L"+faker.number().numberBetween(400000, 700000));
		clients.add(c);
		session.save(c);
	  }

////////////////////////////////////////////////////////////////////////////////////////

	  List<User> admins = new ArrayList<User>();
	  for (int i = 0; i < 2; i++) {
		Faker faker = new Faker(new Random(i+50));
		User u = new User();
		u.setFirstName(faker.name().firstName());
		u.setLastName(faker.name().lastName());
		u.setEmail(faker.internet().emailAddress());
		u.setPhone(faker.phoneNumber().cellPhone().toString());
		u.setPassword(Md5.getMD5("123456"));
		u.setRole(r1);
		admins.add(u);
		session.save(u);
	  }

	  List<User> comptables = new ArrayList<User>();
	  for (int i = 0; i < 3; i++) {
		Faker faker = new Faker(new Random(i+55));
		User u = new User();
		u.setFirstName(faker.name().firstName());
		u.setLastName(faker.name().lastName());
		u.setEmail(faker.internet().emailAddress());
		u.setPhone(faker.phoneNumber().cellPhone().toString());
		u.setPassword(Md5.getMD5("123456"));
		u.setRole(r3);
		comptables.add(u);
		session.save(u);
	  }

	  List<User> commercials = new ArrayList<User>();
	  for (int i = 0; i < 3; i++) {
		Faker faker = new Faker(new Random(i+60));
		User u = new User();
		u.setFirstName(faker.name().firstName());
		u.setLastName(faker.name().lastName());
		u.setEmail(faker.internet().emailAddress());
		u.setPhone(faker.phoneNumber().cellPhone().toString());
		u.setPassword(Md5.getMD5("123456"));
		u.setRole(r2);
		commercials.add(u);
		session.save(u);
	  }

////////////////////////////////////////////////////////////////////////////////////////

	Date currentDate = new Date();
	
	Calendar c = Calendar.getInstance();
	c.setTime(currentDate);
	c.add(Calendar.DATE, 1);// after 1 day
	c.add(Calendar.HOUR, 3);// after 3 hours
	
	Calendar c2 = Calendar.getInstance();
	c2.setTime(currentDate);
	c2.add(Calendar.DATE, 3);// after 2 days
	Date date2=new SimpleDateFormat("dd-MM-yyyy").parse(Integer.toString(c2.get(Calendar.DAY_OF_MONTH))+"-"+Integer.toString(c2.get(Calendar.MONTH))+"-"+Integer.toString(c2.get(Calendar.YEAR)));  
	
	Calendar c3 = Calendar.getInstance();
	c3.setTime(currentDate);
	c3.add(Calendar.DATE, 6);// after 6 days
	Date date3=new SimpleDateFormat("dd-MM-yyyy").parse(Integer.toString(c3.get(Calendar.DAY_OF_MONTH))+"-"+Integer.toString(c3.get(Calendar.MONTH))+"-"+Integer.toString(c3.get(Calendar.YEAR)));  

	List<Reservation> reservationsInProgress = new ArrayList<Reservation>();
	for (int i = 0; i < 15; i++) {
	Random rand = new Random();
	Reservation r = new Reservation();

	r.setReservationDate(currentDate);
	r.setAcceptanceDate(null);
	r.setStartDate(date2);
	r.setEndDate(date3);
	r.setClient(clients.get(rand.nextInt(clients.size())));
	r.setCommercial(null);
	r.setRoom(rooms.get(rand.nextInt(rooms.size())));
	r.setState("En cours de traitement");

	reservationsInProgress.add(r);
	session.save(r);
	}


	List<Reservation> reservationsAccepted = new ArrayList<Reservation>();
	for (int i = 0; i < 30; i++) {
	Faker faker = new Faker();
	Random rand = new Random();
	Reservation r = new Reservation();

	r.setReservationDate(currentDate);
	r.setAcceptanceDate(c.getTime());
	r.setStartDate(date2);
	r.setEndDate(date3);
	r.setClient(clients.get(rand.nextInt(clients.size())));
	r.setCommercial(commercials.get(rand.nextInt(commercials.size())));
	r.setPricePerNight((float)faker.number().numberBetween(100, 400));
	r.setRoom(rooms.get(rand.nextInt(rooms.size())));
	r.setState("Acceptée");

	reservationsAccepted.add(r);
	session.save(r);
	}


	List<Reservation> reservationsRejected = new ArrayList<Reservation>();
	for (int i = 0; i < 8; i++) {
	Random rand = new Random();
	Reservation r = new Reservation();

	r.setReservationDate(currentDate);
	r.setAcceptanceDate(null);
	r.setStartDate(date2);
	r.setEndDate(date3);
	r.setClient(clients.get(rand.nextInt(clients.size())));
	r.setCommercial(commercials.get(rand.nextInt(commercials.size())));
	r.setRoom(rooms.get(rand.nextInt(rooms.size())));
	r.setState("Rejetée");

	reservationsRejected.add(r);
	session.save(r);
	}

//////////////////////////////////////////////////////////////////////////////////////////

	List<String> methods = new ArrayList<String>();
	methods.add("Chèque");
	methods.add("Carte bancaire");
	methods.add("En espèce");
	c.add(Calendar.DATE, 2);// after 2 days
	Date date4 = new SimpleDateFormat("yyyyMMdd").parse(Integer.toString(c2.get(Calendar.YEAR))+Integer.toString(c2.get(Calendar.MONTH))+Integer.toString(c2.get(Calendar.DAY_OF_MONTH)));
	String date4ToString = new SimpleDateFormat("yyyyMMdd").format(date4);

	for (int i = 0; i < 20; i++) {
	Random rand = new Random();
	Payment p = new Payment();
	
	p.setComptable(comptables.get(rand.nextInt(comptables.size())));
	p.setMethodOfPayment(methods.get(rand.nextInt(methods.size())));
	p.setPaymentDate(c.getTime());
	p.setReservation(reservationsAccepted.get(rand.nextInt(reservationsAccepted.size())));
	p.setAmountPaid(3 * p.getReservation().getPricePerNight());
	p.setTransactionNumber("P"+date4ToString+p.getReservation().getReservationId());

	try{
		session.save(p);
	}catch(Exception e){
		System.out.println(e);
	}
	
	}

	try{
		session.getTransaction().commit();
	} catch(Exception e){
		System.out.println(e);
	}

      session.close();

  }
}