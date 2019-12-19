package frames.panels.popups;

import javax.swing.*;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.*;

import library.*;
import frames.panels.core.*;

public class AddSectionPopup extends JDialog implements ActionListener {
	public AddSectionPopup(JFrame jf) {
		super(jf, "Add New Section", true);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JLabel labelSectionName = new JLabel("Section Name");
		labelSectionName.setBounds(20, 25, 150, 30);
		
		JTextField sectionName = new JTextField();
		sectionName.setBounds(190, 25, 350, 30);
		
		JLabel labelClass = new JLabel("Class Name");
		labelClass.setBounds(20, 65, 150, 30);
		
		String ClassName[]={"SSC","HSC "};
		
		JComboBox<String> comboboxClassName = new JComboBox<String>(ClassName);
	    comboboxClassName.setBounds(190, 65, 350, 30);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(440, 105, 100, 30);
		btnAdd.addActionListener(this);

		this.add(comboboxClassName);
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
	    dispose();
	}
}