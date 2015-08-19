package ir.mahan.train.view;

import ir.mahan.train.model.AgeCategory;

import java.awt.FlowLayout;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class AgePanel extends JPanel {
	public	ButtonGroup		gb;
	public	JRadioButton	rd_18;
	public	JRadioButton	rd_20_30;
	public	JRadioButton	rd_more;
	
	public AgePanel(){
		
		gb = new ButtonGroup();
		rd_18 = new JRadioButton(AgeCategory._18.toString(), true);
		rd_20_30 = new JRadioButton(AgeCategory._20_30.toString());
		rd_more = new JRadioButton(AgeCategory.more.toString());
		gb.add(rd_18);
		gb.add(rd_20_30);
		gb.add(rd_more);
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		this.add(rd_18);
		this.add(rd_20_30);
		this.add(rd_more);
	}
	
    public AgeCategory getSelectedButtonText() {
        for (Enumeration<AbstractButton> buttons = gb.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return AgeCategory.valueOf(button.getText());
            }
            

        }

        return null;
    }
}
