package ir.mahan.train.view;

import java.io.Serializable;

import javax.swing.JOptionPane;

public class FormEvent implements Serializable {
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
