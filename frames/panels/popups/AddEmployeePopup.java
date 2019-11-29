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

public class AddEmployeePopup extends JDialog implements ActionListener {
	public AddEmployeePopup(JFrame jf) {
		super(jf, "Add New Employee", true);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		
		int prefWidth = 566;
		int width = 350;
		
		JPanel jp = new JPanel();
		jp.setPreferredSize(new Dimension(prefWidth, 750));
		jp.setLayout(null);

		JLabel labelFirstName = new JLabel("First Name");
		labelFirstName.setBounds(20, 25, 150, 30);
		
		JTextField firstName = new JTextField();
		firstName.setBounds(190, 25, width, 30);
		
		JLabel labelLastName = new JLabel("Last Name");
		labelLastName.setBounds(20, 65, 150, 30);
		
		JTextField lastName = new JTextField();
		lastName.setBounds(190, 65, width, 30);
		
		JLabel labelFatherName = new JLabel("Father's Name");
		labelFatherName.setBounds(20, 105, 150, 30);
		
		JTextField fatherName = new JTextField();
		fatherName.setBounds(190, 105, width, 30);
		
		JLabel labelMotherName = new JLabel("Mother's Name");
		labelMotherName.setBounds(20, 145, 150, 30);
		
		JTextField motherName = new JTextField();
		motherName.setBounds(190, 145, width, 30);
		
		JLabel labelGender = new JLabel("Gender");
		labelGender.setBounds(20, 185, 150, 30);

		ButtonGroup groupGender = new ButtonGroup(); 
		
		JRadioButton radiobuttonMale = new JRadioButton("MALE");
		radiobuttonMale.setBounds(200, 185, 80, 30);
		
		JRadioButton radiobuttonFemale = new JRadioButton("FEMALE");
		radiobuttonFemale.setBounds(300, 185, 80, 30);

		groupGender.add(radiobuttonMale);
		groupGender.add(radiobuttonFemale);
		
		JLabel labelDateOfBirth = new JLabel("Date Of Birth");
		labelDateOfBirth.setBounds(20, 225, 150, 30);
		
		JTextField dateofBirth = new JTextField();
		dateofBirth.setBounds(190, 225, width, 30);
		
		JLabel labelBloodGroup = new JLabel("Blood Group");
		labelBloodGroup.setBounds(20, 265, 150, 30);
		
		String BloodGrous[]={"A positive (A+)","A negative (A-) ","B positive (B+)","B negative (B-)","O positive (O+)","O negative (O-)","AB positive (AB+)","AB negative (AB-)"};
		
		JComboBox<String> comboboxBloodGroups = new JComboBox<String>(BloodGrous);
	    comboboxBloodGroups.setBounds(190, 265, width, 30);
		
		JLabel labelAddress = new JLabel("Address");
		labelAddress.setBounds(20, 325, 150, 30);
		
		JTextField address = new JTextField();
		address.setBounds(190, 325, width, 30);
		
		JLabel labelMobileEmployee = new JLabel("Mobile");
		labelMobileEmployee.setBounds(20, 365, 150, 30);
		
		JTextField mobileEmployee = new JTextField();
		mobileEmployee.setBounds(190, 365, width, 30);
		
		JLabel labelEmail = new JLabel("Email");
		labelEmail.setBounds(20, 438, 150, 30);
		
		JTextField Email = new JTextField();
		Email.setBounds(190, 438, width, 30);
		
		JLabel labelPass = new JLabel("Password");
		labelPass.setBounds(20, 478, 150, 30);
		
		JPasswordField Pass = new JPasswordField();
		Pass.setBounds(190, 478, width, 30);
		
		JLabel labelSalary = new JLabel("Salary");
		labelSalary.setBounds(20, 538, 150, 30);
		
		JTextField Salary = new JTextField();
		Salary.setBounds(190, 538, width, 30);
		
		JLabel labelHire = new JLabel("Hire Date");
		labelHire.setBounds(20, 578, 150, 30);
		
		JTextField Hire = new JTextField();
		Hire.setBounds(190, 578, width, 30);
		
		JLabel labelResign = new JLabel("Resignation Date");
		labelResign.setBounds(20, 618, 150, 30);
		
		JTextField Resign = new JTextField();
		Resign.setBounds(190,618, width, 30);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(250, 700, 100, 30);
		btnAdd.addActionListener(this);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(400, 700, 100, 30);
		btnCancel.addActionListener(this);
        
		jp.add(btnCancel);
		jp.add(btnAdd);
		jp.add(Salary);
		jp.add(labelSalary);
		jp.add(Hire);
		jp.add(labelHire);
		jp.add(Resign);
		jp.add(labelResign);
		jp.add(Pass);
		jp.add(labelPass);
		jp.add(Email);
		jp.add(labelEmail);
		jp.add(mobileEmployee);
		jp.add(labelMobileEmployee);
		jp.add(address);
		jp.add(labelAddress);
		jp.add(comboboxBloodGroups);
		jp.add(labelBloodGroup);
		jp.add(dateofBirth);
		jp.add(labelDateOfBirth);
		jp.add(radiobuttonFemale);
		jp.add(radiobuttonMale);
		jp.add(labelGender);
		jp.add(motherName);
		jp.add(labelMotherName);
		jp.add(fatherName);
		jp.add(labelFatherName);
		jp.add(lastName);
		jp.add(labelLastName);
		jp.add(firstName);
		jp.add(labelFirstName);
		
		
		jp.add(titleBar("Personal Information", prefWidth, new Point(0, 0)));
		jp.add(titleBar("Contact Information", prefWidth, new Point(0, 300)));
		jp.add(titleBar("Login Information", prefWidth, new Point(0, 410)));
		jp.add(titleBar("Official Information", prefWidth, new Point(0, 510)));
		
		
		JScrollPane sp = new JScrollPane(jp, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setBorder(BorderFactory.createEmptyBorder());

		this.setContentPane(sp);

		
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
	
	private JPanel titleBar( String text, int width, Point location) {
		
		SpringLayout sl = new SpringLayout();
		JPanel tpanel = new JPanel();
		tpanel.setLocation(location);
		tpanel.setSize(width, 20);
		tpanel.setLayout(sl);
		tpanel.setBackground(new Color(199,203,209));

		JLabel title = new JLabel(text);
		title.setFont( Configuration.getFont(Configuration.FONT_REGULAR, Font.BOLD, 12) );
		tpanel.add(title);

		sl.putConstraint(SpringLayout.WEST, title, 20, SpringLayout.WEST, tpanel);
		sl.putConstraint(SpringLayout.NORTH, title, 2, SpringLayout.NORTH, tpanel);

		return tpanel;
	}

	public void actionPerformed(ActionEvent e) {
	    dispose();
	}
}