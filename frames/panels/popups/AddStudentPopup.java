package frames.panels.popups;

import javax.swing.*;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Font;
import library.apachelang3.StringUtils;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.*;
import java.time.*;
import java.util.HashMap;
import java.sql.SQLException;
import java.io.IOException;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;

import library.*;
import library.datepicker.main.components.*;
import library.datepicker.main.components.TimePickerSettings.TimeArea;
import library.datepicker.main.zinternaltools.*;
import frames.panels.StudentsPanel;
import frames.panels.core.*;
import entity.*;
import models.*;

public class AddStudentPopup extends JDialog implements ActionListener {

	DatePicker inputDateofBirth;
	HashMap<Integer, JcbItem<Section>> sections;
	JComboBox<JcbItem<Classes>> comboboxClass;
	JComboBox<JcbItem<Section>> comboboxSection;
	JComboBox<JcbItem<String>> comboboxBloodGroups;
	JComboBox<JcbItem<Session>> comboboxAcademicYear;
	JLabel previewProfilePicture;
	JButton btnBrowsePicture, btnAdd, btnCancel;
	JFileChooser fileChooser;
	String chosenAvatar;
	JFrame parent;
	JTextField inputFirstName, inputLastName, inputFatherName, inputMotherName, 
				inputEmail, inputAddress, inputMobileStudent, inputMobileParents, 
				inputInstitute;
	ButtonGroup groupAimInLife, groupGender; 
	JRadioButton radiobuttonMale, radiobuttonFemale, radiobuttonMedical, radiobuttonEngineering, radiobuttonOthers;
	JPasswordField inputPassword;

	StudentsPanel sparent;

