package frames.panels;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import library.apachelang3.StringUtils;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Point;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Image;
import java.sql.SQLException;
import java.awt.FileDialog;
import java.awt.event.*;
import java.io.*;

import models.*;
import library.*;
import entity.*;
import frames.panels.core.*;

public class SettingsPanel extends TitledPanel implements ActionListener {

	private Point coordinates;
	private User user;
	private JPanel profilePanel, psswordPanel, databasePanel;
	private String changedAvatar;
	private ImageIcon defaultAvatar;
	private JLabel previewProfilePicture;
	private JFileChooser fileChooser;
	private JFrame parent;
	private JButton btnSaveProfile, btnBrowsePicture, btnChangePassword;
	private JTextField inputEmailAddress, inputFirstName, inputLastName, inputMobileNo;
	private HeaderPanel headerPanel;
	private JPasswordField inputOldPassword, inputNewPassword, inputConfirmPassword;

	public SettingsPanel(User user, JFrame parent, HeaderPanel headerPanel, Point coordinates, Dimension dimension) {
		super(dimension);
		this.coordinates = coordinates;
		this.user = user;
		this.parent = parent;
		this.headerPanel = headerPanel;
		Construct();
	}

	public SettingsPanel(User user, JFrame parent, HeaderPanel headerPanel, Point coordinates, Dimension dimension, boolean isDoubleBuffered) {
		super(dimension, isDoubleBuffered);
		this.coordinates = coordinates;
		this.parent = parent;
		this.headerPanel = headerPanel;
		Construct();
	}

	public SettingsPanel(User user, JFrame parent, HeaderPanel headerPanel, Point coordinates, Dimension dimension, LayoutManager layout) {
		super(dimension, layout);
		this.coordinates = coordinates;
		this.user = user;
		this.parent = parent;
		this.headerPanel = headerPanel;
		Construct();
	}

	public SettingsPanel(User user, JFrame parent, HeaderPanel headerPanel, Point coordinates, Dimension dimension, LayoutManager layout, boolean isDoubleBuffered) {
		super(dimension, layout, isDoubleBuffered);
		this.coordinates = coordinates;
		this.user = user;
		this.parent = parent;
		this.headerPanel = headerPanel;
		Construct();
	}

	private void Construct() {

		profilePanel = new JPanel(); 
		psswordPanel = new JPanel(); 
		
	    JTabbedPane tabbedPanel = new JTabbedPane();

		tabbedPanel.add("Profile", profilePanel);  
		tabbedPanel.add("Change Password", psswordPanel);  

        profilePanel();
        passwordPanel();
	
		tabbedPanel.setBackground(new Color(199,203,209));	
		tabbedPanel.setLocation(locBody);
		tabbedPanel.setSize(sizeBody);
		this.add(tabbedPanel);
		this.setTitle("Settings");
		this.setBackground(new Color(255,255,255));
		this.setLocation(coordinates);
		this.setSize(dimension);
		disableAdd();
	}

