package ir.mahan.train.view;

import ir.mahan.train.model.AgeCategory;
import ir.mahan.train.model.EmpCategory;
import ir.mahan.train.model.Gender;
import ir.mahan.train.model.FavouriteSport;

import java.io.Serializable;

import javax.swing.JOptionPane;

public class FormEvent {
	
	
	public FormEvent() {
		this.id = ++count;
	}

	public FormEvent(String firstName, String lastName, EmpCategory empCategory,
			AgeCategory age, Gender gender, String city,
			FavouriteSport favouriteSport, boolean isEmployee, int salary) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.empCategory = empCategory;
		this.age = age;
		this.gender = gender;
		this.city = city;
		this.favouriteSport = favouriteSport;
		this.isEmployee = isEmployee;
		this.salary = salary;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public EmpCategory getEmpCategory() {
		return empCategory;
	}

	public void setEmpCategory(EmpCategory empCategory) {
		this.empCategory = empCategory;
	}

	public int getId() {
		return id;
	}

	public AgeCategory getAge() {
		return age;
	}

	public void setAge(AgeCategory age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public FavouriteSport getFavouriteSport() {
		return favouriteSport;
	}


	public void setFavouriteSport(FavouriteSport favouriteSport) {
		this.favouriteSport = favouriteSport;
	}

	public boolean isEmployee() {
		return isEmployee;
	}

	public void setEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int id;
	public String firstName;
	public String lastName;
	public EmpCategory empCategory;
	public AgeCategory age;
	public Gender gender;
	public String city;
	public FavouriteSport favouriteSport;
	public boolean isEmployee;
	public int salary;
	private static int count = 0;

	public String ToString(String Seperator) {

		return firstName + Seperator + lastName + Seperator + empCategory + Seperator
				+ age + Seperator + gender + Seperator + city + Seperator
				+ favouriteSport + Seperator + salary;
	}


	

}
