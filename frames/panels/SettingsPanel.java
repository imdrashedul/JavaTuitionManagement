package frames.panels;

import javax.swing.*;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Point;

import library.*;
import frames.panels.core.*;

public class SettingsPanel extends TitledPanel {

	private Point coordinates;

	public SettingsPanel(Point coordinates, Dimension dimension) {
		super(dimension);
		this.coordinates = coordinates;
		Construct();
	}

	public SettingsPanel(Point coordinates, Dimension dimension, boolean isDoubleBuffered) {
		super(dimension, isDoubleBuffered);
		this.coordinates = coordinates;
		Construct();
	}

	public SettingsPanel(Point coordinates, Dimension dimension, LayoutManager layout) {
		super(dimension, layout);
		this.coordinates = coordinates;
		Construct();
	}

	public SettingsPanel(Point coordinates, Dimension dimension, LayoutManager layout, boolean isDoubleBuffered) {
		super(dimension, layout, isDoubleBuffered);
		this.coordinates = coordinates;
		Construct();
	}

	private void Construct() {
        
		JPanel pp=new JPanel(); 
        JPanel pp2=new JPanel(); 
		
		pp.setBackground(Color.white);
		pp2.setBackground(Color.white);
		
		
		
	    JTabbedPane Pr=new JTabbedPane();
		Pr.setBounds(20,80,500,500);
		Pr.add("Profile",pp);  
        Pr.add("Change Password",pp2);
		
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
        
		
        
       
		
         //tp.add("help",p3);  
		//Pr.setBackground(Color.white);
		
		
		
	
		
		
		this.setTitle("Settings");
		this.setBackground(new Color(255,255,255));
		this.setLocation(coordinates);
		this.setSize(dimension);
		this.add(Pr);
		pp2.setLayout(null);
		pp2.add(lOldpass);
		pp2.add(Oldp);
		pp2.add(lNewpass);
		pp2.add(Newp);
		pp2.add(lConpass);
		pp2.add(Conpass);
		
		
		disableAdd();
	}
}