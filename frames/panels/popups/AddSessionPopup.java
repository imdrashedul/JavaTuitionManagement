package frames.panels.popups;

import javax.swing.*;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.*;
import java.time.LocalDateTime;

import entity.*;
import models.*;
import library.*;
import frames.panels.core.*;


public class AddSessionPopup extends JDialog implements ActionListener {

	public Session ses;
	public MSession mses = new MSession();
	JTextField startYear;
	JTextField endYear;


	public AddSessionPopup(JFrame jf) {
		super(jf, "Add New Session", true);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JLabel labelStartYear = new JLabel("Start Year");
		labelStartYear.setBounds(20, 25, 150, 30);
		
		startYear = new JTextField();
		startYear.setBounds(190, 25, 350, 30);
		
		JLabel labelEndYear = new JLabel("End Year");
		labelEndYear.setBounds(20, 65, 150, 30);
		
		endYear = new JTextField();
		endYear.setBounds(190, 65, 350, 30);

		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(440, 105, 100, 30);
		btnAdd.addActionListener(this);

		this.add(endYear);
		this.add(labelEndYear);
		this.add(startYear);
		this.add(labelStartYear);
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
	   // dispose();

		try{

				String st = new String(startYear.getText());
				int start = Integer.parseInt(st);

				String en = new String(endYear.getText());
				int end = Integer.parseInt(en);

				LocalDateTime datetime1 = LocalDateTime.now();  
    			
				mses.insert(new Session (1,start, end, datetime1 ));
		}

		catch(Exception aa){
				System.out.println(aa);
		}

	}
}