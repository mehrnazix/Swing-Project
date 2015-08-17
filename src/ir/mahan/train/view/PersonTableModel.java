package ir.mahan.train.view;

import ir.mahan.train.model.AgeCategory;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class PersonTableModel extends AbstractTableModel {

	private List<FormEvent> db;
	private String[] colname = {"Id","FirstName", "LastName", "Role", "Age", "Gender", "City", "Salary"};
	
	@Override
	public String getColumnName (int column){
		return colname[column];
	}
	
	public void setData (List<FormEvent> db) {
		this.db = db;
	}
	
	@Override
	public int getColumnCount() {
		return colname.length;
	}
	
	@Override
	public int getRowCount() {
		return db.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		FormEvent formEvent = db.get(row);
		switch (col) {
		case 0:
			return formEvent.id;
		case 1:
			return formEvent.firstName;
		case 2: 
			return formEvent.lastName;
		case 3:
			return formEvent.empCategory;
		case 4: 
			return formEvent.age;
		case 5:
			return formEvent.gender;
		case 6:
			return formEvent.city;
		case 7:
			return formEvent.salary;
	}
		return null;
	}
	
	@Override
	public boolean isCellEditable (int row, int col) {
		switch (col) {
		case 1:
			return true;
		case 2:
			return true;
		}
		return false;
		
	}
	
	@Override
	public void setValueAt(Object value, int row, int col){
		if (db == null) {
			return;
		}
		FormEvent p = (FormEvent)db.get(row);
		switch (col) {
		case 1:
			p.setFirstName((String)value);
		case 2:
			p.setLastName((String)value);
		}
	}

}
