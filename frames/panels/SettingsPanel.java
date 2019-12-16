package frames.panels;

import javax.swing.*;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Point;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Image;
import java.sql.SQLException;

import library.*;
import entity.*;
import frames.panels.core.*;

public class SettingsPanel extends TitledPanel {

	private Point coordinates;
	private User user;

	public SettingsPanel(User user, Point coordinates, Dimension dimension) {
		super(dimension);
		this.coordinates = coordinates;
		this.user = user;
		Construct();
	}

	public SettingsPanel(User user, Point coordinates, Dimension dimension, boolean isDoubleBuffered) {
		super(dimension, isDoubleBuffered);
		this.coordinates = coordinates;
		Construct();
	}

	public SettingsPanel(User user, Point coordinates, Dimension dimension, LayoutManager layout) {
		super(dimension, layout);
		this.coordinates = coordinates;
		this.user = user;
		Construct();
	}

	public SettingsPanel(User user, Point coordinates, Dimension dimension, LayoutManager layout, boolean isDoubleBuffered) {
		super(dimension, layout, isDoubleBuffered);
		this.coordinates = coordinates;
		this.user = user;
		Construct();
	}

	private void Construct() {
        
		JPanel profilePanel = new JPanel(); 
		JPanel psswordPanel = new JPanel(); 
        JPanel databasePanel = new JPanel(); 

        profilePanel.setBackground(new Color(255,255,255));	
        psswordPanel.setBackground(new Color(255,255,255));	
        databasePanel.setBackground(new Color(255,255,255));	

        psswordPanel.setLayout(null);
        profilePanel.setLayout(null);
		
	    JTabbedPane tabbedPanel =new JTabbedPane();

		tabbedPanel.add("Profile", profilePanel);  
		tabbedPanel.add("Change Password", psswordPanel);  
        tabbedPanel.add("Database", databasePanel);

        JLabel profilePicture = new JLabel();
        profilePicture.setBounds(20, 20, 100, 100); 

		try {
			profilePicture.setIcon(Helper.squareImage( Helper.bufferedImage(Helper.getAvatar(user)), 100 ));
		} catch( DatabaseException | SQLException | IOException ex ) {
			JOptionPane.showMessageDialog((JFrame) SwingUtilities.getWindowAncestor(this), "ERROR OCCURED !!", ex.getMessage(), JOptionPane.ERROR_MESSAGE);
		}

		profilePanel.add(profilePicture);


		JLabel lOldpass=new JLabel("Old Password");
		lOldpass.setBounds(20,120,150,50);
		
		JPasswordField Oldp=new JPasswordField();
		Oldp.setBounds(150,120,150,30);
		
		JLabel lNewpass=new JLabel("New Password");
		lNewpass.setBounds(20,180,150,50);
		
		JPasswordField Newp=new JPasswordField();
		Newp.setBounds(150,180,150,30);
		
		JLabel lConpass=new JLabel("Confirm Password");
		lConpass.setBounds(20,240,150,50);
		
		JPasswordField Conpass=new JPasswordField();
		Conpass.setBounds(150,240,150,30);
        


		psswordPanel.add(lOldpass);
		psswordPanel.add(Oldp);
		psswordPanel.add(lNewpass);
		psswordPanel.add(Newp);
		psswordPanel.add(lConpass);
		psswordPanel.add(Conpass);
		
	
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
}