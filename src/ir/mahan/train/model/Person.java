package ir.mahan.train.model;

import java.io.Serializable;

public class Person implements Serializable {

	
	public Person(int id, String firstName, String lastName,
			EmpCategory empCategory, AgeCategory age, Gender gender,
			String city, FavouriteSport favouriteSport, boolean isEmployee,
			int salary) {
		super();
		this.id = id;
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
	
	private static final long serialVersionUID = 1L;
	private static int count = 0;
	public int id;
	public String firstName;
	public String lastName;
	public EmpCategory empCategory;
	public AgeCategory age;
	public Gender gender;
	public String city;
	public FavouriteSport favouriteSport;
	public boolean isEmployee;
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
	public EmpCategory getEmpCategory() {
		return empCategory;
	}
	public void setEmpCategory(EmpCategory empCategory) {
		this.empCategory = empCategory;
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
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int salary;
	


}
