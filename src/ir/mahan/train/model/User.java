package ir.mahan.train.model;

import java.io.Serializable;

import javax.swing.JOptionPane;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	public User() {
		super();
		this.id = ++count;
	}

	public int id;
	public String FirstName;
	public String LastName;
	public String Role;
	public String Age;
	public String Gender;
	public String City;
	public String favouriteSport;
	public boolean isEmployee;
	public int salary;
	private static int count = 0;

	public String ToString(String Seperator) {

		return FirstName + Seperator + LastName + Seperator + Role + Seperator
				+ Age + Seperator + Gender + Seperator + City + Seperator
				+ favouriteSport + Seperator + salary;
	}
	

}
