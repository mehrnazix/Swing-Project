package ir.mahan.train.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;


import com.sun.xml.internal.fastinfoset.sax.Properties;

public class DataBase {
	private List<Person> people;
	
	public DataBase() {
		
		this.people = new ArrayList<Person>();
		Properties properties = new Properties();

		try {
			File X = new File("DBconfig.properties");
			FileInputStream input = new FileInputStream(X);
//			properties.l
			
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
	
	public void loadFromFile (File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		try {
			Person[] persons = (Person[]) ois.readObject();
			people.clear();
			people.addAll(Arrays.asList(persons));
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
//		people.toArray(new Person[people.size()]);
		ois.close();
	}

	
	
	public void addPerson(Person person){
		people.add(person);
	}
	
	public void deletePerson(int index){
		people.remove(index);
	}
}
