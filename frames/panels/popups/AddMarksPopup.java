package frames.panels.popups;

import javax.swing.*;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;
import library.*;
import frames.panels.core.*;

public class AddMarksPopup extends JDialog implements ActionListener {

	JPanel panelTHeader = new JPanel();
	JPanel panelTBody = new JPanel();
	JScrollPane sp = new JScrollPane();
	JButton btnAdd1;

	public AddMarksPopup(JFrame jf) {
		super(jf, "Add Marks", true);
		this.setLayout(null);
		this.setLocationRelativeTo(null);

		JLabel lExam = new JLabel("Exam");
		lExam.setBounds(75, 10, 250, 30);
	    String[] exm = {"Select Exam","Class Test","Model Test"};
		JComboBox<String> cExam = new JComboBox<String>(exm);
		cExam.setBounds(75,40,200,30);
		
		JLabel lMtype= new JLabel("Marks Type");
		lMtype.setBounds(310, 10, 250, 30);
	    String[] mrk = {"Select Type","Written","MCQ","Written+MCQ"};
		JComboBox<String> cMarks = new JComboBox<String>(mrk);
		cMarks.setBounds(310,40,200,30);

		JButton btnAdd = new JButton("View");
		btnAdd.setBounds(550, 40, 150, 30);

	 	btnAdd1 = new JButton("Add Marks");
		btnAdd1.setBounds(400,500, 150, 30);
		btnAdd1.setVisible(false);



		panelTHeader.setBounds(30, 90, 720, 30);
		panelTHeader.setBackground(new Color(199,203,209));
		panelTHeader.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, new Color(202, 202, 202)));
		panelTHeader.setVisible(false);
		panelTHeader.setLayout(null);

		panelTBody.setPreferredSize(new Dimension(720, 360));
		panelTBody.setBackground(new Color(172, 172, 173));
		panelTBody.setLayout(null);

		JLabel labelRollNo = new JLabel("Roll");
		labelRollNo.setBounds(10, 0,50,30);
		panelTHeader.add(labelRollNo);

		JLabel labelSName = new JLabel("Student Name",SwingConstants.CENTER);
		labelSName.setBounds(60, 0, 100, 30);
		panelTHeader.add(labelSName);

		JLabel labelAttendance = new JLabel("Attendance",SwingConstants.CENTER);
		labelAttendance.setBounds(170,0,150,30);
		panelTHeader.add(labelAttendance);

		JLabel labelMarks = new JLabel("Written/ MCQ / Written+MCQ",SwingConstants.CENTER);
		labelMarks.setBounds(280, 0,300,30);
		panelTHeader.add(labelMarks);

		JLabel labelTotal = new JLabel("Total Marks");
		labelTotal.setBounds(590, 0, 120, 30);
		panelTHeader.add(labelTotal);

		sp = new JScrollPane(panelTBody, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(202, 202, 202)));
		sp.setBounds(30,120,720,360);
		sp.setVisible(false);

		this.add(lExam);
		this.add(cExam);
		this.add(lMtype);
		this.add(cMarks);
		this.add(panelTHeader);
		this.add(sp);
        this.add(btnAdd);
         this.add(btnAdd1);
        btnAdd.addActionListener(this);
		setExtendedSize(new Dimension(800,600));    
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


			JLabel labelRollNo = new JLabel("Roll");
			labelRollNo.setBounds(10, 0,50,30);
			//labelRollNo.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(202, 202, 202)));
			jp.add(labelRollNo);

			JLabel labelSName = new JLabel(data.get(sr),SwingConstants.CENTER);
			labelSName.setBounds(70, 0, 100, 30);
			jp.add(labelSName);


			String at[] = {"Present", "Absent"};
			JComboBox<String> cAttendance = new JComboBox<String>(at);
			cAttendance.setBounds(170,0,150,30);
			jp.add(cAttendance);

			JTextField tMarks = new JTextField();
			tMarks.setBounds(360, 0, 150, 30);
			tMarks.setBackground((sr+1)%2==0?even:odd);
			jp.add(tMarks);

			JTextField tTotal = new JTextField();
			tTotal.setBounds(560, 0, 135, 30);
			tTotal.setBackground((sr+1)%2==0?even:odd);
			jp.add(tTotal);


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

	    	students.add("Shifat");
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
	    btnAdd1.setVisible(true);

	    panelTHeader.setVisible(true);
	    sp.setVisible(true);

	}
}