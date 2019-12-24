package com.hotel.gestionhoteliere.service;

public class TestMail {

	public static void main(String[] args) {
		Mail email = new Mail("elyousfi.wail@gmail.com", "Confirmation de réservation", "Vous avez réservé une chambre avec succès, Veuillez contacter le service de comptablité pour payer votre facture ! \n \n Administration WAILMOAD HOTEL");
		email.sendMail();
	}

}
