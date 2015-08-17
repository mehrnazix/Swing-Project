package ir.mahan.train.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
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
	public String username;

	
	public Controller() {
		this.db = new DataBase();
	}

	public void saveToDb() throws Exception  {
		db.saveToDb();
	}

	public List<FormEvent> loadFromDb() throws SQLException {
		people = db.loadFromDb();
		formEvents = new ArrayList<FormEvent>();
		for (Person p : people) {
			FormEvent e = convertPersonToFormEvent(p);
			formEvents.add(e);
		}
		return formEvents;
	}

	public boolean checkUser (String username, String password) throws Exception {
		if (db.chekLoginValidity(username, password)) {
			return true;
		}
		return false;
	}
	
	public void addPerson(FormEvent formEvent) {

		Person person = convertFormEventToPerson(formEvent);
		db.addPerson(person);
	}

	public void getPerson(Person person) {

		FormEvent formEvent = convertPersonToFormEvent(person);
		db.getPeopleList();
	}
	
	public void savePerson (File file) throws IOException{
		db.saveToFile(file);
	}
	
	public List<FormEvent> loadPerson (File file) throws IOException, ClassNotFoundException {
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
		boolean isEmployee = person.getIsEmployee();
		
		
		
		FormEvent formEvent = new FormEvent(id,firstName,lastName,empCategory,age,gender,city,favouriteSport,isEmployee,salary);
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
		boolean isEmployee = formEvent.getIsEmployee();
		
		Person person = new Person(id,firstName,lastName,empCategory,age,gender,city,favouriteSport,isEmployee,salary);
		return person;

	}


	public void connectToDb() throws Exception {
		db.connect();
	}
	
	public void disconnectFromDb() throws Exception {
		db.disConnect();
	}

	public void deletePerson(int row) throws SQLException {
		db.deletePerson(row);
	}

	public void editPerson(int row) throws SQLException {
		db.editDb(row);
		
	}
}
