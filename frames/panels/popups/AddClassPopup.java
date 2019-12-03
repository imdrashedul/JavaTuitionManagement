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

public class AddClassPopup extends JDialog implements ActionListener {
	public AddClassPopup(JFrame jf) {
		super(jf, "Add New Class", true);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JLabel labelClassName = new JLabel("Class Name");
		labelClassName.setBounds(20, 25, 150, 30);
		
		JTextField className = new JTextField();
		className.setBounds(190, 25, 350, 30);
		
		JLabel labelNumericName = new JLabel("Numeric Name");
		labelNumericName.setBounds(20, 65, 150, 30);
		
		JTextField numericName = new JTextField();
		numericName.setBounds(190, 65, 350, 30);

		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(440, 105, 100, 30);
		btnAdd.addActionListener(this);

		

		this.add(numericName);
		this.add(labelNumericName);
		this.add(className);
		this.add(labelClassName);
		this.add(btnAdd);
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