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
		jp.setPreferredSize(new Dimension(prefWidth, 720));
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
		
		JLabel labelMobileNumber = new JLabel("Mobile");
		labelMobileNumber.setBounds(20, 365, 150, 30);
		
		JTextField mobileEmployee = new JTextField();
		mobileEmployee.setBounds(190, 365, width, 30);
		
		//JLabel labelMobileParents = new JLabel("Mobile(Parents)");
		//labelMobileParents.setBounds(20, 405, 150, 30);
		
		//JTextField mobileParents = new JTextField();
		//mobileParents.setBounds(190, 405, width, 30);
		
		//JLabel labelAcademicYear = new JLabel("Academic Year");
		//labelAcademicYear.setBounds(20, 465, 150, 30);
		
		//String AcademicYear[]={"2010-2011","2011-2012","2012-2013","2013-2014","2014-2015","2015-2016","2016-2017","2017-2018","2018-2019","2019-2020"};
		
		//JComboBox<String> comboboxAcademicYear = new JComboBox<String>(AcademicYear);
	    //comboboxAcademicYear.setBounds(190, 465, width, 30);
		
		//JLabel labelClass = new JLabel("Class");
		//labelClass.setBounds(20, 505, 150, 30);
		
		//String Class[]={"SSC","HSC","ADMISSION"};
		
		/*JComboBox<String> comboboxClass = new JComboBox<String>(Class);
	    comboboxClass.setBounds(190, 505, width, 30);
		
		JLabel labelSection = new JLabel("Section");
		labelSection.setBounds(20, 545, 150, 30);
		
		String Section[]={"A","B","C","D"};
		
		JComboBox<String> comboboxSection = new JComboBox<String>(Section);
	    comboboxSection.setBounds(190, 545, width, 30);
		
		JLabel labelInstitute = new JLabel("Institute");
		labelInstitute.setBounds(20, 585, 150, 30); 
		
		JTextField institute = new JTextField();
		institute.setBounds(190, 585, width, 30);
		
		JLabel labelAimInLife = new JLabel("Aim In Life");
		labelAimInLife.setBounds(20, 625, 150, 30);
		
		ButtonGroup groupAimInLife = new ButtonGroup(); 
		
		JRadioButton radiobuttonMedical = new JRadioButton("MEDICAL");
		radiobuttonMedical.setBounds(200, 625, 80, 30);
		
		JRadioButton radiobuttonEngineering = new JRadioButton("ENGINEERING");
		radiobuttonEngineering.setBounds(300, 625, 120, 30);
		
		JRadioButton radiobuttonOthers = new JRadioButton("OTHERS");
		radiobuttonOthers.setBounds(420, 625, 80, 30);*/
		
		JLabel labelEmail = new JLabel("Email");
		labelEmail.setBounds(20, 445, 150, 30); 
		
		JTextField Email = new JTextField();
		Email.setBounds(190, 445, width, 30);
		
		JLabel labelPassword = new JLabel("Password");
		labelPassword.setBounds(20, 485, 150, 30); 
		
		JPasswordField Password = new JPasswordField();
		Password.setBounds(190, 485, width, 30);
		
		JLabel labelSalary = new JLabel("Salary");
		labelSalary.setBounds(20, 565, 150, 30); 
		
		JTextField Salary = new JTextField();
		Salary.setBounds(190, 565, width, 30);
		
		JLabel labelHireDate = new JLabel("Hire Date");
		labelHireDate.setBounds(20, 605, 150, 30); 
		
		JTextField HireDate = new JTextField();
		HireDate.setBounds(190, 605, width, 30);
		
		JLabel labelResignationDate = new JLabel("Resignation Date");
		labelResignationDate.setBounds(20, 645, 150, 30); 
		
		JTextField ResignationDate = new JTextField();
		ResignationDate.setBounds(190, 645, width, 30);
		
		
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(190, 685, 100, 30);
		btnAdd.addActionListener(this);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(300, 685, 100, 30);
		btnCancel.addActionListener(this);

		jp.add(ResignationDate);
		jp.add(labelResignationDate);
		jp.add(HireDate);
		jp.add(labelHireDate);
		jp.add(Salary);
		jp.add(labelSalary);
		jp.add(Password);
		jp.add(labelPassword);
		jp.add(Email);
		jp.add(labelEmail);
		/*jp.add(radiobuttonOthers);
		jp.add(radiobuttonEngineering);
		jp.add(radiobuttonMedical);
		jp.add(labelAimInLife);
		jp.add(labelInstitute);
		jp.add(institute);
		jp.add(comboboxSection);
		jp.add(labelSection);
		jp.add(comboboxClass);
		jp.add(labelClass);
		jp.add(comboboxAcademicYear);
		jp.add(labelAcademicYear);*/
		//jp.add(mobileParents);
		//jp.add(labelMobileParents);
		jp.add(mobileEmployee);
		jp.add(labelMobileNumber);
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
		jp.add(btnAdd);
		jp.add(btnCancel);

		jp.add(titleBar("Personal Information", prefWidth, new Point(0, 0)));
		jp.add(titleBar("Contact Information", prefWidth, new Point(0, 300)));
		//jp.add(titleBar("Academic Information", prefWidth, new Point(0, 440)));
		jp.add(titleBar("Login Information", prefWidth, new Point(0, 405)));
		jp.add(titleBar("Official Information", prefWidth, new Point(0, 525)));
		
		JScrollPane sp = new JScrollPane(jp, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setBorder(BorderFactory.createEmptyBorder());

		this.setContentPane(sp);
		this.setResizable(false);
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