package ir.mahan.train.view;

import ir.mahan.train.model.AgeCategory;

import java.io.Serializable;

import javax.swing.JOptionPane;

public class FormEvent implements Serializable {
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

	public void setAge(String value) {
		this.age = value;
	}

	private static final long serialVersionUID = 1L;
	public FormEvent() {
		super();
		this.id = ++count;
	}

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
	private static int count = 0;

	public String ToString(String Seperator) {

		return firstName + Seperator + lastName + Seperator + role + Seperator
				+ age + Seperator + gender + Seperator + city + Seperator
				+ favouriteSport + Seperator + salary;
	}


	

}
