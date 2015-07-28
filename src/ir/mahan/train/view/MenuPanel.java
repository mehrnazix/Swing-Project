package ir.mahan.train.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class MenuPanel extends JPanel {

	private JMenuBar fileMenuBar;
	private JMenu fileMenu, windowBar, showMenu;
	private JMenuItem exportMenuItem, importMenuItem, exitMenuItem, prefsItem, showFormItem;
	private JCheckBoxMenuItem showFormCheckBoxItem;
	private IuserListener iuserListener;

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
		exitMenuItem.addActionListener(new MenuItemActionListener());
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
			else if (menuItem == importMenuItem) {
//				exportToFile();
			}
			}
		}

	}
}
