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

public class AddGradingPopup extends JDialog implements ActionListener {
	public AddGradingPopup(JFrame jf) {
		super(jf, "Add New Grade", true);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JLabel labelGrade = new JLabel("Grade");
		labelGrade.setBounds(20, 25, 150, 30);
		
		JTextField grade = new JTextField();
		grade.setBounds(190, 25, 350, 30);
		
		JLabel labelGPA = new JLabel("GPA");
		labelGPA.setBounds(20, 65, 150, 30);
		
		JTextField GPA = new JTextField();
		GPA.setBounds(190, 65, 350, 30);
		
		JLabel labelGPAfrom = new JLabel("FROM");
		labelGPAfrom.setBounds(190, 105, 150, 30);
		
		JTextField GPAfrom = new JTextField();
		GPAfrom.setBounds(250, 105, 100, 30);
		
		JLabel labelGPAto = new JLabel("TO");
		labelGPAto.setBounds(390, 105, 150, 30);
		
		JTextField GPAto = new JTextField();
		GPAto.setBounds(440, 105, 100, 30);

		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(440, 150, 100, 30);
		btnAdd.addActionListener(this);
		
		

		this.add(GPAto);
		this.add(labelGPAto);
		this.add(GPAfrom);
		this.add(labelGPAfrom);
		this.add(GPA);
		this.add(labelGPA);
		this.add(grade);
		this.add(labelGrade);
		this.add(btnAdd);
		this.setResizable(false);
		setExtendedSize(new Dimension(600,245));    
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