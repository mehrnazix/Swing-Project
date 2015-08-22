package ir.mahan.train.view;

import ir.mahan.train.controller.Controller;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame{

	public Controller controller;
	public LoginPanel loginPanel;
	public static String user = "";
	
	
	public LoginFrame(String title) {
		super(title);
		addComponent();
		setView();
		
		loginPanel.setiLoginListener(new ILoginListener() {
			@Override
			public void userInfoEmit(String username, String password) {
				try {
					if (controller.checkUser(username, password)) {
						user += username;
						new MainFrame("My App");
						LoginFrame.this.dispose();
					} else {
						JOptionPane.showMessageDialog(LoginFrame.this,"Incorrect username or password", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(LoginFrame.this,e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
	}
	
	public void setView() {
		this.setSize(300, 170);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public void addComponent() {
		this.controller = new Controller();
		loginPanel = new LoginPanel();
		this.add(loginPanel);
		try {
			controller.connectToDb();
			loginPanel.setLblText("Connected To DataBase");
		} catch (Exception e) {
			loginPanel.setLblText("Can not connect to database");
		}
	}
}


