package ir.mahan.train.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TablePanel extends JPanel {

	private JTable table;
	private PersonTableModel personTableModel;
	private JPopupMenu popupMenu;
	
	public TablePanel() {
		super();
		Dimension dim = new Dimension();
		dim.width = 250;
		setPreferredSize(dim);
		setMinimumSize(dim);
		
		popupMenu = new JPopupMenu();
		personTableModel = new PersonTableModel();
		table = new JTable(personTableModel);
		
		setLayout(new BorderLayout());
		add(new JScrollPane(table), BorderLayout.CENTER);
			
	}
	
	public void setData (List<FormEvent> db) {
		personTableModel.setData(db);
	}
	
	public void refresh(){
		personTableModel.fireTableDataChanged();
	}
}
