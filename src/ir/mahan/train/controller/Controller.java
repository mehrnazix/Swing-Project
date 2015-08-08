package ir.mahan.train.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import ir.mahan.train.model.AgeCategory;
import ir.mahan.train.model.DataBase;
import ir.mahan.train.model.Person;
import ir.mahan.train.view.FormEvent;

public class Controller {
	DataBase db;
	private List<Person> people;

	public Controller(DataBase db) {
		this.db = new DataBase();
	}

	public void saveToDb(FormEvent formEvent) {

	}

	public void loadFromDb(FormEvent formEvent) {

	}

	public void addPerson(FormEvent formEvent) {

		Person person = convertFromEventToPerson(formEvent);
		db.addPerson(person);
	}

	private Person convertFromEventToPerson(FormEvent formEvent) {
	String firstName = formEvent.firstName;
	String lastName = formEvent.lastName;
	String ageCat = formEvent.age;
	String role = formEvent.role;
	return null;
	
//	switch (ageCat) {
//	case 0:
//		ageCat = AgeCategory._18.toString();
//		break;
//
//	default:
//		break;
//	}
		
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
