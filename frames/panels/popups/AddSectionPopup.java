package frames.panels.popups;

import javax.swing.*;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Font;
import library.apachelang3.StringUtils;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.*;
import java.time.*;
import java.util.HashMap;
import java.sql.SQLException;
import java.io.IOException;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

import java.util.ArrayList;

import library.*;
import frames.panels.core.*;
import entity.*;
import models.*;

public class AddSectionPopup extends JDialog implements ActionListener {

	JComboBox<JcbItem<Classes>> comboboxClass;
	JFrame parent;
	Section section;
	JTextField sectionName;
	
	public AddSectionPopup(JFrame parent) {
		super(parent, "Add New Section", true);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		
		
		JLabel labelClass = new JLabel("Class Name");
		labelClass.setBounds(20, 25, 150, 30);
		
		HashMap<Integer, JcbItem<Classes>> classes = null;

		try{
			MClasses mClasses = new MClasses();
			classes = mClasses.retrive();
		} catch (DatabaseException | SQLException ex) {}
		
		comboboxClass = new JComboBox<JcbItem<Classes>>();
	    comboboxClass.setBounds(190, 25, 350, 30);

	    if(classes!=null) {
		    Helper.initializeCombo(comboboxClass, classes, null, "Select Class");
		}


		JLabel labelSectionName = new JLabel("Section Name");
		labelSectionName.setBounds(20, 65, 150, 30);
		
	    sectionName = new JTextField();
		sectionName.setBounds(190, 65, 350, 30);


	   // comboboxClassName.addActionListener(this);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(440, 105, 100, 30);
		btnAdd.addActionListener(this);

		this.add(comboboxClass);
		this.add(labelClass);
		this.add(sectionName);
		this.add(labelSectionName);
		this.add(btnAdd);
		this.setResizable(false);
		setExtendedSize(new Dimension(600,200));    
        this.setVisible(true);  
	}

	private void setExtendedSize(Dimension dimension)
	{
		Point location = new Point();
		location.setLocation((Configuration.mainWidth()/2) - (dimension.getWidth()/2), (Configuration.mainHeight()/2) - (dimension.getHeight()/2));
		this.setSize(dimension);
		this.setLocation(location);
	}

	public void actionPerformed(ActionEvent e) {

		Classes _class = (Classes) Helper.getSelectedValue(comboboxClass);
		int cid = _class.getId();
		
		MSection mSection = new MSection();

		if( _class==null || sectionName.getText()==null ) {
			JOptionPane.showMessageDialog(parent, "Required fields are missing.\nAll the * markerd\nfields are required", "ERROR OCCURED !!",  JOptionPane.ERROR_MESSAGE);
		}

		else{

			try{

				int inserted = mSection.insert(new Section(1, cid, sectionName.getText()));

				System.out.println(inserted);
				dispose();

			}

			catch(DatabaseException | SQLException ex) {
				JOptionPane.showMessageDialog(parent, ex.getMessage(), "ERROR OCCURED !!",  JOptionPane.ERROR_MESSAGE);
			}


		}

		
	    
	}
}