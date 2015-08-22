import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import ir.mahan.train.controller.Controller;
import ir.mahan.train.view.LoginFrame;
import ir.mahan.train.view.MainFrame;
//new comment
public class StartApp {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new LoginFrame("Login");
			}
		});

	}

}
