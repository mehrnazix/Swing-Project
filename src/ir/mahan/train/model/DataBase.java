package ir.mahan.train.model;

import ir.mahan.train.view.FormEvent;
import ir.mahan.train.view.MainFrame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.omg.CORBA.PRIVATE_MEMBER;

public class DataBase {
	private List<Person> people;

	Connection con;
	private int port;
	private String user;
	private String pass;
	private Properties properties;
	private InputStream input;

	public DataBase() {
		this.people = new ArrayList<Person>();
		Properties properties = new Properties();

		try {
			File X = new File("DBconfig.properties");
			FileInputStream input = new FileInputStream(X);
			properties.load(input);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void saveToFile(File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file + ".per");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		Person[] persons = people.toArray(new Person[people.size()]);
		oos.writeObject(persons);
		oos.close();

	}

	public List<Person> loadFromFile(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);

		try {

			Person[] p = (Person[]) ois.readObject();

			people.clear();

			people.addAll(Arrays.asList(p));

			ois.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return people;

	}

	public void addPerson(Person person) {
		people.add(person);
	}

	public void deletePerson(int index) {
		people.remove(index);
	}

	public void setPerson(ArrayList<Person> person) {
		this.people = person;
	}

	public List<Person> getPeopleList() {
		// TODO Auto-generated method stub
		return people;
	}

	public void connect() throws Exception {
		if (con != null) {
			return;
		}
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new Exception("Driver not found");
		}
		String connectionURL = "jdbc:sqlserver://swsql.mahanair.aero;user=sa;password=123;database=c005_s01_n";
		con = DriverManager.getConnection(connectionURL);
		System.out.println("connected");
	}

	public void disConnect() throws Exception {
		if (con != null) {
			try {
				con.close();
				System.out.println("disconnected");
			} catch (SQLException e) {
				throw new Exception("Could not dissconnect...");
			}
		}
	}

	public void save() throws SQLException {
		String SQLcheckCommand = "select count(*) as count from person where id=?";

		PreparedStatement checkstm = con.prepareStatement(SQLcheckCommand);

		for (Person p : people) {

			int id = p.getId();
			String name = p.getFirstName();

			checkstm.setInt(3, id);
			ResultSet checkResult = checkstm.executeQuery();
			checkResult.next();
			int count = checkResult.getInt(1);
		}

	}

	// public static void main(String[] args) {
	//
	// DataBase db = new DataBase();
	// try {
	// db.connect();
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }

}
