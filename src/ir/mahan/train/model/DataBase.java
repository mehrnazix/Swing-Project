package ir.mahan.train.model;


import ir.mahan.train.controller.Controller;

import java.io.File;
import java.io.FileInputStream;
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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.swing.JOptionPane;



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
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		Person[] persons = people.toArray(new Person[people.size()]);
		oos.writeObject(persons);
		oos.close();

	}

	public List<Person> loadFromFile(File file) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Person[] p = (Person[]) ois.readObject();
		people.clear();
		people.addAll(Arrays.asList(p));
		ois.close();
		return people;

	}

	public void addPerson(Person person) {
		people.add(person);
	}

	//TODO also delete from database
	public void deletePerson(int index) throws SQLException {
		int id ;
		id = people.get(index).getId();
		people.remove(index);
		String removeQuery = "delete from g2.person where id=?";
		PreparedStatement preparedStatement = con.prepareStatement(removeQuery);
		preparedStatement.setInt(1,id);
		preparedStatement.executeUpdate();
		
	}

	public void setPerson(ArrayList<Person> person) {
		this.people = person;
	}

	public List<Person> getPeopleList() {
		// TODO Auto-generated method stub
		return people;
	}

	public boolean connect()  {
		
		boolean flag;
		if (con != null) {
			return true;
		}
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
		} catch (ClassNotFoundException e) {
			try {
				throw new Exception("Driver not found");
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e.getMessage(),
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		String connectionURL = "jdbc:sqlserver://swsql.mahanair.aero;user=sa;password=123;database=javaTraining";
		try {
			con = DriverManager.getConnection(connectionURL);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		System.out.println("connected");
		
		if (con == null){
			flag = false;
		}
		else {
			flag = true;
		}
		return flag;
	}
	
	

	public boolean checkUserExist(int id) throws SQLException {
		String getQuery = "select count(*) from g2.person where id=?";
		PreparedStatement preparedStatement = con.prepareStatement(getQuery);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			int result = resultSet.getInt(1);
			if (result == 0) {
				return true;
			}
		}
		return false;
		
	}
	
	public boolean chekLoginValidity(String username, String password) throws SQLException {
		
		
		String getData = "select * from g2.users";
		PreparedStatement preparedStatement = con.prepareStatement(getData);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while (resultSet.next()) {
			String user = resultSet.getString(2).toLowerCase();
			String pass = resultSet.getString(3).toLowerCase();
			if (user.equals(username.toLowerCase()) & pass.equals(password.toLowerCase())) {
				return true;
			}
		}
		
		return false;
		
	}
	
	public void disConnect() throws Exception {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				throw new Exception("Could not dissconnect...");
			}
		}
	}

	public void saveToDb() throws Exception {		
		
		String insertTableSql = "insert into G2.Person "
				+ "(ID, FirstName, LastName, Gender, Age, Category, City, Sport, IsEmployee, Salary)"
				+ " values (?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = con.prepareStatement(insertTableSql);

		String updateDbQuery = "UPDATE G2.Person SET FirstName='?', LastName='?' WHERE id=?";
		PreparedStatement preparedStatementUpdate = con.prepareStatement(updateDbQuery);
		
		
		for (Person p : people) {
			if (this.checkUserExist(p.getId())) {
				preparedStatement.setInt(1, p.getId());
				preparedStatement.setString(2, p.getFirstName());
				preparedStatement.setString(3, p.getLastName());
				preparedStatement.setString(4, p.getGender().name());
				preparedStatement.setString(5, p.getAge().name());
				preparedStatement.setString(6, p.getEmpCategory().name());
				preparedStatement.setString(7, p.getCity());
				preparedStatement.setString(8, p.getFavouriteSport().name());
				preparedStatement.setBoolean(9, p.getIsEmployee());
				preparedStatement.setInt(10, p.getSalary());

				preparedStatement.executeUpdate();
			}
//			else {
//					int row =p.getId();
//					String firstName = p.getFirstName();
//					String lastName = p.getLastName();
//				editDb(row, firstName, lastName);
//				preparedStatementUpdate.setString(1, p.getFirstName());
//				preparedStatementUpdate.setString(2, p.getLastName());
//				preparedStatementUpdate.setInt(3, p.getId());
//				preparedStatementUpdate.executeUpdate();
//			}


		}
		preparedStatement.close();

	}
	
	public List<Person> loadFromDb() throws SQLException {
		
		String loadData = "select * from g2.Person order by ID";
		PreparedStatement preparedStatement = con.prepareStatement(loadData);
		ResultSet resultSet = preparedStatement.executeQuery();
		people.clear();
		while (resultSet.next()) {
			int id = resultSet.getInt(1);
			String firstName = resultSet.getString(2);
			String lastName = resultSet.getString(3);
			String gender = resultSet.getString(4);
			String ageCat = resultSet.getString(5);
			String empCategory = resultSet.getString(6);
			String city = resultSet.getString(7);
			String favouriteSport = resultSet.getString(8);
			boolean isEmployee = resultSet.getBoolean(9);
			int salary = resultSet.getInt(10);
			
			Person person = new Person(id,firstName,lastName,EmpCategory.valueOf(empCategory),AgeCategory.valueOf(ageCat)
					,Gender.valueOf(gender),city,FavouriteSport.valueOf(favouriteSport),isEmployee, salary);
				
			people.add(person);
		}
		return people;
	}

	public void editDb(int row , String firstName , String lastName) throws SQLException {
		
		int id = people.get(row).getId();
		String editQuery = "UPDATE G2.Person SET FirstName='"+firstName+" ', LastName='" + lastName+"' WHERE id=" +id+";";
		PreparedStatement preparedStatementEdit = con.prepareStatement(editQuery);
		preparedStatementEdit.executeUpdate();
	}

}
