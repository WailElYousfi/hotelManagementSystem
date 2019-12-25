package com.hotel.gestionhoteliere.presentation;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hotel.gestionhoteliere.dao.entity.Reservation;
import com.hotel.gestionhoteliere.dao.entity.Role;
import com.hotel.gestionhoteliere.dao.entity.Room;
import com.hotel.gestionhoteliere.dao.entity.Type;
import com.hotel.gestionhoteliere.dao.entity.User;
import com.hotel.gestionhoteliere.generateData.Md5;

@ManagedBean(name="roomBean")
@SessionScoped
public class RoomBean implements Serializable {
	
	private static final long serialVersionUID = 8150756503956053844L;
    private SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
    
    private Room room;
    private Part file1=null;
    private Part file2=null;
    private Part file3=null;
    private Date debut;
    private Date fin;
    private List<Room> searchedRooms = new ArrayList<Room>();
    
    @PostConstruct
    public void init() {
        room = new Room();
    }
    
    public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Part getFile1() {
		return file1;
	}

	public void setFile1(Part image1) {
		this.file1 = image1;
	}

	public Part getFile2() {
		return file2;
	}

	public void setFile2(Part image2) {
		this.file2 = image2;
	}

	public Part getFile3() {
		return file3;
	}

	public void setFile3(Part image3) {
		this.file3 = image3;
	}

	public Date getDebut() {
		return debut;
	}

	public void setDebut(Date debut) {
		this.debut = debut;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public List<Room> getSearchedRooms() {
		return searchedRooms;
	}

	public void setSearchedRooms(List<Room> searchedRooms) {
		this.searchedRooms = searchedRooms;
	}
	
	

	public void upload() throws IOException {
		if(file1!=null)
			file1.write("C:\\Users\\Wail\\eclipse-workspace\\GestionHoteliere\\WebContent\\resources\\images\\"+getFilename(file1));
		if(file2!=null)
			file2.write("C:\\Users\\Wail\\eclipse-workspace\\GestionHoteliere\\WebContent\\resources\\images\\"+getFilename(file2));
		if(file3!=null)
			file3.write("C:\\Users\\Wail\\eclipse-workspace\\GestionHoteliere\\WebContent\\resources\\images\\"+getFilename(file3));
	}

    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }
    
    public Room getRoomById(Integer id) {
		Session session = sessionfactory.openSession();
		Room room = session.load(Room.class, id);
		session.close();
		return room;
	}
    
    public Long count() {
    	Session session = sessionfactory.openSession();
		Query query = session.createQuery("Select count(c) from Room c");
		Long nbr = (Long) query.uniqueResult();
		session.close();
		return nbr;
    }
    
      
	public List<Room> getAllRooms(){
		Session session = sessionfactory.openSession();
		Query query = session.createQuery("from Room");
		List<Room> rooms=new ArrayList<Room>();
		rooms = query.getResultList();
		session.close();
		return rooms;
	}
    
    public void addRoom() throws IOException {
		Session session = sessionfactory.openSession();
	    FacesContext context = FacesContext.getCurrentInstance();
			try {
				session.beginTransaction();
		    	upload();
				Type type = session.load(Type.class, room.getType().getTypeId());	// Récuperer le type qui a l'id envoyé par le formulaire
				room.setType(type);
				if(file1 != null) 	room.setImage(getFilename(file1));
				if(file2 != null) 	room.setImage2(getFilename(file2));
				if(file3 != null) 	room.setImage3(getFilename(file3));
				session.save(room);
				session.getTransaction().commit();
				room = new Room();
				context.getExternalContext().redirect("index.xhtml");
				session.close();
			} catch (Exception e) {
				System.out.println("Exception in addRoom method: " + e.getMessage());
			}
	}
    
    public String editRoom(Room room) {
		this.room=room;
		return "ok";
	}
 
	public void updateRoomRecord() {
		Session session = sessionfactory.openSession();
	    FacesContext context = FacesContext.getCurrentInstance();
        try {
        	session.beginTransaction();
            session.merge(this.room);        
            session.getTransaction().commit();
        	session.close();
        	room = new Room();
			context.getExternalContext().redirect("index.xhtml");
        } catch(Exception e){
            System.out.println("Exception in updateRoomRecord: " + e.getMessage());
        }
    }
	
	public void deleteRoom(Room room) {
		Session session = sessionfactory.openSession();
        try {
        	session.beginTransaction();
        	session.delete(room);
        	session.getTransaction().commit();
        	session.close();
        } catch(Exception e){
            System.out.println("Exception in deleteRoom: " + e.getMessage());
        }
	}
	
	public String searchRooms(){
		Session session = sessionfactory.openSession();
		List<Room> rooms = new ArrayList<Room>();
		Query query = session.createQuery("from Room");
		rooms = query.getResultList();
	
	    List<Room> roomsList = new ArrayList<Room>();
	    int i=0;
	    for(Room room : rooms){
			i=0;
			for(Reservation reservation : room.getReservations() ) {
				if(!reservation.getState().equals("Rejetée") && 
				( (reservation.getStartDate().compareTo(debut) <= 0 && debut.compareTo(reservation.getEndDate()) <= 0) || 
				(reservation.getStartDate().compareTo(fin) <= 0 && fin.compareTo(reservation.getEndDate()) <= 0) ||
				(debut.compareTo(reservation.getStartDate()) <= 0 && reservation.getEndDate().compareTo(fin) <= 0) ))  {
					i=1;
				}
			}
			if(i==0) roomsList.add(room);
		}
	    session.close();
	    searchedRooms = roomsList;
	    return "ok";
	}
	
	
}



