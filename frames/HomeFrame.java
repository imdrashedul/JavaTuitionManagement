package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import library.*;
import entity.*;
import frames.panels.core.*;

public class HomeFrame extends JFrame
{
	private JPanel panel;
	private Point location;
	private HeaderPanel header;
	private SidebarPanel sidebar;
	private FooterPanel footer;
	private BodyPanel body;

	public HomeFrame(User user)
	{
		super("Tution Management System");
		this.setIconImage(Configuration.SYSTEM_ICON.getImage());
		this.setSize(Configuration.mainWidth(), Configuration.mainHeight());
		this.setLocation(-3, -1);
		this.lockLocation();
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setBackground(new Color(230, 230, 230));

		body = new BodyPanel(user);
		header = new HeaderPanel(user);
		sidebar = new SidebarPanel(body, user);
		footer = new FooterPanel();

		body.setHeader(header);
		header.setBody(body);

		panel = new JPanel();
		panel.setLayout(null);
		panel.add(header);
		panel.add(sidebar);
		panel.add(body);
		panel.add(footer);
		this.add(panel);

		addComponentListener(new ComponentAdapter(){
        public void componentMoved(ComponentEvent e) {
            if (location!=null) HomeFrame.this.setLocation(location);
        }});
	}

	private void lockLocation()
	{
		location = getLocation();
	}
}