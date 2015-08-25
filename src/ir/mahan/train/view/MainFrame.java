package ir.mahan.train.view;

import ir.mahan.train.controller.Controller;
import ir.mahan.train.model.DataBase;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
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
	private List<FormEvent> fe;

	public MainFrame(String title) {
		super(title);
		addMenuBar();
		setView();
		addComponent();
		this.controller = new Controller();
		try {
			controller.connectToDb();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(MainFrame.this, e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private JMenuBar addMenuBar() {
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
		prefsItem = new JMenuItem("Preferences");
		showMenu = new JMenu("SHOW");

		showFormCheckBoxItem = new JCheckBoxMenuItem("Show the form");
		showFormCheckBoxItem.isSelected();
		showMenu.add(prefsItem);

		showMenu.add(showFormCheckBoxItem);
		showFormCheckBoxItem.setSelected(true);

		
		windowBar = new JMenu("Window");
		windowBar.add(showMenu);

		fileMenuBar.add(fileMenu);
		fileMenuBar.add(windowBar);

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

		return fileMenuBar;
	}

	private void refreshData() {
		try {
			dbForm.clear();
			fe = new ArrayList<>();
			fe = controller.loadFromDb();
			for (FormEvent formEvent : fe) {
				textPanel.setText(formEvent.ToString(";"));
				dbForm.add(formEvent);
				FormEvent.count = formEvent.getId();
			}
			tablePanel.refresh();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(MainFrame.this,
					e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(MainFrame.this,
					e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void addComponent() {
		textPanel = new TextPanel();
		tablePanel = new TablePanel();
		dbForm = new ArrayList<FormEvent>();
		tablePanel.setData(dbForm);
		formPanel = new FormPanel();

		

		
		tablePanel.setPersonTableListener(new PersonTableListener() {

			@Override
			public void rowDeleted(int row) {
				try {
					controller.deletePerson(row);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}
				dbForm.remove(row);

			}


			
			@Override
			public void refresh() {
				refreshData();
			}

			@Override
			public void save(int row) {
				
				try {
					controller.editPerson(row);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		toolbarPanel = new ToolBarPanel();

		formPanel.setformEventListener(new IformEvent<FormEvent>() {
			public void formEventEmitted(FormEvent formEvent) {
				if (Validation.userValidation(formEvent)) {
					textPanel.setText(formEvent.ToString("::"));
					dbForm.add(formEvent);
					tablePanel.refresh();
					controller.addPerson(formEvent);
				} else {
					JOptionPane.showMessageDialog(null,
							"Firstname or Lastname can not be empty!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		});

		toolbarPanel.setToolbarListener(new ItoolbarListener() {
			@Override
			public void refreshEventOccured() {
				refreshData();
			}

			@Override
			public void saveEventOccured() {

				try {
					controller.connectToDb();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(MainFrame.this,
							e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				try {
					controller.saveToDb(dbForm);
					JOptionPane.showMessageDialog(MainFrame.this,
							"Successfuly saved to database", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
					e.printStackTrace();
//					JOptionPane.showMessageDialog(MainFrame.this,
//							"Can not save to DB", "Error", JOptionPane.ERROR_MESSAGE);
				}

			}

		});
		tabbedpane = new JTabbedPane();
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, formPanel,
				tabbedpane);
		// tabbedpane.add("Text Area", textPanel);
		tabbedpane.add("Person DB", tablePanel);
		splitPane.setOneTouchExpandable(true);
		this.add(splitPane, BorderLayout.CENTER);
		setJMenuBar(addMenuBar());

		this.add(toolbarPanel, BorderLayout.PAGE_START);
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
			filechooser = new JFileChooser();
			filechooser.setAcceptAllFileFilterUsed(false);
			PersonFileFilter personFileFilter = new PersonFileFilter();
			filechooser.addChoosableFileFilter(personFileFilter);
			JMenuItem menuItem = (JMenuItem) event.getSource();
			if (menuItem == exitMenuItem) {
				System.exit(0);
			} else if (menuItem == exportMenuItem) {
				if (filechooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					File getSelectedFile = filechooser.getSelectedFile();
					try {
						controller.savePerson(getSelectedFile);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(MainFrame.this,
								"File can not be save in this location",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			} else if (menuItem == importMenuItem) {

				if (filechooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					File getSelectedFile = filechooser.getSelectedFile();

					fe = new ArrayList();
					try {
						fe = controller.loadPerson(getSelectedFile);
					} catch (ClassNotFoundException e) {
						JOptionPane.showMessageDialog(MainFrame.this,
								e.getMessage(), "Error",
								JOptionPane.ERROR_MESSAGE);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(MainFrame.this,
								e.getMessage(), "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					// FormEvent.count = fe.size();
					for (FormEvent f : fe) {
						textPanel.setText(f.ToString("/"));
						dbForm.add(f);
					}
					tablePanel.refresh();

				}

			}

		}
	}
}
