package ir.mahan.train.controller;

import ir.mahan.train.model.AgeCategory;
import ir.mahan.train.model.DataBase;
import ir.mahan.train.model.Person;
import ir.mahan.train.view.IformEvent;
import ir.mahan.train.view.FormEvent;

public class Controller {
	DataBase db;

	public Controller(DataBase db) {
		this.db = new DataBase();
	}

	public void addPerson(FormEvent formEvent) {

	}

	public void saveToDb(FormEvent formEvent) {

	}

	public void loadFromDb(FormEvent formEvent) {

	}

	public void addPerson(FormEvent formEvent) {

		Person person = convertFromEventToPerson(formEvent);
		db.addPerson(person);
	}

	private Person convertFromEventToPerson(User ev) {
	String name = ev.FirstName;
	String occuptaion = ev.Role;
	String ageCat = ev.Age;
	String empCat = ev.Role;
	
	switch (ageCat) {
	case 0:
		AgeCategory = AgeCategory.child
		
		break;

	default:
		break;
	}

	public void connect() {
		try {
			db.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
