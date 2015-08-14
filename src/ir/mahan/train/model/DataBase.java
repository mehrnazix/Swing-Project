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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;



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
		String connectionURL = "jdbc:sqlserver://swsql.mahanair.aero;user=sa;password=123;database=javaTraining";
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

	public void save() throws Exception {
		this.connect();
		//		String SQLcheckCommand = "select count(*) as count from person where id=?";
//		String getQuery = "select * from g2.person where id=?";
		String insertTableSql = "insert into G2.Person "
				+ "(ID, FirstName, LastName, Gender, Age, Category, City, Sport, IsEmployee, Salary)"
				+ " values (?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = con.prepareStatement(insertTableSql);
//		preparedStatement.setInt(1, 1);
		
//		ResultSet resultSet = preparedStatement.executeQuery();
//		
//		while (resultSet.next()) {
//			System.out.println(resultSet.getString(1));
//		}
		for (Person p : people) {
//			
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

//			checkstm.setInt(3, id);
//			ResultSet checkResult = checkstm.executeQuery();
//			checkResult.next();
//			int count = checkResult.getInt(1);
		}
		con.close();
		preparedStatement.close();

	}
	
	public List<Person> load() throws Exception {
		this.connect();
		
//		String loadData = "select * from g2.person where id=? and firstname=? and lastname=? and gender=?"
//				+ "and age=? and category=? and city=? and sport=? and isemployee=? and salary=?";
		
		String loadData = "select * from g2.person";
		PreparedStatement preparedStatement = con.prepareStatement(loadData);
//		preparedStatement.setInt(1,1);
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
			
//			System.out.println(firstName);
			Person person = new Person(id,firstName,lastName,EmpCategory.valueOf(empCategory),AgeCategory.valueOf(ageCat)
					,Gender.valueOf(gender),city,FavouriteSport.valueOf(favouriteSport),isEmployee, salary);
			
			people.add(person);
			
		}
		
		return people;
		
	}

//	 public static void main(String[] args) {
//	
//	 DataBase db = new DataBase();
//	 try {
//	 db.connect();
//	 db.save();
//	 } catch (Exception e) {
//	 // TODO Auto-generated catch block
//	 e.printStackTrace();
//	 }
//	
//	 }

}
