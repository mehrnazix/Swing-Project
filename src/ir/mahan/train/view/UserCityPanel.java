package ir.mahan.train.view;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import sun.org.mozilla.javascript.internal.regexp.SubString;

public class UserCityPanel extends JPanel {
	
	public JCheckBox tehranChB;
	public JCheckBox kermanChB;
	
	public UserCityPanel() {
		tehranChB = new JCheckBox("Tehran");
		kermanChB = new JCheckBox("Kerman");
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 0.1;
		gc.weighty = 0.1;

		gc.gridy = 0;
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(tehranChB, gc);

		gc.gridy = 1;
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(kermanChB, gc);		
		
		
	}
	
    public String getSelectedButtonText() {
    	String result = "";
    	for( int i=0; i<this.getComponentCount(); i++ ) {
    		  JCheckBox checkBox = (JCheckBox)this.getComponent(i);
    		  if(checkBox.isSelected()) {
    		     result += checkBox.getText() + ",";
    		  }
    		}
    	if (result != "") {
			result = result.substring(0, result.length()-1);
		}
        return result;
    }
}
