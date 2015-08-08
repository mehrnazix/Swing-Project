package ir.mahan.train.model;

import ir.mahan.train.view.FormEvent;
import ir.mahan.train.view.MainFrame;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.SwingUtilities;

import org.omg.CORBA.PRIVATE_MEMBER;

public class DataBase {
	private ArrayList<Person> person;

	Connection con;
	private int port;
	private String user;
	private String pass;
	private Properties properties;
	private InputStream input;
	
	public DataBase() {
		
		
		this.person = new ArrayList<Person>();
	}
	
	public void setPerson(ArrayList<Person> person) {
		this.person = person;
	}
	
	public List<Person> getPeopleList() {
		// TODO Auto-generated method stub
		return person;
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
		
		
		for (Person p : person) {
			
			int id = p.getId(); 
			String name = p.getFirstName();
			
			checkstm.setInt(3, id);
			ResultSet checkResult = checkstm.executeQuery();
			checkResult.next();
			int count = checkResult.getInt(1);
		}
		
	}

	public static void main(String[] args) {

		DataBase db  = new DataBase();
		try {
			db.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
