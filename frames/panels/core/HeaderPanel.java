package frames.panels.core;

import javax.swing.*;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.awt.event.*;
import java.awt.Cursor;
import java.sql.SQLException;

import library.*;
import entity.*;
import models.*;

public class HeaderPanel extends JPanel {

	private User user;

	public HeaderPanel(User user) {
		super();
		this.user = user;
		Construct();
	}

	public HeaderPanel(User user, boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		this.user = user;
		Construct();
	}

	public HeaderPanel(User user, LayoutManager layout) {
		super(layout);
		this.user = user;
		Construct();
	}

	public HeaderPanel(User user, LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		this.user = user;
		Construct();
	}

	private void Construct() {

		SpringLayout sl = new SpringLayout();
		FlowLayout fl = new FlowLayout();

		JPanel userCard = new JPanel();
		userCard.setLayout(fl);
		userCard.setBackground(new Color(0, 0, 0, 0));

		JLabel userName = new JLabel("Rashedul");
		userName.setFont( Configuration.getFont(Configuration.FONT_REGULAR, Font.BOLD, 14) );
		userName.setForeground(new Color(255, 255, 255));

		JLabel userPic = new JLabel("[x]");

		try {
			userPic = new JLabel(Helper.circularImage( Helper.bufferedImage(Helper.getAvatar(user)), 40 ));
		} catch(DatabaseException | SQLException | IOException ex){
		    JOptionPane.showMessageDialog((JFrame) SwingUtilities.getWindowAncestor(this), "ERROR OCCURED !!", ex.getMessage(), JOptionPane.ERROR_MESSAGE);
		}

		userCard.add(userPic);
		userCard.add(userName);
		userCard.setCursor(new Cursor(Cursor.HAND_CURSOR));
		userCard.addMouseListener(listenUserCard(userCard));
		this.add(userCard);


		JLabel lbLogo = new JLabel(new ImageIcon(Configuration.PATH_IMAGE + "logo.png"));
		this.add(lbLogo);

		this.setLayout(sl);
		this.setBounds(0, 0, Configuration.frameWidth(), Configuration.HEADER_HEIGHT);
		this.setBackground(new Color(66, 157, 46));

		sl.putConstraint(SpringLayout.WEST, lbLogo, 17, SpringLayout.WEST, this);
		sl.putConstraint(SpringLayout.NORTH, lbLogo, 9, SpringLayout.NORTH, this);
		sl.putConstraint(SpringLayout.EAST, userCard, -17, SpringLayout.EAST, this);
		sl.putConstraint(SpringLayout.NORTH, userCard, 5, SpringLayout.NORTH, this);

	}
	private MouseListener listenUserCard(JPanel panel) {
		Color in = new Color(60,142,41);
		Color out = new Color(66,157,46);
		return new MouseAdapter() {
			public void mouseClicked(MouseEvent me){
				System.out.println("clicked");
			}
			public void mousePressed(MouseEvent me){
				panel.setBackground(in);
			}
			public void mouseReleased(MouseEvent me){
				panel.setBackground(out);
			}
			public void mouseEntered(MouseEvent me){
				panel.setBackground(in);
			}
			public void mouseExited(MouseEvent me){
				panel.setBackground(out);
			}
		};
	}
}