package ir.mahan.train.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ir.mahan.train.controller.Controller;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class LoginPanel extends JPanel {
	public JPanel jPanel;
	public JLabel userNamelbl, passwordlbl;
	public JTextField userNametf;
	public JButton loginBtn;
	public JPasswordField passwordFd;
	public JPasswordField pf;
	public JLabel connectionLabel;
	public Controller controller;
	public ILoginListener iLoginListener;
	private JLabel emptyUsernameStar, emptyPasswordStar;

	public LoginPanel() {
		addComponent();
		setLayout();
	}

	public void addComponent() {
		userNamelbl = new JLabel("Username:");
		passwordlbl = new JLabel("Password:");
		userNametf = new JTextField();
		emptyPasswordStar = new JLabel("*");
		emptyUsernameStar = new JLabel("*");
		emptyPasswordStar.setFont(new Font("Courier New", Font.BOLD, 18));
		emptyPasswordStar.setForeground(Color.RED);
		emptyUsernameStar.setFont(new Font("Courier New", Font.BOLD, 18));
		emptyUsernameStar.setForeground(Color.RED);
		emptyPasswordStar.setVisible(false);
		emptyUsernameStar.setVisible(false);
		userNametf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				passwordFd.requestFocusInWindow();
				
			}
		});
		
		passwordFd = new JPasswordField();
		loginBtn = new JButton("Login");
		passwordFd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				loginBtn.requestFocusInWindow();
				
			}
		});
		
		connectionLabel = new JLabel();
	}

	public void setLblText(String text) {
		connectionLabel.setText(text);
	}
	
	public void setiLoginListener(ILoginListener iLoginListener) {
		this.iLoginListener = iLoginListener;
	}

	public void setLayout() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		gc.weightx = 1;
		gc.weighty = 1;

		gc.insets = new Insets(5, 5, 5, 5);

		gc.weightx = 2;
		gc.gridy = 0;
		gc.gridx = 1;
		this.add(emptyUsernameStar, gc);
		
		gc.weightx = 2;
		gc.gridy = 1;
		gc.gridx = 1;
		this.add(emptyPasswordStar, gc);
		
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridy = 0;
		gc.gridx = 0;
		this.add(userNamelbl, gc);

		gc.gridy = 1;
		gc.gridx = 0;
		this.add(passwordlbl, gc);

		gc.weightx = 2;
		gc.gridy = 0;
		gc.gridx = 2;
		this.add(userNametf, gc);

		gc.gridy = 1;
		gc.gridx = 2;
		this.add(passwordFd, gc);

		gc.gridy = 2;
		gc.gridx = 2;
		gc.weighty = 14;
		gc.anchor = GridBagConstraints.NORTH;
		this.add(loginBtn, gc);
		loginBtn.addActionListener(new LoginBtnClick());
		loginBtn.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {

			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					 loginBtn.doClick();
					
				}	
			}
		} );

		gc.gridy = 3;
		gc.gridx = 2;
		gc.weighty = 1;
		gc.anchor = GridBagConstraints.NORTH;
		this.add(connectionLabel, gc);

	}

	private class LoginBtnClick implements ActionListener  {

		@Override
		public void actionPerformed(ActionEvent event) {
			JButton btn = (JButton) event.getSource();
			btn.requestFocusInWindow();
			String password = "";
			if (btn == loginBtn) {
				if (iLoginListener != null) {
					String username = userNametf.getText();
					char[] passwordarr = passwordFd.getPassword();
					for (int i = 0; i < passwordarr.length; i++) {
						password += passwordarr[i];
					}
					if(username.isEmpty() & password.isEmpty()){
						userNametf.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.red));
						emptyUsernameStar.setVisible(true);
						passwordFd.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.red));
						emptyPasswordStar.setVisible(true);
						JOptionPane.showMessageDialog(null,"Please Enter UserName And Password", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else if (username.isEmpty()) {
						userNametf.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.red));
						emptyUsernameStar.setVisible(true);
						JOptionPane.showMessageDialog(null,"Usename Can Not be empty!", "Error", JOptionPane.ERROR_MESSAGE);
						passwordFd.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
						emptyPasswordStar.setVisible(false);
					}
					else if (password.isEmpty()) {
						passwordFd.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.red));
						emptyPasswordStar.setVisible(true);
						JOptionPane.showMessageDialog(null,"Password can not be empty!", "Error", JOptionPane.ERROR_MESSAGE);
						userNametf.setBorder(BorderFactory.createMatteBorder(1,
								1, 1, 1, Color.BLACK));
						emptyUsernameStar.setVisible(false);
					} else {
					iLoginListener.userInfoEmit(username, password);
					}
				}
			}

		}

	}
}
