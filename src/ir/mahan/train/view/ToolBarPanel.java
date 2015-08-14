package ir.mahan.train.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class ToolBarPanel extends JToolBar implements ActionListener {
	public JToolBar toolbar;
	public JButton saveBtn,refreshBtn;
	public ItoolbarListener itoolbarListenr;
	public JLabel userlbl;
	
	public ToolBarPanel() {
		

		setBorder(BorderFactory.createEtchedBorder());
		setFloatable(false);
		
		userlbl = new JLabel("    Welcome dear user, " +  LoginFrame.user);
		
		
		saveBtn = new JButton();
		refreshBtn = new JButton();
		
		saveBtn.setIcon(Utils.createIcon("/images/Save16.gif"));
		saveBtn.setToolTipText("Save To database");
		
		refreshBtn.setIcon(Utils.createIcon("/images/refresh16.gif"));
		refreshBtn.setToolTipText("refresh from databse");
		
		saveBtn.addActionListener(this);
		refreshBtn.addActionListener(this);
		
		add(saveBtn);
		addSeparator();
		add(refreshBtn);
		add(userlbl);
	}

	public void setToolbarListener(ItoolbarListener toolbarListener) {
		itoolbarListenr = toolbarListener;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton) e.getSource();
		if (clicked == saveBtn) {
			if (this.itoolbarListenr != null) {
				itoolbarListenr.saveEventOccured();
			}
		}
		else {
			if (itoolbarListenr != null) {
				itoolbarListenr.refreshEventOccured();
			}
		}
	}
	
	
	
	
}
