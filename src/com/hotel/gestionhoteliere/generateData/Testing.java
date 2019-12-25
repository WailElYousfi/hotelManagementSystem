package com.hotel.gestionhoteliere.generateData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hotel.gestionhoteliere.dao.entity.Reservation;
import com.hotel.gestionhoteliere.dao.entity.Room;
import com.hotel.gestionhoteliere.dao.entity.User;

public class Testing {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
	    SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
	    Session session = sessionfactory.openSession();
	    
	    /*User user=new User();
		try {
			//Query query = session.createQuery("FROM Room INNER JOIN Reservation ON Room.RoomId = Reservation.RoomId");
			Query query = session.createQuery("from User where Email= :email");
			query.setString("email", "commercial");
			user = (User) query.uniqueResult();
		    List<Reservation> reservations = new ArrayList<Reservation>();
		    int i=1;
		    for(Reservation item : user.getReservations()){
				System.out.println("l'id du reservation " + i + " est : " + item.getReservationId());
				i++;
			}

			System.out.println();
		} catch(Exception e) {
			 System.out.println("Exception in main: "+e.getMessage());
		}*/
	    /*
	    List<Room> rooms = new ArrayList<Room>();
		Query query = session.createQuery("from Room");
		rooms = query.getResultList();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //Pour déclarer les valeurs dans les nouveaux objets Date, employez le même format de date que pour créer des dates
		Date date1 = sdf.parse("2019-11-12");
		Date date2 = sdf.parse("2019-11-14");
		
	    List<Room> roomsList = new ArrayList<Room>();

		
		//les cas ou il y a des conflits
		//	start < date1 < end 
		//	start < date2 < end
	    // date1 < start && end < date2
		
		for(Room room : rooms){
			int i=0;
			for(Reservation reservation : room.getReservations() ) {
				if(!reservation.getState().equals("Rejetée") && 
				( (reservation.getStartDate().compareTo(date1) < 0 && date1.compareTo(reservation.getEndDate()) < 0) || 
				(reservation.getStartDate().compareTo(date2) < 0 && date2.compareTo(reservation.getEndDate()) < 0) ||
				(date1.compareTo(reservation.getStartDate()) < 0 && reservation.getEndDate().compareTo(date2) < 0) ))  {
					i=1;
				}
			}
			if(i==0) roomsList.add(room);
		}
		
		System.out.println("les resultats: ");
		for(Room room : roomsList) {
			System.out.println(room.getRoomId());
		}*/
	    
	    Query query = session.createQuery("select ReservationId from Reservation where State= :state and NOT IN (select Reservation.ReservationId from Payment)");
		query.setParameter("state", "Acceptée");
		List<Reservation> reservations = new ArrayList<Reservation>();
		reservations = query.getResultList();
		for(Reservation re : reservations) {
			System.out.println(re.getReservationId());
		}

	}

}
