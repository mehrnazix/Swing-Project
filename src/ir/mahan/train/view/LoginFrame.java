package ir.mahan.train.view;

import ir.mahan.train.controller.Controller;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	public JTextField passwordFd;
	public Controller controller;
	public JPasswordField pf;
	public static String user = "";
	
	public LoginFrame(String title) {
		super(title);
		
		
		setLocationRelativeTo(null);
		
		controller = new Controller();
		setView();
		addComponent();
		setLayout();
	}
	
	
	public void setView() {
		this.setSize(300, 140);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void addComponent() {
		jPanel = new JPanel();
		this.add(jPanel);
		userNamelbl = new JLabel("Username:");
		passwordlbl = new JLabel("Password:");
		userNametf = new JTextField();
		passwordFd = new JTextField();
		loginBtn = new JButton("Login");
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

		loginBtn.addActionListener(new LoginBtnClick());
	}
	

	private class LoginBtnClick implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent event) {
			JButton btn = (JButton) event.getSource();
			if (btn == loginBtn) {
				String username = userNametf.getText();
				String password = passwordFd.getText();
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
}

	
	
}


