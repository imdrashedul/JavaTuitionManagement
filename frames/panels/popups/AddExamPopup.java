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

public class AddExamPopup extends JDialog implements ActionListener {
	public AddExamPopup(JFrame jf) {
		super(jf, "Add New Exam", true);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JLabel l1= new JLabel("Session");
		l1.setBounds(30, 50, 250, 30);
	    String[] Ses={"Select Session","2018-2019","2019-2020"};
		JComboBox<String> c1 =new JComboBox<String>(Ses);
		c1.setBounds(130,50,170,30);
		
		JLabel l2= new JLabel("Exam Name");
		l2.setBounds(30, 100, 150, 30);
		JTextField t2 =new JTextField(null);
		t2.setBounds(130,100,170,30);
		
		JLabel l3= new JLabel("Exam Type");
		l3.setBounds(30, 150, 150, 30);
	    String[] E={"Select Type","Class Test","Model Test"};
		JComboBox<String> e1 =new JComboBox<String>(E);
		e1.setBounds(130,150,170,30);
		
		JLabel l5= new JLabel("Class");
		l5.setBounds(30, 200, 250, 30);
	    String[] Cls={"Select Class","9","10","11","12"};
		JComboBox<String> c2 =new JComboBox<String>(Cls);
		c2.setBounds(130,200,170,30);
		
		
		JLabel l4= new JLabel("Section");
		l4.setBounds(30, 250, 250, 30);
	    String[] Sec={"Select Class","A","B","C","D"};
		JComboBox<String> c3 =new JComboBox<String>(Sec);
		c3.setBounds(130,250,170,30);
		
		
		JLabel l6= new JLabel("Exam Date");
		l6.setBounds(30, 300, 150, 30);
		JTextField t1 =new JTextField(null);
		t1.setBounds(130,300,170,30);
		
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(450, 500, 100, 30);
		btnAdd.addActionListener(this);

        this.add(l1);
		this.add(t1);
		this.add(l2);
		this.add(c1);
		this.add(l3);
		this.add(c2);
		this.add(l4);
		this.add(t2);
		this.add(l5);
		this.add(c3);
		
		this.add(l6);
		this.add(e1);
		this.add(btnAdd);
		setExtendedSize(new Dimension(600,600));    
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