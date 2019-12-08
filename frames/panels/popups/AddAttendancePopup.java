package frames.panels.popups;

import javax.swing.*;
import java.awt.*;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;

import library.*;
import frames.panels.core.*;

public class AddAttendancePopup extends JDialog implements ActionListener {
	JPanel panelTBody,panelTHeader;
	JScrollPane sp;
	JTextField tReason;
	public AddAttendancePopup(JFrame jf) {
		super(jf, "Take Attendance", true);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		
		panelTBody = new JPanel();
	    panelTHeader = new JPanel();

		panelTHeader.setBounds(30, 90, 720, 30);
		panelTHeader.setBackground(new Color(199,203,209));
		panelTHeader.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, new Color(202, 202, 202)));
		panelTHeader.setVisible(false);
		panelTHeader.setLayout(null);
		
		
        JLabel l1 = new JLabel("Date");
		l1.setBounds(30, 10, 50, 30);
		JTextField t1 = new JTextField(null);
		t1.setBounds(30,40,170,30);
		
		JLabel l2 = new JLabel("Select Class");
		l2.setBounds(220, 10, 250, 30);
	    String[] Cls = {"Select Class","9","10","11","12"};
		JComboBox<String> c1 = new JComboBox<String>(Cls);
		c1.setBounds(220,40,170,30);
		
		JLabel l3= new JLabel("Select Session");
		l3.setBounds(410, 10, 250, 30);
	    String[] Ses = {"Select Session","2018-2019","2019-2020"};
		JComboBox<String> c2 = new JComboBox<String>(Ses);
		c2.setBounds(410,40,170,30);
		
		panelTBody.setPreferredSize(new Dimension(720, 360));
		panelTBody.setBackground(new Color(172, 172, 173));
		panelTBody.setLayout(null);
		
		
		JButton btnAdd = new JButton("Take/View Attendance");
		btnAdd.setBounds(600, 40, 150, 30);

		JLabel labelSrNo = new JLabel("SrNo");
		labelSrNo.setBounds(10, 0,50,30);
		panelTHeader.add(labelSrNo);

		JLabel labelRollNo = new JLabel("Roll No",SwingConstants.CENTER);
		labelRollNo.setBounds(30, 0, 100, 30);
		panelTHeader.add(labelRollNo);

		JLabel labelSName = new JLabel("Student Name",SwingConstants.CENTER);
		labelSName.setBounds(130,0,150,30);
		panelTHeader.add(labelSName);

		JLabel labelAttendence = new JLabel("Attendance",SwingConstants.CENTER);
		labelAttendence.setBounds(270, 0,300,30);
		panelTHeader.add(labelAttendence);

		JLabel labelReason = new JLabel("Reason");
		labelReason.setBounds(590, 0, 120, 30);
		panelTHeader.add(labelReason);

		sp = new JScrollPane(panelTBody, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(202, 202, 202)));
		sp.setBounds(30,120,720,360);
		sp.setVisible(false);

        
		
		
        
		this.add(l1);
		this.add(t1);
		this.add(l2);
		this.add(c1);
		this.add(l3);
		this.add(c2);
		this.add(panelTHeader);
		this.add(sp);
		this.add(btnAdd);
		
		btnAdd.addActionListener(this);
		
		setExtendedSize(new Dimension(800,540));    
        this.setVisible(true);
		
		

           		
	}

	private void drawTable(ArrayList<String> data)
	{
		

		int size = data.size();
		int height = 30*size;
		int width = height>360?701:720;
		Color odd = new Color(249, 249, 249);
		Color even = new Color(230, 230, 230);

		for(int sr = 0; sr<size; sr++)
		{
			int x = 0;
			int y = sr*30;

			JPanel jp = new JPanel();
			jp.setBounds(x, y, width, 30);
			jp.setLayout(null);
			jp.setBackground((sr+1)%2==0?even:odd);
			if((sr+1)!=size) jp.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(202, 202, 202)));


			JLabel labelSrNo = new JLabel(String.valueOf(sr+1));
			labelSrNo.setBounds(10, 0,50,30);
			//labelSrNo.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(202, 202, 202)));
			jp.add(labelSrNo);

			JLabel labelRollNo = new JLabel("Roll No",SwingConstants.CENTER);
			labelRollNo.setBounds(30, 0, 100, 30);
			//labelRollNo.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(202, 202, 202)));
			jp.add(labelRollNo);

			JLabel labelSName = new JLabel(data.get(sr));
			labelSName.setBounds(130,0,150,30);
		    labelSName.setBackground((sr+1)%2==0?even:odd);
			jp.add(labelSName);

			ButtonGroup groupAttendance = new ButtonGroup(); 
            JRadioButton rPresent = new JRadioButton("Presrent");
			JRadioButton rAbsent = new JRadioButton("Absent");
			JRadioButton rLate = new JRadioButton("Late");
			rPresent.setBounds(300, 0, 80, 30);
			rAbsent.setBounds(385, 0, 80, 30);
			rLate.setBounds(475, 0, 80, 30);
			groupAttendance.add(rPresent);
			groupAttendance.add(rAbsent);
			groupAttendance.add(rLate);
			rPresent.setBackground((sr+1)%2==0?even:odd);
			rAbsent.setBackground((sr+1)%2==0?even:odd);
			rLate.setBackground((sr+1)%2==0?even:odd);


			jp.add(rPresent);
			jp.add(rAbsent);
			jp.add(rLate);
			
			tReason = new JTextField();
			tReason.setBounds(560, 0, 135, 30);   
			tReason.setBackground((sr+1)%2==0?even:odd);
			jp.add(tReason);

			panelTBody.add(jp);

		}

		panelTBody.setPreferredSize(new Dimension(width, height));


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
              
			ArrayList<String> students = new ArrayList<String>();

			students.add("Rashed");
			students.add("Shifat"); 
			students.add("Mosavvir"); 
			students.add("Rashed");
			students.add("Shifat"); 
			students.add("Mosavvir"); 
			students.add("Rashed");
			students.add("Shifat"); 
			students.add("Mosavvir"); 
			students.add("Rashed");
			students.add("Shifat"); 
			students.add("Mosavvir"); 
			students.add("Rashed");
			students.add("Shifat"); 
			students.add("Mosavvir"); 
			students.add("Rashed");
			students.add("Shifat"); 
			students.add("Mosavvir"); 
			students.add("Rashed");
			students.add("Shifat"); 
			students.add("Mosavvir"); 
			students.add("Rashed");
			students.add("Shifat"); 
			students.add("Mosavvir"); 
			students.add("Rashed");
			students.add("Shifat"); 
			students.add("Mosavvir"); 
			students.add("Rashed");
			students.add("Shifat"); 
			students.add("Mosavvir"); 
			students.add("Rashed");
			students.add("Shifat"); 
			students.add("Mosavvir"); 
			students.add("Rashed");
			students.add("Shifat"); 
			students.add("Mosavvir"); 
            
            this.drawTable(students);
		    panelTHeader.setVisible(true);
		    
			sp.setVisible(true);
			  
			 
	 }
		
	
}