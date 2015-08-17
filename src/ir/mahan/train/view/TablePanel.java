package ir.mahan.train.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TablePanel extends JPanel {

	private JTable table;
	private PersonTableModel personTableModel;
	private JPopupMenu popupMenu;
	private PersonTableListener personTableListener;
	
	public TablePanel() {
		super();
		Dimension dim = new Dimension();
		dim.width = 250;
		setPreferredSize(dim);
		setMinimumSize(dim);
		
		popupMenu = new JPopupMenu();
		personTableModel = new PersonTableModel();
		table = new JTable(personTableModel);
		
		JMenuItem removeItem = new JMenuItem("Delete row");
		popupMenu.add(removeItem);
		
		setLayout(new BorderLayout());
		add(new JScrollPane(table), BorderLayout.CENTER);
		
		
		removeItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				int row = table.getSelectedRow();
				if (personTableListener != null) {
					personTableListener.rowDeleted(row);
					personTableModel.fireTableDataChanged();			
				}
				
			}
		});
		
		//TODO refresh and save
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				table.getSelectionModel().setSelectionInterval(row, row);
//				System.out.println(row);
				if (e.getButton() == MouseEvent.BUTTON3) {
					popupMenu.show(table, e.getX(), getY());
				}
			}
		});
			
	}
	
	public void setData (List<FormEvent> db) {
		personTableModel.setData(db);
	}
	
	public void refresh(){
		personTableModel.fireTableDataChanged();
	}


	public void setPersonTableListener(PersonTableListener personTableListener) {
		this.personTableListener = personTableListener;
	}
}
