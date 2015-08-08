package ir.mahan.train.view;

import ir.mahan.train.controller.Controller;
import ir.mahan.train.model.DataBase;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

public class MainFrame extends JFrame {

	public TextPanel textPanel;
	public FormPanel formPanel;
	private JTabbedPane tabbedpane;
	private JSplitPane splitPane;
	private TablePanel tablePanel;
	private List<FormEvent> dbForm;
	private JMenuBar fileMenuBar;
	private JMenu fileMenu, windowBar, showMenu;
	private JMenuItem exportMenuItem, importMenuItem, exitMenuItem, prefsItem,
			showFormItem;
	private JCheckBoxMenuItem showFormCheckBoxItem;
	private JFileChooser filechooser;
	private ToolBarPanel toolbarPanel;
	private Controller controller;
	

	public MainFrame(String title) {
		super(title);
		addMenuBar();
		setView();
		addComponent();
	}

	private void addMenuBar() {
		fileMenuBar = new JMenuBar();
		fileMenu = new JMenu("File");

		exportMenuItem = new JMenuItem("Export data...");
		importMenuItem = new JMenuItem("Import data...");
		exitMenuItem = new JMenuItem("Exit");

		MenuItemActionListener menuItemActionListener = new MenuItemActionListener();
		exitMenuItem.addActionListener(menuItemActionListener);
		exportMenuItem.addActionListener(menuItemActionListener);
		importMenuItem.addActionListener(menuItemActionListener);

		fileMenu.add(exportMenuItem);
		fileMenu.add(importMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(exitMenuItem);
		fileMenuBar.add(fileMenu);

		prefsItem = new JMenuItem("Preferences");
		showMenu = new JMenu("SHOW");
		showFormItem = new JMenuItem("Person Form");
		showFormCheckBoxItem = new JCheckBoxMenuItem("Show the form");
		showMenu.add(prefsItem);
		showMenu.add(showFormItem);
		showFormCheckBoxItem.setSelected(true);

		windowBar = new JMenu("Window");

		fileMenuBar.add(windowBar);
		windowBar.add(showMenu);

		fileMenu.setMnemonic(KeyEvent.VK_F);
		exitMenuItem.setMnemonic(KeyEvent.VK_X);

		prefsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				ActionEvent.CTRL_MASK));
		exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.CTRL_MASK));
		importMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
				ActionEvent.CTRL_MASK));
		exportMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK));

	}

	private void addComponent() {
		textPanel = new TextPanel();
		tablePanel = new TablePanel();
		dbForm = new ArrayList<FormEvent>();
		tablePanel.setData(dbForm);
		formPanel = new FormPanel();
		toolbarPanel = new ToolBarPanel();
		
		formPanel.setIstringListener(new IformEvent<FormEvent>() {
			public void formEventEmitted(FormEvent formEvent) {
				if (Validation.userValidation(formEvent)) {
					textPanel.setText(formEvent.ToString("::"));
					dbForm.add(formEvent);
					tablePanel.refresh();
				} else {
					JOptionPane optionPane = new JOptionPane();
					optionPane.showMessageDialog(null,
							"Firstname or Lastname can not be empty!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		});
		
//		controller = new Controller(null);
		toolbarPanel.setToolbarListener(new ItoolbarListener() {
			
			@Override
			public void saveEventOccured() {
			
			}
			
			@Override
			public void refreshEventOccured() {
				// TODO Auto-generated method stub
				
			}
		});
		tabbedpane = new JTabbedPane();
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, formPanel,
				tabbedpane);
		tabbedpane.add("Text Area", textPanel);
		tabbedpane.add("Person DB", tablePanel);
		splitPane.setOneTouchExpandable(true);
		this.add(splitPane, BorderLayout.EAST);
		this.add(fileMenuBar, BorderLayout.NORTH);
		this.add(toolbarPanel, BorderLayout.SOUTH);
	}

	private void setView() {
		this.setSize(720, 500);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private class MenuItemActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			JMenuItem menuItem = (JMenuItem) event.getSource();
			if (menuItem == exitMenuItem) {
				System.exit(0);
			} else if (menuItem == exportMenuItem) {
				 FileManager fileManager = new FileManager();
//
//				 try {
//				 fileManager.exportToFile(dbForm);
//				 } catch (IOException e) {
//				 e.printStackTrace();
//				 }
				if (filechooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					File getSelectedFile = filechooser.getSelectedFile();
					
				}
				DataBase db = new DataBase();
				db.saveToFile(dbForm);
			} else if (menuItem == importMenuItem) {
				FileManager fileManager = new FileManager();
				try {
					List<FormEvent> userList = fileManager.importFromFile();
					if (userList != null) {
						for (FormEvent u : userList) {
							textPanel.setText(u.ToString("/"));
							dbForm.add(u);
						}
						tablePanel.refresh();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
}
