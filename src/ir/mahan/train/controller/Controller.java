package ir.mahan.train.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import ir.mahan.train.model.AgeCategory;
import ir.mahan.train.model.DataBase;
import ir.mahan.train.model.Person;
import ir.mahan.train.view.IformEvent;
import ir.mahan.train.view.FormEvent;

public class Controller {
	DataBase db;
	private java.util.List<Person> people;

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