	public AddStudentPopup(JFrame parent, StudentsPanel sparent) {
		super(parent, "Add New Student", true);
		this.parent = parent;
		this.sparent = sparent;
		this.setLayout(null);
		this.setLocationRelativeTo(null);

		int prefWidth = 574;
		int width = 358;

		JPanel jp = new JPanel();
		jp.setPreferredSize(new Dimension(prefWidth, 815));
		jp.setLayout(null);

		chosenAvatar = "";

		fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image Files (*.jpg, *.png)", new String[] { "png", "jpg", "jpeg" }));

		previewProfilePicture = new JLabel();
		previewProfilePicture.setBounds(190+width-92, 25, 100, 100);

		btnBrowsePicture = new JButton("Choose");
		btnBrowsePicture.setBounds(190+width-92, 130, 100, 20);
		btnBrowsePicture.addActionListener(this);

		try {
			previewProfilePicture.setIcon(Helper.squareImage( Helper.bufferedImage(new File(Configuration.PATH_AVATAR)), 100 ));
		} catch( IOException ex ) {}

		JLabel labelFirstName = new JLabel("First Name*");
		labelFirstName.setBounds(20, 25, 150, 30);
		
		inputFirstName = new JTextField();
		inputFirstName.setBounds(190, 25, width-102, 30);
		
		JLabel labelLastName = new JLabel("Last Name");
		labelLastName.setBounds(20, 65, 150, 30);
		
		inputLastName = new JTextField();
		inputLastName.setBounds(190, 65, width-102, 30);
		
		JLabel labelFatherName = new JLabel("Father's Name*");
		labelFatherName.setBounds(20, 105, 150, 30);
		
		inputFatherName = new JTextField();
		inputFatherName.setBounds(190, 105, width-102, 30);
		
		JLabel labelMotherName = new JLabel("Mother's Name*");
		labelMotherName.setBounds(20, 145, 150, 30);
		
		inputMotherName = new JTextField();
		inputMotherName.setBounds(190, 145, width-102, 30);
		
		JLabel labelGender = new JLabel("Gender*");
		labelGender.setBounds(20, 185, 150, 30);

		groupGender = new ButtonGroup(); 
		
		radiobuttonMale = new JRadioButton("MALE");
		radiobuttonMale.setBounds(200, 185, 80, 30);
		radiobuttonMale.setActionCommand("Male");
		
		radiobuttonFemale = new JRadioButton("FEMALE");
		radiobuttonFemale.setBounds(300, 185, 80, 30);
		radiobuttonFemale.setActionCommand("Female");

		groupGender.add(radiobuttonMale);
		groupGender.add(radiobuttonFemale);
		
		JLabel labelDateOfBirth = new JLabel("Date Of Birth*");
		labelDateOfBirth.setBounds(20, 225, 150, 30);
		
		DatePickerSettings dateofBirthSettings = new DatePickerSettings();
		LocalDate today = LocalDate.now();
		dateofBirthSettings.setAllowKeyboardEditing(false);
		dateofBirthSettings.setFirstDayOfWeek(DayOfWeek.SATURDAY);
		inputDateofBirth = new DatePicker(dateofBirthSettings);
		Configuration.setDatePickerIcon(inputDateofBirth.getComponentToggleCalendarButton());
		inputDateofBirth.setBounds(190, 225, width, 30);
		
		JLabel labelBloodGroup = new JLabel("Blood Group");
		labelBloodGroup.setBounds(20, 265, 150, 30);

		HashMap<String, JcbItem<String>> bloodGroups = new HashMap<String, JcbItem<String>>() {{
			put("A+", new JcbItem<String>("A positive (A+)", "A+"));
			put("A-", new JcbItem<String>("A negative (A-)", "A-"));
			put("B+", new JcbItem<String>("B positive (B+)", "B+"));
			put("B-", new JcbItem<String>("B negative (B-)", "B-"));
			put("O+", new JcbItem<String>("O positive (O+)", "O+"));
			put("O-", new JcbItem<String>("O negative (O-)", "O-"));
			put("AB+", new JcbItem<String>("AB positive (AB+)", "AB+"));
			put("AB-", new JcbItem<String>("AB negative (AB-)", "AB-"));
		}};
		
		comboboxBloodGroups = new JComboBox<JcbItem<String>>();
	    comboboxBloodGroups.setBounds(190, 265, width, 30);

	    Helper.initializeCombo(comboboxBloodGroups, bloodGroups, null, "Select Blood Group");
		
		JLabel labelAddress = new JLabel("Address*");
		labelAddress.setBounds(20, 325, 150, 30);
		
		inputAddress = new JTextField();
		inputAddress.setBounds(190, 325, width, 30);
		
		JLabel labelMobileStudent = new JLabel("Mobile(Student)");
		labelMobileStudent.setBounds(20, 365, 150, 30);
		
		inputMobileStudent = new JTextField();
		inputMobileStudent.setBounds(190, 365, width, 30);
		
		JLabel labelMobileParents = new JLabel("Mobile(Parents)*");
		labelMobileParents.setBounds(20, 405, 150, 30);
		
		inputMobileParents = new JTextField();
		inputMobileParents.setBounds(190, 405, width, 30);
		
		JLabel labelAcademicYear = new JLabel("Academic Year*");
		labelAcademicYear.setBounds(20, 465, 150, 30);
		
		HashMap<Integer, JcbItem<Session>> academicYears = null;

		try {
			MSession mSession = new MSession();
			academicYears = mSession.retrive();
		} catch (DatabaseException | SQLException ex) {}

		comboboxAcademicYear = new JComboBox<JcbItem<Session>>();
	    comboboxAcademicYear.setBounds(190, 465, width, 30);

	    if(academicYears!=null) {
	    	Helper.initializeCombo(comboboxAcademicYear, academicYears, null, "Select Academic Year");
	    }
		
		JLabel labelClass = new JLabel("Class*");
		labelClass.setBounds(20, 505, 150, 30);
		
		HashMap<Integer, JcbItem<Classes>> classes = null;

		try {
			MClasses mClasses = new MClasses();
			classes = mClasses.retrive();
		} catch (DatabaseException | SQLException ex) {}
		
		comboboxClass = new JComboBox<JcbItem<Classes>>();
	    comboboxClass.setBounds(190, 505, width, 30);

	    if(classes!=null) {
		    Helper.initializeCombo(comboboxClass, classes, null, "Select Class");
		}
		
		JLabel labelSection = new JLabel("Section*");
		labelSection.setBounds(20, 545, 150, 30);
		
		sections = null;
		
		comboboxSection = new JComboBox<JcbItem<Section>>();
	    comboboxSection.setBounds(190, 545, width, 30);

	    Helper.initializeCombo(comboboxSection, sections, null, "Select Class First");


	    comboboxClass.addActionListener(this);
		
		JLabel labelInstitute = new JLabel("Institute*");
		labelInstitute.setBounds(20, 585, 150, 30); 
		
		inputInstitute = new JTextField();
		inputInstitute.setBounds(190, 585, width, 30);
		
		JLabel labelAimInLife = new JLabel("Aim In Life*");
		labelAimInLife.setBounds(20, 625, 150, 30);
		
		groupAimInLife = new ButtonGroup(); 
		
		radiobuttonMedical = new JRadioButton("MEDICAL");
		radiobuttonMedical.setBounds(200, 625, 80, 30);
		radiobuttonMedical.setActionCommand("Medical");

		radiobuttonEngineering = new JRadioButton("ENGINEERING");
		radiobuttonEngineering.setBounds(300, 625, 120, 30);
		radiobuttonEngineering.setActionCommand("Engineering");
		
		radiobuttonOthers = new JRadioButton("OTHERS");
		radiobuttonOthers.setBounds(420, 625, 80, 30);
		radiobuttonOthers.setActionCommand("Others");

		groupAimInLife.add(radiobuttonMedical);
		groupAimInLife.add(radiobuttonEngineering);
		groupAimInLife.add(radiobuttonOthers);

		JLabel labelEmail = new JLabel("Email*");
		labelEmail.setBounds(20, 700, 150, 30); 
		
		inputEmail = new JTextField();
		inputEmail.setBounds(190, 700, width, 30);
		
		JLabel labelPassword = new JLabel("Password*");
		labelPassword.setBounds(20, 740, 150, 30); 
		
		inputPassword = new JPasswordField(String.valueOf(Helper.generateRandom(6)));
		inputPassword.setBounds(190, 740, width, 30);
		
		btnAdd = new JButton("Add");
		btnAdd.setBounds(190, 780, 100, 30);
		btnAdd.addActionListener(this);

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(300, 780, 100, 30);
		btnCancel.addActionListener(this);

		jp.add(previewProfilePicture);
		jp.add(btnBrowsePicture);
		jp.add(inputPassword);
		jp.add(labelPassword);
		jp.add(inputEmail);
		jp.add(labelEmail);
		jp.add(radiobuttonOthers);
		jp.add(radiobuttonEngineering);
		jp.add(radiobuttonMedical);
		jp.add(labelAimInLife);
		jp.add(labelInstitute);
		jp.add(inputInstitute);
		jp.add(comboboxSection);
		jp.add(labelSection);
		jp.add(comboboxClass);
		jp.add(labelClass);
		jp.add(comboboxAcademicYear);
		jp.add(labelAcademicYear);
		jp.add(inputMobileParents);
		jp.add(labelMobileParents);
		jp.add(inputMobileStudent);
		jp.add(labelMobileStudent);
		jp.add(inputAddress);
		jp.add(labelAddress);
		jp.add(comboboxBloodGroups);
		jp.add(labelBloodGroup);
		jp.add(inputDateofBirth);
		jp.add(labelDateOfBirth);
		jp.add(radiobuttonFemale);
		jp.add(radiobuttonMale);
		jp.add(labelGender);
		jp.add(inputMotherName);
		jp.add(labelMotherName);
		jp.add(inputFatherName);
		jp.add(labelFatherName);
		jp.add(inputLastName);
		jp.add(labelLastName);
		jp.add(inputFirstName);
		jp.add(labelFirstName);
		jp.add(btnAdd);
		jp.add(btnCancel);

		jp.add(titleBar("Personal Information", prefWidth, new Point(0, 0)));
		jp.add(titleBar("Contact Information", prefWidth, new Point(0, 300)));
		jp.add(titleBar("Academic Information", prefWidth, new Point(0, 440)));
		jp.add(titleBar("Login Information", prefWidth, new Point(0, 660)));

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
	    if(e.getSource()==comboboxClass) {
	    	comboboxSection.removeAllItems();
	    	Classes _class = (Classes) ((JcbItem) comboboxClass.getSelectedItem()).getKey();
	    	if(_class!=null) {
	    		try {
					MSection mSection = new MSection();
					sections = mSection.retrive(_class);
				} catch (DatabaseException | SQLException ex) {}
				Helper.initializeCombo(comboboxSection, sections, null, "Select Section");
	    	} else {
	    		sections = null;
	    		Helper.initializeCombo(comboboxSection, sections, null, "Select Class First");
	    	}
	    	
	    } else if(e.getSource()==btnBrowsePicture) {
	    	boolean flaq = false;
			if (fileChooser.showDialog(parent, "Open") == JFileChooser.APPROVE_OPTION) {
				String chosenPath = fileChooser.getSelectedFile().toString();
				if( !StringUtils.isEmpty(chosenPath) ) {
					File chosenFile = new File(chosenPath);
					if(chosenFile.exists()) {
						try {
							flaq = true;
							chosenAvatar = chosenPath;
							previewProfilePicture.setIcon(Helper.squareImage( Helper.bufferedImage(chosenFile), 100 ));
						} catch (IOException ex) {}
					} 
				}
		    } 

		    if(!flaq) {
		    	try {
					chosenAvatar = "";
					previewProfilePicture.setIcon(Helper.squareImage( Helper.bufferedImage(new File(Configuration.PATH_AVATAR)), 100 ));
				} catch (IOException ex) {}
		    }
		} else if(e.getSource()==btnAdd) {
			addStudent();
	    } else {
	    	dispose();
	    }
	}