	private void profilePanel() {
		profilePanel.setBackground(new Color(255,255,255));
		profilePanel.setLayout(null);

		String oldFirstName = "";
		String oldLastName = "";
		String oldMobileNo = "";

		changedAvatar = "";
		defaultAvatar = null;

		fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image Files (*.jpg, *.png)", new String[] { "png", "jpg", "jpeg" }));
		try {

			MUserMeta mUserMeta = new MUserMeta();
			UserMeta metaFirstName = mUserMeta.retrive(user.getId(), Configuration.META_FNAME);
			UserMeta metaLastName = mUserMeta.retrive(user.getId(), Configuration.META_LNAME);
			UserMeta metaMobileNo = mUserMeta.retrive(user.getId(), Configuration.META_MOBILE);

			oldFirstName = metaFirstName.getValue();
			oldLastName = metaLastName.getValue();
			oldMobileNo = metaMobileNo.getValue();

		} catch(DatabaseException | SQLException ex) {
			JOptionPane.showMessageDialog(parent, ex.getMessage(), "ERROR OCCURED !!", JOptionPane.ERROR_MESSAGE);
		}
        

		JLabel labelEmailAddress = new JLabel("Email Address");
        JLabel labelFirstName = new JLabel("First Name");
        JLabel labelLastName = new JLabel("Last Name");
        JLabel labelMobileNo = new JLabel("Mobile No");
        JLabel labelProfilePicture = new JLabel("Profile Picture");

        btnBrowsePicture = new JButton("Choose");
        btnSaveProfile = new JButton("Save");

		previewProfilePicture = new JLabel();
		inputEmailAddress = new JTextField(user.getEmail().get());
		inputFirstName = new JTextField(oldFirstName);
		inputLastName = new JTextField(oldLastName);
		inputMobileNo = new JTextField(oldMobileNo);

		labelEmailAddress.setBounds(20, 20, 150, 30); 
		inputEmailAddress.setBounds(150, 20, 200, 30); 
		inputEmailAddress.setBackground( new Color(230,237,242) ); 

		labelFirstName.setBounds(20, 70, 150, 30); 
		inputFirstName.setBounds(150, 70, 200, 30); 
		inputFirstName.setBackground( new Color(230,237,242) );

		labelLastName.setBounds(20, 120, 150, 30); 
		inputLastName.setBounds(150, 120, 200, 30); 
		inputLastName.setBackground( new Color(230,237,242) );

		labelMobileNo.setBounds(20, 170, 150, 30); 
		inputMobileNo.setBounds(150, 170, 200, 30);
		inputMobileNo.setBackground( new Color(230,237,242) ); 	

		labelProfilePicture.setBounds(20, 220, 150, 30);
        previewProfilePicture.setBounds(150, 220, 100, 100);
        btnBrowsePicture.setBounds(150, 325, 100, 20);

		btnSaveProfile.setBounds(150, 370, 100, 30);

		try {
			previewProfilePicture.setIcon(Helper.squareImage( Helper.bufferedImage(Helper.getAvatar(user)), 100 ));
		} catch( DatabaseException | SQLException | IOException ex ) {
			JOptionPane.showMessageDialog(parent, ex.getMessage(), "ERROR OCCURED !!", JOptionPane.ERROR_MESSAGE);
		}

		btnBrowsePicture.addActionListener(this);
		btnSaveProfile.addActionListener(this);

		profilePanel.add(labelEmailAddress);
		profilePanel.add(inputEmailAddress);
		profilePanel.add(labelFirstName);
		profilePanel.add(inputFirstName);
		profilePanel.add(labelLastName);
		profilePanel.add(inputLastName);
		profilePanel.add(labelMobileNo);
		profilePanel.add(inputMobileNo);
		profilePanel.add(labelProfilePicture);
		profilePanel.add(previewProfilePicture);
		profilePanel.add(btnBrowsePicture);
		profilePanel.add(btnSaveProfile);
	}

