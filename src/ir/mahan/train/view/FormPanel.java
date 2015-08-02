package ir.mahan.train.view;

import ir.mahan.train.model.Gender;
import ir.mahan.train.model.UserFavouriteSportEnum;
import ir.mahan.train.model.EmpCategory;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class FormPanel extends JPanel {
	private JTextField firstNameTxt;
	private JLabel firstNameLbl;
	private JTextField lastNameTxt;
	private JLabel lastNameLbl;
	private AgePanel agePanel;
	private JLabel ageLbl;
	private JComboBox roleCB;
	private JLabel roleLbl;
	private JComboBox genderCB;
	private JLabel genderLbl;
	private JLabel cityLbl;
	private JButton submitBtn;
	private IformEvent istringListener;
	private UserCityPanel userCity;
	private JList userFavouriteSportList;
	private JLabel userFavouriteSportLbl;
	private JCheckBox isEmployeeChB;
	private JLabel salaryLbl;
	private JTextField salaryTxt;

	public void setIstringListener(IformEvent istringListener) {
		this.istringListener = istringListener;
	}

	public FormPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 300;
		setPreferredSize(dim);

		Border innerBorder = BorderFactory.createTitledBorder("User Profile");
		Border outerborder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(innerBorder, outerborder));

		firstNameTxt = new JTextField();
		firstNameLbl = new JLabel("Name :");
		lastNameTxt = new JTextField();
		lastNameLbl = new JLabel("Family :");
		agePanel = new AgePanel();
		ageLbl = new JLabel("Age :");
		roleCB = new JComboBox(EmpCategory.values());
		roleLbl = new JLabel("Role :");
		genderCB = new JComboBox(Gender.values());
		genderLbl = new JLabel("Gender :");
		userCity = new UserCityPanel();
		userFavouriteSportList = new JList(UserFavouriteSportEnum.values());
		userFavouriteSportLbl = new JLabel("Favourite Sport :");
		userFavouriteSportList.setSelectedIndex(0);
		cityLbl = new JLabel("City :");
		isEmployeeChB = new JCheckBox("Is Employee");
		salaryLbl = new JLabel("Salary :");
		salaryTxt = new JTextField();
		submitBtn = new JButton("Submit");
		// isEmployeeChB.addActionListener(this);
		salaryTxt.setEnabled(false);
		setLayout(new GridBagLayout());
		layoutComponent();
		
		
		CheckboxItemListener checkboxItemListener = new CheckboxItemListener();
		isEmployeeChB.addItemListener(checkboxItemListener);
		
		BtnActionListener btnActionListener = new BtnActionListener();
		submitBtn.addActionListener(btnActionListener);
	}

	private void layoutComponent() {
		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridy = 0;
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(firstNameLbl, gc);

		gc.gridy = 0;
		gc.gridx = 1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		add(firstNameTxt, gc);

		gc.gridy = 1;
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(lastNameLbl, gc);

		gc.gridy = 1;
		gc.gridx = 1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		add(lastNameTxt, gc);

		gc.gridy = 2;
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(roleLbl, gc);

		gc.gridy = 2;
		gc.gridx = 1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		add(roleCB, gc);

		gc.gridy = 3;
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(ageLbl, gc);

		gc.gridy = 3;
		gc.gridx = 1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		add(agePanel, gc);

		gc.gridy = 4;
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(genderLbl, gc);

		gc.gridy = 4;
		gc.gridx = 1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		add(genderCB, gc);

		gc.gridy = 5;
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.NORTHWEST;
		add(cityLbl, gc);

		gc.gridy = 5;
		gc.gridx = 1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		add(userCity, gc);

		gc.gridy = 6;
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.NORTHWEST;
		add(userFavouriteSportLbl, gc);

		gc.gridy = 6;
		gc.gridx = 1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		add(userFavouriteSportList, gc);

		gc.gridy = 7;
		gc.gridx = 1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		add(isEmployeeChB, gc);

		gc.gridy = 8;
		gc.gridx = 0;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		add(salaryLbl, gc);

		gc.gridy = 8;
		gc.gridx = 1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		add(salaryTxt, gc);

		gc.gridy = 9;
		gc.gridx = 1;
		gc.weighty = 2;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.fill = GridBagConstraints.CENTER;
		add(submitBtn, gc);
	}

	// @Override
	// public void actionPerformed(ActionEvent event) {
	// JButton btn = (JButton) event.getSource();
	//
	// if (btn == submitBtn) {
	// if (istringListener != null) {
	// User user = new User();
	// user.FirstName = firstNameTxt.getText();
	// user.LastName = lastNameTxt.getText();
	// user.Age = agePanel.getSelectedButtonText();
	// user.Gender = genderCB.getSelectedItem().toString();
	// user.Role = roleCB.getSelectedItem().toString();
	// user.City = userCity.getSelectedButtonText();
	// user.favouriteSport =
	// userFavouriteSportList.getSelectedValue().toString();
	// user.isEmployee = isEmployeeChB.isSelected();
	// // user.salary = Integer.parseInt(salaryTxt.getText());
	//
	//
	// istringListener.stringEmitted(user);
	// }
	// }
	// // JCheckBox chb = (JCheckBox) event.getSource();
	// // if (chb == isEmployeeChB) {
	// // salaryTxt.setEnabled(isEmployeeChB.isSelected());
	// // }
	//
	// }
	private class BtnActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			JButton btn = (JButton) event.getSource();
			
			
			if (btn == submitBtn) {
				
				
				if (istringListener != null) {
					User user = new User();
					user.firstName = firstNameTxt.getText();
					user.lastName = lastNameTxt.getText();
					user.age = agePanel.getSelectedButtonText();
					user.gender = genderCB.getSelectedItem().toString();
					user.role = roleCB.getSelectedItem().toString();
					user.city = userCity.getSelectedButtonText();
					user.favouriteSport = userFavouriteSportList.getSelectedValue().toString();
					user.isEmployee = isEmployeeChB.isSelected();
					
					try {
						user.salary = Integer.parseInt(salaryTxt.getText());
						salaryTxt.setText("");
					} catch (Exception e) {
						
					}
//					if (salaryTxt.getText() != null) {
//						user.salary = Integer.parseInt(salaryTxt.getText());
//					} else {
//						System.out.println("hello");
//					}
//					
					
					
					istringListener.formEventEmitted(user);
				}
			}
		}

	}
	
	private class CheckboxItemListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent event) {
			if (isEmployeeChB.isSelected()) {
				salaryTxt.setEnabled(true);
			} else {
				salaryTxt.setEnabled(false);
			}
		}
		
	}
}
