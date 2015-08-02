package ir.mahan.train.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class PersonTableModel extends AbstractTableModel {

	private List<User> db;
	private String[] colname = {"Id","FirstName", "LastName", "Role", "Age", "Gender", "City"};
	
	@Override
	public String getColumnName (int column){
		return colname[column];
	}
	
	public void setData (List<User> db) {
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
		User user = db.get(row);
		switch (col) {
		case 0:
			return user.id;
		case 1:
			return user.firstName;
		case 2: 
			return user.lastName;
		case 3:
			return user.role;
		case 4: 
			return user.age;
		case 5:
			return user.gender;
		case 6:
			return user.city;
		}
		return null;
	}

}