	private void passwordPanel() {
		psswordPanel.setBackground(new Color(255,255,255));
		psswordPanel.setLayout(null);

		JLabel labelOldPassword = new JLabel("Old Password");
		inputOldPassword = new JPasswordField();
		inputOldPassword.setBackground( new Color(230,237,242) );

		labelOldPassword.setBounds(20, 20, 150, 30);
		inputOldPassword.setBounds(150, 20, 200, 30);
		
		JLabel labelNewPassword = new JLabel("New Password");
		inputNewPassword = new JPasswordField();
		inputNewPassword.setBackground( new Color(230,237,242) );

		labelNewPassword.setBounds(20, 70, 150, 30);
		inputNewPassword.setBounds(150, 70, 200, 30);
		
		JLabel labelConfirmPassword = new JLabel("Confirm Password");
		inputConfirmPassword = new JPasswordField();
		inputConfirmPassword.setBackground( new Color(230,237,242) );

		labelConfirmPassword.setBounds(20, 120, 150, 30);
		inputConfirmPassword.setBounds(150, 120, 200, 30);

		btnChangePassword = new JButton("Change");
		btnChangePassword.setBounds(150, 170, 100, 30);

		btnChangePassword.addActionListener(this);

		psswordPanel.add(labelOldPassword);
		psswordPanel.add(inputOldPassword);
		psswordPanel.add(labelNewPassword);
		psswordPanel.add(inputNewPassword);
		psswordPanel.add(labelConfirmPassword);
		psswordPanel.add(inputConfirmPassword);
		psswordPanel.add(btnChangePassword);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==btnSaveProfile) {
			String emailAddress = inputEmailAddress.getText();
			String firstName = inputFirstName.getText();
			String lastName = inputLastName.getText();
			String mobileNo = inputMobileNo.getText();

			if(StringUtils.isEmpty(emailAddress)) {
				JOptionPane.showMessageDialog(parent, "Email cannot be empty", "ERROR OCCURED !!",JOptionPane.ERROR_MESSAGE);
			} else if(!Helper.isValidEmail(emailAddress)) {
				JOptionPane.showMessageDialog(parent, "Invalid email address", "ERROR OCCURED !!",  JOptionPane.ERROR_MESSAGE);
			} else if(Helper.emailExists(emailAddress) && !emailAddress.equals(user.getEmail().get())) {
				JOptionPane.showMessageDialog(parent, "Email already used", "ERROR OCCURED !!", JOptionPane.ERROR_MESSAGE);
			} else if(StringUtils.isEmpty(firstName+lastName)) {
				JOptionPane.showMessageDialog(parent, "First/Last Name cannot be empty", "ERROR OCCURED !!", JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					
					boolean flaq = false;

					MUser mUser = new MUser();
					MUserMeta mUserMeta = new MUserMeta();

					if(!emailAddress.equals(user.getEmail().get())) {
						user.getEmail().set(emailAddress);
						mUser.update(user);
						flaq = true;
					}

					if(!StringUtils.isEmpty(firstName)) {
						headerPanel.setUserName(firstName);
						mUserMeta.update(user, Configuration.META_FNAME, firstName);
						flaq = true;
					}

					if(!StringUtils.isEmpty(lastName)) {
						mUserMeta.update(user, Configuration.META_LNAME, lastName);
						flaq = true;
					}

					if(!StringUtils.isEmpty(mobileNo)) {
						mUserMeta.update(user, Configuration.META_MOBILE, mobileNo);
						flaq = true;
					}

					if(!StringUtils.isEmpty(changedAvatar)) {
						FileManager fileManager = new FileManager("new_file", Configuration.PATH_UPLOAD);
						String filename = fileManager.copy(changedAvatar, true);
						mUserMeta.update(user, Configuration.META_PROFILE_PIC, filename);
						headerPanel.setUserAvatar(new File(Configuration.PATH_UPLOAD + filename));
						flaq = true;
					}

					if(flaq) {
						JOptionPane.showMessageDialog(parent, "Successful !", "Profile Updated Successfully", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				catch( DatabaseException | SQLException | IOException ex ) {
					JOptionPane.showMessageDialog(parent, ex.getMessage(), "ERROR OCCURED !!", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if(e.getSource()==btnBrowsePicture) {
			if (fileChooser.showDialog(parent, "Open") == JFileChooser.APPROVE_OPTION) {
				String chosenPath = fileChooser.getSelectedFile().toString();
				if( !StringUtils.isEmpty(chosenPath) ) {
					File chosenFile = new File(chosenPath);
					if(chosenFile.exists()) {
						try {
							changedAvatar = chosenPath;
							previewProfilePicture.setIcon(Helper.squareImage( Helper.bufferedImage(chosenFile), 100 ));
						} catch (IOException ex) {
							JOptionPane.showMessageDialog(parent, ex.getMessage(), "ERROR OCCURED !!", JOptionPane.ERROR_MESSAGE);
						}
					} 
				}
		    }
		} else if(e.getSource()==btnChangePassword) {

			String oldPassword = inputOldPassword.getText();
			String newPassword = inputNewPassword.getText();
			String confirmPassword = inputConfirmPassword.getText();

			String newPassHash = "";

			if(StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(newPassword) || StringUtils.isEmpty(confirmPassword)) {
				JOptionPane.showMessageDialog(parent, "All the fields are required", "ERROR OCCURED !!", JOptionPane.ERROR_MESSAGE);
			} else if(!user.getPassHash().equals(HashManager.md5(oldPassword))) {
				JOptionPane.showMessageDialog(parent, "Old password doesn't match", "ERROR OCCURED !!", JOptionPane.ERROR_MESSAGE);
			} else if(user.getPassHash().equals(newPassHash = HashManager.md5(newPassword))) {
				JOptionPane.showMessageDialog(parent, "Old and New password cannot be same", "ERROR OCCURED !!", JOptionPane.ERROR_MESSAGE);
			} else if(!newPassword.equals(confirmPassword)) {
				JOptionPane.showMessageDialog(parent, "Confirm password doesn't match", "ERROR OCCURED !!", JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					MUser mUser = new MUser();
					MUserMeta mUserMeta = new MUserMeta();

					user.setPassHash(newPassHash);
					mUser.update(user);

					JOptionPane.showMessageDialog(parent, "Successful !", "Password Changed Successfully! Please Login Again", JOptionPane.INFORMATION_MESSAGE);
				
					headerPanel.getBody().aclInvoke("Logout");

				}
				catch( DatabaseException | SQLException ex) {
					JOptionPane.showMessageDialog(parent, ex.getMessage(), "ERROR OCCURED !!", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}