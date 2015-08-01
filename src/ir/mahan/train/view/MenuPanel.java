package ir.mahan.train.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MenuPanel extends JPanel {

	private JMenuBar fileMenuBar;
	private JMenu fileMenu, windowBar, showMenu;
	private JMenuItem exportMenuItem, importMenuItem, exitMenuItem, prefsItem, showFormItem;
	private JCheckBoxMenuItem showFormCheckBoxItem;
	private IuserListener iuserListener;
	private JFileChooser filechooser;
	private FileNameExtensionFilter filenameFilter;
	
	public void setIstringListener(IuserListener iuserListener) {
		this.iuserListener = iuserListener;
	}

	public MenuPanel() {

		Dimension dim = getPreferredSize();
		dim.height = 35;
		setPreferredSize(dim);
		setBorder(BorderFactory.createEtchedBorder());
		fileMenuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		exportMenuItem = new JMenuItem("Export data...");
		importMenuItem = new JMenuItem("Import data...");
		exitMenuItem = new JMenuItem("Exit");
		MenuItemActionListener menuItemActionListener = new MenuItemActionListener();
		exitMenuItem.addActionListener(menuItemActionListener);
		exportMenuItem.addActionListener(menuItemActionListener);
		fileMenu.add(exportMenuItem);
		fileMenu.add(importMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(exitMenuItem);
		fileMenuBar.add(fileMenu);
		add(fileMenuBar);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		prefsItem = new JMenuItem("Preferences");
		showMenu = new JMenu("SHOW");
		showFormItem = new JMenuItem("Person Form");
		showFormCheckBoxItem = new JCheckBoxMenuItem("Show the form");
		showMenu.add(prefsItem);
		showMenu.add(showFormItem);
		showFormCheckBoxItem.setSelected(true);
		windowBar = new JMenu("Window");
		
		filechooser = new JFileChooser();
		
		
		
		fileMenuBar.add(windowBar);
		windowBar.add(showMenu);
		
		fileMenu.setMnemonic(KeyEvent.VK_F);
		exitMenuItem.setMnemonic(KeyEvent.VK_X);
		
		prefsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P , ActionEvent.CTRL_MASK));
		exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X , ActionEvent.CTRL_MASK));
		importMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I , ActionEvent.CTRL_MASK));
		exportMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S , ActionEvent.CTRL_MASK));
	}

	private class MenuItemActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			JMenuItem menuItem = (JMenuItem) event.getSource();
			if (menuItem == exitMenuItem) {
				System.exit(0);
			}
			else if (menuItem == exportMenuItem) {
				
				int returnValue = filechooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					filechooser.setFileFilter(new PersonFileFilter());
					File select = filechooser.getSelectedFile();
				}
			}
			}
		}

	}

