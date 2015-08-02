package ir.mahan.train.model;

import java.io.Serializable;

public class Person implements Serializable {

	public Person(String firstName, String lastName, String role, String age,
			String gender, String city, String favouriteSport,
			boolean isEmployee, int salary) {
		super();
		firstName = firstName;
		lastName = lastName;
		role = role;
		age = age;
		gender = gender;
		city = city;
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

}
