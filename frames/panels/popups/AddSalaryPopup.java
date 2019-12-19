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
	JScrollPane sp;
	public AddSalaryPopup(JFrame jf) {
		super(jf, "Add Salary", true);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		
		
		JLabel labelEmployee = new JLabel("Employee");
		labelEmployee.setBounds(20, 25, 150, 30);
		
		String Emp[]={"Rashed","Captain","Munira"};
		JComboBox<String> comboboxEmployee = new JComboBox<String>(Emp);
	    comboboxEmployee.setBounds(100, 25, 250, 30);
		
		JLabel labelMonth = new JLabel("Month");
		labelMonth.setBounds(20, 65, 150, 30);
		
		JTextField Month = new JTextField();
		Month.setBounds(100, 65, 250, 30);
		
		JLabel labelSalary = new JLabel("Salary");
		labelSalary.setBounds(20, 105, 150, 30);
		
		JTextField salary = new JTextField(" 0");
		salary.setBounds(100, 105, 250, 30);
		
		JLabel labelBonus = new JLabel("Bonus");
		labelBonus.setBounds(20, 145, 150, 30);
		
		JTextField bonus = new JTextField(" 0");
		bonus.setBounds(100, 145, 250, 30);
		
		JLabel labelDues = new JLabel("Dues");
		labelDues.setBounds(20, 185, 150, 30);
		
		JTextField dues= new JTextField();
		dues.setBounds(100,185,250,30);
		
		JLabel labelAdv = new JLabel("Advance");
		labelAdv.setBounds(20, 225, 150, 30);
		
		JTextField adv= new JTextField(" 0");
		adv.setBounds(100,225,250,30);
		
		JLabel labelTotal = new JLabel("Total");
		labelTotal.setBounds(20, 265, 150, 30);
		
		JTextField total= new JTextField(" 0");
		total.setBounds(100,265,250,30);
		
		JLabel labelNote = new JLabel("Note");
		labelNote.setBounds(20, 305, 150, 30);
		
		JTextArea note= new JTextArea();
		note.setPreferredSize(new Dimension(250,350));
		note.setLayout(null);

		sp = new JScrollPane(note, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(202, 202, 202)));
		sp.setBounds(100,305,250,100);
		sp.setVisible(true);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(220, 445, 100, 30);
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
		this.add(salary);
		this.add(bonus);
		this.add(dues);
		this.add(adv);
		this.add(total);
		this.add(sp);
		this.add(btnAdd);  
		this.setResizable(false);
		setExtendedSize(new Dimension(400,530));    
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