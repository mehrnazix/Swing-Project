package ir.mahan.train.model;

import javax.swing.JOptionPane;

public class User {
	public User() {
		super();
		this.id = ++count;
	}

	public  int 		id;
	public  String			FirstName;
	public  String			LastName;
	public  String			Role;
	public	String	   		Age;
	public	String			Gender;
	public	String			City;
	public 	String 			favouriteSport;
	public 	boolean    		isEmployee;
	public 	int 			salary;

	public String ToString(String Seperator)
	{
		
		return FirstName + Seperator + LastName + Seperator + Role + Seperator + Age + Seperator + Gender + Seperator + City + Seperator + favouriteSport + Seperator + salary; 
	}
	
	private static int count = 0;
	
	
	public boolean IsValid(boolean showMessage)
	{
		String message = "";
		Boolean result = true;
		
		if (FirstName.isEmpty()) {
			result = false;
			message += "First name is required.\n";
		}
		
		if (LastName.isEmpty()) {
			result = false;
			message += "Last name is required.\n";
		}		
		
		if (showMessage && message != "") {
			JOptionPane.showMessageDialog (null, message, "User Validation", JOptionPane.ERROR_MESSAGE);
		}
		
		return result;
	}
}