	private void addStudent() {
		String firstName = inputFirstName.getText(); //required
		String lastName = inputLastName.getText();
		String fatherName = inputFatherName.getText(); //required
		String motherName = inputMotherName.getText(); //required
		String dateofBirth = inputDateofBirth.getDateStringOrSuppliedString("0000-00-00"); //required
		String gender = Helper.getSelectedValue(groupGender); //required
		String bloodGroup = (String) Helper.getSelectedValue(comboboxBloodGroups);
				bloodGroup = bloodGroup!=null ? bloodGroup : "";
		//
		String address = inputAddress.getText(); //required
		String mobileStudent = inputMobileStudent.getText();
		String mobileParents = inputMobileParents.getText(); //required
		//
		Session academicYear = (Session) Helper.getSelectedValue(comboboxAcademicYear); //required
		Classes _class = (Classes) Helper.getSelectedValue(comboboxClass); //required
		Section section = (Section) Helper.getSelectedValue(comboboxSection); //required
		String institute = inputInstitute.getText();  //required
		String aimInLife = Helper.getSelectedValue(groupAimInLife); //required
		//
		String email = inputEmail.getText(); //required
		String password = inputPassword.getText(); //required
		
		if(StringUtils.isEmpty(firstName) || StringUtils.isEmpty(fatherName) || 
			StringUtils.isEmpty(motherName) || StringUtils.isEmpty(dateofBirth) ||
			StringUtils.isEmpty(gender) || StringUtils.isEmpty(address) || 
			StringUtils.isEmpty(mobileParents) || academicYear==null || _class==null || 
			section==null || StringUtils.isEmpty(institute) || StringUtils.isEmpty(aimInLife) || 
			StringUtils.isEmpty(email) || StringUtils.isEmpty(password) ) {
			JOptionPane.showMessageDialog(parent, "Required fields are missing.\nAll the * markerd\nfields are required", "ERROR OCCURED !!",  JOptionPane.ERROR_MESSAGE);
		} else if(firstName.length()<4) {
			JOptionPane.showMessageDialog(parent, "Invalid First Name", "ERROR OCCURED !!",  JOptionPane.ERROR_MESSAGE);
		} else if(fatherName.length()<4) {
			JOptionPane.showMessageDialog(parent, "Invalid Father Name", "ERROR OCCURED !!",  JOptionPane.ERROR_MESSAGE);
		} else if(motherName.length()<4) {
			JOptionPane.showMessageDialog(parent, "Invalid Mother Name", "ERROR OCCURED !!",  JOptionPane.ERROR_MESSAGE);
		} else if(!StringUtils.isEmpty(mobileStudent) && !Helper.verifyPhoneNo(mobileStudent)) {
			JOptionPane.showMessageDialog(parent, "Invalid Mobile Number (Student)", "ERROR OCCURED !!",  JOptionPane.ERROR_MESSAGE);
		} else if(!Helper.verifyPhoneNo(mobileParents)) {
			JOptionPane.showMessageDialog(parent, "Invalid Mobile Number (Parents)", "ERROR OCCURED !!",  JOptionPane.ERROR_MESSAGE);
		} else if(!Helper.isValidEmail(email)) {
			JOptionPane.showMessageDialog(parent, "Invalid Email Address", "ERROR OCCURED !!",  JOptionPane.ERROR_MESSAGE);
		} else if(Helper.emailAssigned(email)) {
			JOptionPane.showMessageDialog(parent, "Email Already Exists", "ERROR OCCURED !!",  JOptionPane.ERROR_MESSAGE);
		} else if(password.length()<6) {
			JOptionPane.showMessageDialog(parent, "Weak Password. It should be 6 digits long", "ERROR OCCURED !!",  JOptionPane.ERROR_MESSAGE);
		} else {

			String profilePic = "";
			try {

				if(!StringUtils.isEmpty(chosenAvatar)) {
					FileManager fileManager = new FileManager("new_file", Configuration.PATH_UPLOAD);
					profilePic = fileManager.copy(chosenAvatar, true);
				}

				if(StringUtils.isEmpty(chosenAvatar) || !StringUtils.isEmpty(profilePic)) {
					MUser mUser = new MUser();
					MSessionMeta mSessionMeta = new MSessionMeta();
					MUserMeta mUsermeta = new MUserMeta();

					User user = new User(
						null,
						new Email(email),
						HashManager.md5(password),
						Configuration.ROLE_STUDENT,
						LocalDateTime.now()
					);

					int userid = mUser.insert(user);

					SessionMeta sessionMeta = new SessionMeta(
						null,
						academicYear.getId(),
						section.getId(),
						BigInteger.valueOf(userid),
						null,
						null
					);

					int roll = mSessionMeta.insert(sessionMeta);

					ArrayList<UserMeta> metas = new ArrayList<UserMeta>();

					metas.add(new UserMeta(
						null,
						BigInteger.valueOf(userid),
						Configuration.META_FNAME,
						firstName
					));

					metas.add(new UserMeta(
						null,
						BigInteger.valueOf(userid),
						Configuration.META_LNAME,
						lastName
					));

					metas.add(new UserMeta(
						null,
						BigInteger.valueOf(userid),
						Configuration.META_FATHER_NAME,
						fatherName
					));

					metas.add(new UserMeta(
						null,
						BigInteger.valueOf(userid),
						Configuration.META_MOTHER_NAME,
						motherName
					));

					metas.add(new UserMeta(
						null,
						BigInteger.valueOf(userid),
						Configuration.META_GENDER,
						gender
					));

					metas.add(new UserMeta(
						null,
						BigInteger.valueOf(userid),
						Configuration.META_DOB,
						dateofBirth
					));

					metas.add(new UserMeta(
						null,
						BigInteger.valueOf(userid),
						Configuration.META_BLOOD_GROUP,
						bloodGroup
					));
					
					metas.add(new UserMeta(
						null,
						BigInteger.valueOf(userid),
						Configuration.META_ADDRESS,
						address
					));

					metas.add(new UserMeta(
						null,
						BigInteger.valueOf(userid),
						Configuration.META_MOBILE,
						Helper.cleanPhoneNo(mobileStudent)
					));

					metas.add(new UserMeta(
						null,
						BigInteger.valueOf(userid),
						Configuration.META_MOBILE_PARENT,
						Helper.cleanPhoneNo(mobileParents)
					));

					metas.add(new UserMeta(
						null,
						BigInteger.valueOf(userid),
						Configuration.META_INSTITUTE,
						institute
					));

					metas.add(new UserMeta(
						null,
						BigInteger.valueOf(userid),
						Configuration.META_AIM_IN_LIFE,
						aimInLife
					));

					metas.add(new UserMeta(
						null,
						BigInteger.valueOf(userid),
						Configuration.META_PROFILE_PIC,
						profilePic
					));

					int inserted = mUsermeta.insert(metas);

					if(inserted>0) {
						JOptionPane.showMessageDialog(parent, "Student "+firstName+" "+lastName+" Added\nRoll: "+roll+"\nPassword: "+password, "Student Added Successfully !!",  JOptionPane.INFORMATION_MESSAGE);
						sparent.reDrawTable();
						dispose();
					} else {
						JOptionPane.showMessageDialog(parent, "Something went wrong. PLease contact system developer", "ERROR OCCURED !!",  JOptionPane.ERROR_MESSAGE);
					}

				} else {
					JOptionPane.showMessageDialog(parent, "Please Fix Image Upload Issue", "ERROR OCCURED !!",  JOptionPane.ERROR_MESSAGE);
				}
			} catch(DatabaseException | SQLException| IOException ex) {
				JOptionPane.showMessageDialog(parent, ex.getMessage(), "ERROR OCCURED !!",  JOptionPane.ERROR_MESSAGE);
			}

		}
	}
}