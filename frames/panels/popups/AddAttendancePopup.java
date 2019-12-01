package frames.panels.popups;

import javax.swing.*;
import java.awt.*;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.*;

import library.*;
import frames.panels.core.*;

public class AddAttendancePopup extends JDialog implements ActionListener {
	JPanel jp;
	public AddAttendancePopup(JFrame jf) {
		super(jf, "Take Attendance", true);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		
		 jp =new JPanel();
		
        JLabel l1= new JLabel("Date");
		l1.setBounds(30, 50, 50, 30);
		JTextField t1 =new JTextField(null);
		t1.setBounds(30,85,170,30);
		
		JLabel l2= new JLabel("Select Class");
		l2.setBounds(220, 50, 250, 30);
	    String[] Cls={"Select Class","9","10","11","12"};
		JComboBox<String> c1 =new JComboBox<String>(Cls);
		c1.setBounds(220,85,170,30);
		
		JLabel l3= new JLabel("Select Session");
		l3.setBounds(410, 50, 250, 30);
	    String[] Ses={"Select Session","2018-2019","2019-2020"};
		JComboBox<String> c2 =new JComboBox<String>(Ses);
		c2.setBounds(410,85,170,30);
		
		jp.setBounds(30,150,700,700);
		jp.setBackground(Color.white);
		jp.setVisible(false);
		
		
		JButton btnAdd = new JButton("Take/View Attendance");
		btnAdd.setBounds(600, 85, 150, 30);
        
		
		
        
		this.add(l1);
		this.add(t1);
		this.add(l2);
		this.add(c1);
		this.add(l3);
		this.add(c2);
		this.add(jp);
		this.add(btnAdd);
		
		btnAdd.addActionListener(this);
		
		setExtendedSize(new Dimension(800,800));    
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
	     //dispose();
              
			  
		      jp.setVisible(true);
			  jp.setLayout(null);
			  
			  JLabel ll1= new JLabel("SrNo");
			  ll1.setBounds(20,30,150,30);
			  jp.add(ll1);
			  
			  JLabel ll2= new JLabel("Roll No");
			  ll2.setBounds(100,30,150,30);
			  jp.add(ll2);
			  
			  JLabel ll3= new JLabel("Student Name");
			  ll3.setBounds(200,30,150,30);
			  jp.add(ll3);
			  
			  JLabel ll4= new JLabel("Attendance");
			  ll4.setBounds(400,30,150,30);
			  jp.add(ll4);
			  
		      JLabel ll5= new JLabel("Reason");
			  ll5.setBounds(550,30,150,30);
			  jp.add(ll5);
	 }
		
	
}