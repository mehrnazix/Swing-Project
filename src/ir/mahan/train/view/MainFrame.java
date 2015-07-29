package ir.mahan.train.view;

import ir.mahan.train.model.User;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame {

	public MenuPanel btnPanel;
	public TextPanel textPanel;
	public FormPanel formPanel;
	private JTabbedPane tabbedpane;
	private JSplitPane splitPane;
	
	
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
		
		tabbedpane = new JTabbedPane();
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,formPanel,tabbedpane);
		tabbedpane.add("Text Area", textPanel);
		splitPane.setOneTouchExpandable(true);
		this.add(btnPanel, BorderLayout.NORTH);
		this.add(splitPane, BorderLayout.EAST);
	}

	private void setView() {
		this.setSize(720, 500);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
