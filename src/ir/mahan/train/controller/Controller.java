package ir.mahan.train.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import ir.mahan.train.model.AgeCategory;
import ir.mahan.train.model.DataBase;
import ir.mahan.train.model.EmpCategory;
import ir.mahan.train.model.FavouriteSport;
import ir.mahan.train.model.Gender;
import ir.mahan.train.model.Person;
import ir.mahan.train.view.FormEvent;

public class Controller {
	DataBase db;
	private List<Person> people;
	private List<FormEvent> formEvents;

	
	public Controller() {
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

	public void getPerson(Person person) {

		FormEvent formEvent = convertPersonToFormEvent(person);
		db.getPeopleList();
	}
	
	public void savePerson (File file) throws IOException {
		db.saveToFile(file);
	}
	
	public List<FormEvent> loadPerson (File file) throws IOException {
		people = db.loadFromFile(file);
		formEvents = new ArrayList<FormEvent>();
		for (Person p : people) {
			FormEvent e = convertPersonToFormEvent(p);
			formEvents.add(e);
		}
		return formEvents;
	}
	
	private FormEvent convertPersonToFormEvent (Person person) {
		int id = person.getId();
		String firstName = person.getFirstName();
		String lastName = person.getLastName();
		EmpCategory empCategory = person.getEmpCategory();
		AgeCategory age = person.getAge();
		Gender gender = person.getGender();
		String city = person.getCity();
		FavouriteSport favouriteSport = person.getFavouriteSport();
		int salary = person.getSalary();
		
		
		FormEvent formEvent = new FormEvent(firstName,lastName,empCategory,age,gender,city,favouriteSport,true,salary);
		return formEvent;
	}
	
	private Person convertFormEventToPerson(FormEvent formEvent) {

		int id = formEvent.getId();
		String firstName = formEvent.getFirstName();
		String lastName = formEvent.getLastName();
		EmpCategory empCategory = formEvent.getEmpCategory();
		AgeCategory age = formEvent.getAge();
		Gender gender = formEvent.getGender();
		String city = formEvent.getCity();
		FavouriteSport favouriteSport = formEvent.getFavouriteSport();
		int salary = formEvent.getSalary();
		
		
		Person person = new Person(id,firstName,lastName,empCategory,age,gender,city,favouriteSport,true,salary);
		return person;

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
