package ir.mahan.train.view;

import ir.mahan.train.model.User;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainFrame extends JFrame {

	public MenuPanel btnPanel;
	public TextPanel textPanel;
	public FormPanel formPanel;

	public MainFrame(String title) {
		super(title);
		this.setView();
		this.addComponent();
	}

	private void addComponent() {
		btnPanel = new MenuPanel();
		textPanel = new TextPanel();
		btnPanel.setIstringListener(new IuserListener<String>(){
		public void stringEmitted(String input){
				textPanel.setText(input);
			}
		});
		
		formPanel = new FormPanel();
		formPanel.setIstringListener(new IuserListener<User>() {
		public void stringEmitted(User user) {
				if (user.IsValid(true)) {
					textPanel.setText(user.ToString("::"));
				}
			}
			
		});
		
		this.add(btnPanel, BorderLayout.NORTH);
		this.add(textPanel, BorderLayout.EAST);
		this.add(formPanel, BorderLayout.WEST);
	}

	private void setView() {
		this.setSize(720, 500);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
