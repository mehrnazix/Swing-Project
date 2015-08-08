package ir.mahan.train.model;

import java.io.Serializable;

public class Person implements Serializable {

	public Person(String firstName, String lastName, String role, String age,
			String gender, String city, String favouriteSport) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.age = age;
		this.gender = gender;
		this.city = city;
		this.favouriteSport = favouriteSport;
		this.isEmployee = isEmployee;
		this.salary = salary;
	}

	private static final long serialVersionUID = 1L;
	private static int count = 0;
	public int id;
	public String firstName;
	public String lastName;
	public String role;
	public String age;
	public String gender;
	public String city;
	public String favouriteSport;
	public boolean isEmployee;
	public int salary;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getFavouriteSport() {
		return favouriteSport;
	}
	public void setFavouriteSport(String favouriteSport) {
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

}
