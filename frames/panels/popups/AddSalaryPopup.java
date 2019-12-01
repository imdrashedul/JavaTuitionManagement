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

public class AddSalaryPopup extends JDialog implements ActionListener {
	public AddSalaryPopup(JFrame jf) {
		super(jf, "Add Salary", true);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		
		
		JLabel labelEmployee = new JLabel("Employee");
		labelEmployee.setBounds(20, 25, 150, 30);
		
		String Emp[]={"Rashed","Captain","Munira"};
		JComboBox<String> comboboxEmployee = new JComboBox<String>(Emp);
	    comboboxEmployee.setBounds(190, 25, 150, 30);
		
		JLabel labelMonth = new JLabel("Month");
		labelMonth.setBounds(20, 65, 150, 30);
		
		JTextField Month = new JTextField();
		Month.setBounds(190, 65, 150, 30);
		
		JLabel labelSalary = new JLabel("Salary");
		labelSalary.setBounds(20, 105, 150, 30);
		
		JTextField Salary = new JTextField(" 0");
		Salary.setBounds(190, 105, 150, 30);
		
		JLabel labelBonus = new JLabel("Bonus");
		labelBonus.setBounds(20, 145, 150, 30);
		
		JTextField Bonus = new JTextField(" 0");
		Bonus.setBounds(190, 145, 150, 30);
		
		JLabel labelDues = new JLabel("Dues");
		labelDues.setBounds(20, 185, 150, 30);
		
		JTextField Dues= new JTextField();
		Dues.setBounds(190,185,150,30);
		
		JLabel labelAdv = new JLabel("Advance");
		labelAdv.setBounds(20, 225, 150, 30);
		
		JTextField Adv= new JTextField(" 0");
		Adv.setBounds(190,225,150,30);
		
		JLabel labelTotal = new JLabel("Total");
		labelTotal.setBounds(20, 265, 150, 30);
		
		JTextField Total= new JTextField(" 0");
		Total.setBounds(190,265,150,30);
		
		JLabel labelNote = new JLabel("Note");
		labelNote.setBounds(20, 305, 150, 30);
		
		JTextArea Note= new JTextArea();
		Note.setBounds(190,305,150,100);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(450, 500, 100, 30);
		btnAdd.addActionListener(this);
         
		 this.add(comboboxEmployee);
		 this.add(labelEmployee);
		this.add(Month);
		this.add(labelMonth);
		this.add(labelSalary);
		this.add(labelBonus);
		this.add(labelDues);
		this.add(labelAdv);
		this.add(labelTotal);
		this.add(labelNote);
		this.add(Salary);
		this.add(Bonus);
		this.add(Dues);
		this.add(Adv);
		this.add(Total);
		this.add(Note);
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