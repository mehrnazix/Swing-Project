package ir.mahan.model.train.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import ir.mahan.train.model.DataBase;
import ir.mahan.train.model.Person;

import org.junit.Before;
import org.junit.Test;

public class DataBaseTest {

	DataBase db;
	@Before
	public void SetUp(){
		db = new DataBase();
	}
	

//	@Test
//	public void setList () {
//		ArrayList<Person> person = new ArrayList<Person>();
//		db.setPerson(person);
//	}
	
	@Test
	public void inisilizeListMustBeEmpty() {
		List<Person> list = db.getPeopleList();
		assertEquals(0, list.size());
		
	}
	
	
}
