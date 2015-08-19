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
	public JPanel jPanel;
	public JLabel userNamelbl,passwordlbl;
	public JTextField userNametf;
	public JButton loginBtn;
	public JPasswordField passwordFd;
	public Controller controller;
	public JPasswordField pf;
	public JLabel connectionLabel;
	public static String user = "";
	
	
	public LoginFrame(String title) {
		super(title);
		setLocationRelativeTo(null);
		controller = new Controller();
		try {
			controller.connectToDb();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(LoginFrame.this, e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		setView();
		addComponent();
		setLayout();
		try {
			if (controller.connectToDb()) {
				connectionLabel.setText("Connected To Data Base");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void setView() {
		this.setSize(300, 170);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void addComponent() {
		jPanel = new JPanel();
		this.add(jPanel);
		userNamelbl = new JLabel("Username:");
		passwordlbl = new JLabel("Password:");
		userNametf = new JTextField();
		passwordFd = new JPasswordField();
		loginBtn = new JButton("Login");
		connectionLabel = new JLabel();
		jPanel.getRootPane().setDefaultButton(loginBtn);
		
//		loginBtn.setMnemonic(KeyEvent.VK_ENTER);
	}
	
	public void setLayout() {
		jPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.insets = new Insets(5, 5, 5, 5);
		

		
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridy = 0;
		gc.gridx = 0;
		jPanel.add(userNamelbl ,gc);
		
		gc.gridy = 1;
		gc.gridx = 0;
		jPanel.add(passwordlbl, gc);
		
		gc.weightx = 2;
		gc.gridy = 0;
		gc.gridx = 1;
		jPanel.add(userNametf, gc);
				
		gc.gridy = 1;
		gc.gridx = 1;
		jPanel.add(passwordFd, gc);
		
		gc.gridy = 2;
		gc.gridx = 1;
		gc.weighty = 14;
		gc.anchor = GridBagConstraints.NORTH;
		jPanel.add(loginBtn, gc);
		
		gc.gridy = 3;
		gc.gridx = 0;
		gc.weighty = 1;
		gc.anchor = GridBagConstraints.NORTH;
		jPanel.add(connectionLabel, gc);

		loginBtn.addActionListener(new LoginBtnClick());
	}
	

	private class LoginBtnClick implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent event) {
			JButton btn = (JButton) event.getSource();
			String password = "";
			if (btn == loginBtn) {
				String username = userNametf.getText();
				char[] passwordarr = passwordFd.getPassword();
				for (int i = 0; i < passwordarr.length; i++) {
					password += passwordarr[i];
				}

				try {
					if (controller.checkUser(username, password)) {
						user += username;
						new MainFrame("User App");
						LoginFrame.this.dispose();
						
						
					} else {
						JOptionPane.showMessageDialog(LoginFrame.this, "Incorret username or password", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(LoginFrame.this, "Database Error", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
		}
}

	
	
}


