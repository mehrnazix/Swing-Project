package ir.mahan.train.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import ir.mahan.train.model.AgeCategory;
import ir.mahan.train.model.DataBase;
import ir.mahan.train.model.Gender;
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

		Person person = convertFormEventToPerson(formEvent);
		db.addPerson(person);
	}

	public void savePerson (File file) throws IOException {
		db.saveToFile(file);
	}
	
	private Person convertFormEventToPerson(FormEvent e) {

		String name = e.getFirstName();
		String family = e.getLastName();
		String role = e.getRole();
		String age = e.getAge();
		String gender = e.gender;
		String city = e.city;
		String favSport = e.favouriteSport;
		
		
		Person person = new Person(name, family, role, city, gender, age,
				favSport);
		return person;

	}
	
	private FormEvent convertFromEventToPerson(Person person) {
		String name = person.getFirstName();
		String family = person.getLastName();
		String role = person.getRole();
		String age = person.getAge();
		String city = person.getCity();
		String favSport = person.getFavouriteSport();
		String gender = person.getGender();

		FormEvent e = new FormEvent();
		e.firstName = name;
		e.lastName = family;
		e.role = role;
		e.age = age;
		e.city = city;
		e.favouriteSport = favSport;
		e.gender = gender;

		return e;

		
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
